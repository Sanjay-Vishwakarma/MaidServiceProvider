package com.maid.service.provider.controller;


import com.maid.service.provider.dto.JobApplicationDTO;
import com.maid.service.provider.entity.JobApplication;
import com.maid.service.provider.service.JobApplicationService;
import com.maid.service.provider.util.CloudinaryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/job-applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> submitApplication(
            @RequestPart("data") @Valid JobApplicationDTO dto,
            @RequestPart("aadharCard") MultipartFile aadharCard,
            @RequestPart("image") MultipartFile image
    ) throws IOException {

        // Validate image content types
        if (!isValidImage(aadharCard) || !isValidImage(image)) {
            return ResponseEntity.badRequest().body("Only JPG, JPEG, or PNG files are allowed.");
        }
        String name = dto.getFullName().replace(" ","_");
        // Upload images to Cloudinary
        String imageId = UUID.randomUUID().toString().replace("-","").substring(0,4);
        String aadharName = name+"_"+"aadhar_" + imageId;
        String profileName = name+"_"+"profile_" + imageId;
        String aadharUrl = cloudinaryService.uploadImage(aadharCard,aadharName);
        String imageUrl = cloudinaryService.uploadImage(image,profileName);

        if (aadharUrl == null || imageUrl == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed.");
        }

        // Map DTO to Entity
        JobApplication application = modelMapper.map(dto, JobApplication.class);
        application.setCreatedAt(LocalDateTime.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a", new Locale("hi", "IN"));
        String formattedDate = application.getCreatedAt().format(formatter);
        application.setCreatedAtFormatted(formattedDate);
        application.setAadharCardUrl(aadharUrl);
        application.setProfileImageUrl(imageUrl);

        // Save application to DB
        jobApplicationService.saveJobApplication(application);

        return ResponseEntity.ok("Application submitted successfully");
    }

    private boolean isValidImage(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && (
                contentType.equals("image/jpeg") ||
                        contentType.equals("image/png") ||
                        contentType.equals("image/jpg")
        );
    }
}
