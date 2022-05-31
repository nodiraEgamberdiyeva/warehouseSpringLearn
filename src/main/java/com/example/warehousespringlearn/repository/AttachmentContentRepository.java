package com.example.warehousespringlearn.repository;

import com.example.warehousespringlearn.entity.Attachment;
import com.example.warehousespringlearn.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {
}
