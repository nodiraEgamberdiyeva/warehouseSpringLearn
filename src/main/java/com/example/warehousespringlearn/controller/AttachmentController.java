package com.example.warehousespringlearn.controller;

import com.example.warehousespringlearn.entity.Attachment;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    @GetMapping("/info")
    public Page<Attachment> getAllAttachmentsInfo(@RequestParam int page){
        return attachmentService.getAllAttachmentInfo(page);
    }

    @GetMapping("/info/{id}")
    public Attachment getAttachmentInfoById(@PathVariable Integer id){
        return attachmentService.getAttachmentInfoById(id);
    }

    @GetMapping("/download/{id}")
    public void download(@PathVariable Integer id, HttpServletResponse response){
        attachmentService.download(id, response);
    }

    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request){
        return attachmentService.upload(request);
    }

    @DeleteMapping("/{id}")
    public Result deleteAttachmentById(@PathVariable Integer id){
        return attachmentService.deleteAttachmentById(id);
    }

}
