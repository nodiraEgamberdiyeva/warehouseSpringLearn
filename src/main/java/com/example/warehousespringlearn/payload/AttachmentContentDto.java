package com.example.warehousespringlearn.payload;

import com.example.warehousespringlearn.entity.Attachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttachmentContentDto {
    private byte[] bytes;
    private Integer attachmentId;
}
