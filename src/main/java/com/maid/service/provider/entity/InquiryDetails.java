package com.maid.service.provider.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "inquiries")
public class InquiryDetails {

    @Id
    private String id;

    private String name;
    private String location;
    private String workHours;
    private String maidServiceType;
    private String email;
    private String phone;
    private String description;
    private String operatingSystem;   // (Optional) parsed from user agent
    private String deviceType;
    private String ipAddress;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    private String city;
    private String region;
    private String country;

}
