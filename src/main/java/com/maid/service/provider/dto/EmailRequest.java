package com.maid.service.provider.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailRequest {

    @Email(message = "Invalid email format")
    @NotBlank(message = "Recipient email cannot be blank")
    private String to;

    @NotBlank(message = "Subject cannot be blank")
    private String subject;

    @NotBlank(message = "Mobile number cannot be blank")
    private String mobileNumber;

    @NotBlank(message = "Sender email cannot be blank")
    private String email;

    private String recipientName;
    private String message;
    private String mailType; // contact, inquiry, job, etc.
}
