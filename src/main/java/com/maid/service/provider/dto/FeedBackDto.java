package com.maid.service.provider.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FeedBackDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Rating is required")
    @Pattern(regexp = "[1-5]", message = "Rating must be between 1-5")
    private String rating;

    @NotBlank(message = "Feedback message is required")
    @Size(max = 500, message = "Feedback must be less than 500 characters")
    private String feedbackMessage;

    // Optional fields - no validation needed
    private String operatingSystem;
    private String deviceType;
    private String ipAddress;
    private String city;
    private String region;
    private String country;
}