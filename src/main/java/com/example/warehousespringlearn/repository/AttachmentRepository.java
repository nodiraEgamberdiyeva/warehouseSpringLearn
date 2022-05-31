package com.example.warehousespringlearn.repository;

import com.example.warehousespringlearn.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
