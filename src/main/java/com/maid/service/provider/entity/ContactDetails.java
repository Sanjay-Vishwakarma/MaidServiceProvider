package com.maid.service.provider.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Document(collection = "contacts")
public class ContactDetails {

    @Id
    private String id;

    private String fullName;
    private String phoneNumber;
    private String email;
    private String description;
    private String operatingSystem;   // (Optional) parsed from user agent
    private String deviceType;
    private String ipAddress;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;


}
