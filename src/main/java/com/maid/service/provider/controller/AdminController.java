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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private MaidService maidService;

    @Autowired
    private JobApplicationService jobApplicationService;



    @PostMapping("/adminLogin")
    public ResponseEntity<?> adminLogin(@RequestBody AdminDto adminDto) {
        Response response = adminService.adminLogin(adminDto);
        System.out.println("response = " + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/getAllContacts")
    public ResponseEntity<PageableResponse<ContactDto>> getAllContacts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
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

    @GetMapping("/getAllApllicants")
    public ResponseEntity<PageableResponse<JobApplicationDTO>> getAllJobApplications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageableResponse<JobApplicationDTO> response = jobApplicationService.getAllJobApplications(page, size);
        return ResponseEntity.ok(response);
    }
}
