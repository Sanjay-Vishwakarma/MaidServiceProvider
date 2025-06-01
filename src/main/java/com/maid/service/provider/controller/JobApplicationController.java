package com.maid.service.provider.controller;


import com.maid.service.provider.dto.JobApplicationDTO;
import com.maid.service.provider.entity.JobApplication;
import com.maid.service.provider.repository.JobApplicationRepository;
import com.maid.service.provider.util.FileUploadUtil;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin("*")
@RestController
@RequestMapping("/job-applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    private final String uploadDir = "uploads/";

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> submitApplication(
        @RequestPart("data") @Valid JobApplicationDTO dto,
        @RequestPart("aadharCard") MultipartFile aadharCard,
        @RequestPart("image") MultipartFile image
    ) throws IOException {

        // Save files
        String uploadDir = "uploads"; // you can also use "src/main/resources/static/uploads"

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);  // directory बनाओ अगर नहीं है
        }

        String aadharFileName = FileUploadUtil.saveFile(uploadDir, aadharCard.getOriginalFilename(), aadharCard);
        String imageFileName  = FileUploadUtil.saveFile(uploadDir, image.getOriginalFilename(), image);

        // Save to DB
        JobApplication application = modelMapper.map(dto, JobApplication.class);
        application.setAadharCardPath(aadharFileName);
        application.setImagePath(imageFileName);
        repository.save(application);
        return ResponseEntity.ok("Application submitted successfully");
    }
}
