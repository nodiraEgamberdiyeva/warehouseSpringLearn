package com.example.warehousespringlearn.service;

import com.example.warehousespringlearn.entity.Attachment;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;

    private String uploadPath = "src/main/resources/static";

    public Attachment getAttachmentInfoById(Integer id){
        Optional<Attachment> byId = attachmentRepository.findById(id);
        if(byId.isPresent()){
            return attachmentRepository.findById(id).get();
        }
        else return new Attachment();

    }

    public Page<Attachment> getAllAttachmentInfo(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return attachmentRepository.findAll(pageable);
    }

    public Result deleteAttachmentById(Integer id){
        Optional<Attachment> byId = attachmentRepository.findById(id);
        if (byId.isPresent()){
            File file = new File(uploadPath+"/"+byId.get().getName());
            file.delete();
            attachmentRepository.deleteById(id);
            return new Result("file is deleted", true);
        }
        else return new Result("id is not exist", false);
    }

    public Result upload(MultipartHttpServletRequest request){
        Iterator<String> fileNames = request.getFileNames();
        int unuploadedFiles=0;
        int uploadedFiles=0;
        while (fileNames.hasNext()) {
            unuploadedFiles++;
            MultipartFile file = request.getFile(fileNames.next());
            if (file!=null){
                String[] split = file.getOriginalFilename().split("\\.");
                String name = UUID.randomUUID().toString() +"."+ split[split.length-1];

                File directory = new File(uploadPath);
                if (!directory.exists()){
                    return new Result("file directory is not exist", false);
                }

                Attachment attachment = new Attachment();
                attachment.setSize(file.getSize());
                attachment.setContentType(file.getContentType());
                attachment.setName(name);
                attachment.setFileOriginalName(file.getOriginalFilename());

                attachmentRepository.save(attachment);
                Path path = Paths.get(uploadPath+"/"+name);
                try {
                    Files.copy(file.getInputStream(), path);
                    uploadedFiles++;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        if(uploadedFiles==unuploadedFiles&&uploadedFiles==1){
            return new Result("file is uploaded", true);
        }
        else if (uploadedFiles==unuploadedFiles){
            return new Result("all files are uploaded", true);
        }else if (unuploadedFiles>uploadedFiles&&uploadedFiles>0){
            return new Result("some files are rejected", true);
        }
        else return new Result("files are not uploaded", false);
    }

    public void download(Integer id, HttpServletResponse response){
        Optional<Attachment> byId = attachmentRepository.findById(id);
        if (byId.isPresent()){
            Attachment attachment = byId.get();
            response.setHeader("Content-Disposition",
                    "attachment; fileName=\""+attachment.getFileOriginalName()+"\"");
            response.setContentType(attachment.getContentType());
            try {
                FileInputStream fileInputStream = new FileInputStream(uploadPath+"/"+attachment.getName());
                FileCopyUtils.copy(fileInputStream, response.getOutputStream());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }


}
