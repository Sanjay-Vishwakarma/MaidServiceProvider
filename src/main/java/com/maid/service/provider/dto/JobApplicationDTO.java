package com.maid.service.provider.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationDTO {

    @NotBlank
    private String fullName;

    @NotBlank
    @Pattern(regexp = "\\d{10}")
    private String mobile;

    private String category;
    private String maritalStatus;

    @Min(18)
    private int age;

    private String religion;
    private String gender;
    private String passport;
    private String education;
    private String workingHours;

    @Size(max = 1000)
    private String address;

    private String workLocation;
    private Integer expectedSalary;
    private Integer experience;

    private String aadharCardUrl;
    private String profileImageUrl;
    private LocalDateTime createdAt;
    private String createdAtFormatted;
    private List<String> languages;
}
