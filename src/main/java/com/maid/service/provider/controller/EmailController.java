//package com.maid.service.provider.controller;
//
//import com.maid.service.provider.dto.EmailRequest;
//import com.maid.service.provider.dto.EmailResponse;
//import com.maid.service.provider.service.EmailService;
//import com.maid.service.provider.util.Response;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//
//@RestController
//@RequestMapping("/email")
//public class EmailController {
//
//    @Autowired
//    private EmailService emailService;
//
//
//    // Test purpose
//    @PostMapping("/send")
//    public ResponseEntity<Response> sendMail(@Valid @RequestBody EmailRequest request) {
//        Response response = emailService.sendMail(request);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//}
