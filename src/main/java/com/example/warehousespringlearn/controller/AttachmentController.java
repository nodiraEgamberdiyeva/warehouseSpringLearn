package com.example.warehousespringlearn.controller;

import com.example.warehousespringlearn.entity.Attachment;
import com.example.warehousespringlearn.payload.Result;
import com.example.warehousespringlearn.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    private final AttachmentService attachmentService;

    @Autowired
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PreAuthorize("hasAnyAuthority('READ_ALL_ATTACHMENT', 'ALL')")
    @GetMapping("/info")
    public Page<Attachment> getAllAttachmentsInfo(@RequestParam int page){
        return attachmentService.getAllAttachmentInfo(page);
    }

    @PreAuthorize("hasAnyAuthority('READ_ATTACHMENT', 'ALL')")
    @GetMapping("/info/{id}")
    public Attachment getAttachmentInfoById(@PathVariable Integer id){
        return attachmentService.getAttachmentInfoById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADD_ATTACHMENT', 'ALL')")
    @GetMapping("/download/{id}")
    public void download(@PathVariable Integer id, HttpServletResponse response){
        attachmentService.download(id, response);
    }

    @PreAuthorize("hasAnyAuthority('EDIT_ATTACHMENT', 'ALL')")
    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request){
        return attachmentService.upload(request);
    }

    @PreAuthorize("hasAnyAuthority('DELETE_ATTACHMENT', 'ALL')")
    @DeleteMapping("/{id}")
    public Result deleteAttachmentById(@PathVariable Integer id){
        return attachmentService.deleteAttachmentById(id);
    }

}
