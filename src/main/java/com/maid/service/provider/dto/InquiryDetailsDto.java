package com.maid.service.provider.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class InquiryDetailsDto {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Location is required")
    @Size(min = 2, max = 100, message = "Location must be between 2 and 100 characters")
    private String location;

    @NotBlank(message = "Work hours is required")
    @Size(min = 1, max = 20, message = "Work hours must be valid")
    private String workHours;

    @NotBlank(message = "Maid service type is required")
    @Size(min = 2, max = 50, message = "Maid service type must be between 2 and 50 characters")
    private String maidServiceType;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    // Optional fields - no validation needed
    private String operatingSystem;
    private String deviceType;
    private String ipAddress;
}
