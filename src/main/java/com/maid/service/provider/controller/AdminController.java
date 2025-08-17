package com.maid.service.provider.controller;


import com.maid.service.provider.dto.*;
import com.maid.service.provider.helper.PageableResponse;
import com.maid.service.provider.service.AdminService;
import com.maid.service.provider.service.JobApplicationService;
import com.maid.service.provider.service.MaidService;
import com.maid.service.provider.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MaidService maidService;

    @Autowired
    private JobApplicationService jobApplicationService;


    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminHello() {
        return "Hello from Admin!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/getAllContacts")
    public ResponseEntity<PageableResponse<ContactDto>> getAllContacts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Current auth: " + auth);

        PageableResponse<ContactDto> response = maidService.getAllContacts(page, size);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getAllInquiries")
    public ResponseEntity<PageableResponse<InquiryDetailsDto>> getAllInquiries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageableResponse<InquiryDetailsDto> response = maidService.getAllInquiryDetails(page, size);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getAllFeedbacks")
    public ResponseEntity<PageableResponse<FeedBackDto>> getAllFeedbacks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageableResponse<FeedBackDto> response = maidService.getAllFeedbacks(page, size);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getAllApllicants")
    public ResponseEntity<PageableResponse<JobApplicationDTO>> getAllJobApplications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageableResponse<JobApplicationDTO> response = jobApplicationService.getAllJobApplications(page, size);
        return ResponseEntity.ok(response);
    }
}
