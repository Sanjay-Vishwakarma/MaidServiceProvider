package com.maid.service.provider.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "feedbacks")
public class FeedBackDetails {
    @Id
    private String id;

    private String name;
    private String email;
    private String rating;
    private String feedbackMessage;
    private String operatingSystem;   // (Optional) parsed from user agent
    private String deviceType;
    private String ipAddress;         // User's IP address
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    private String city;
    private String region;
    private String country;
}