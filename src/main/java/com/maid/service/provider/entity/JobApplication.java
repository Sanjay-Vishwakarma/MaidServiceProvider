package com.maid.service.provider.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "job_applications")
public class JobApplication {

    @Id
    private String id;

    private String fullName;
    private String mobile;
    private String category;
    private String maritalStatus;
    private int age;
    private String religion;
    private String gender;
    private String passport;
    private String education;
    private String workingHours;
    private String address;
    private String workLocation;
    private Integer expectedSalary;
    private Integer experience;
    private List<String> languages;

    private String aadharCardUrl;
    private String profileImageUrl;
}
