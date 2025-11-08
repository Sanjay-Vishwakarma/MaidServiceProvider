//package com.maid.service.provider.serviceImpl;
//
//import com.maid.service.provider.dto.EmailRequest;
//import com.maid.service.provider.dto.EmailResponse;
//import com.maid.service.provider.service.EmailService;
//import com.maid.service.provider.util.Response;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.*;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//import jakarta.mail.internet.InternetAddress;
//
//import java.io.UnsupportedEncodingException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//@Service
//public class EmailServiceImpl implements EmailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Autowired
//    private TemplateEngine templateEngine;
//
//
//    @Override
//    public Response sendMail(EmailRequest request) {
//        Response response = new Response();
//        try {
//            String subject = "New Inquiry From 24in Maid Sservice";
//            String to = "24inmaidservice@gmail.com";
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom(new InternetAddress("webhosttest2023@gmail.com", "24IN MAID SERVICE"));
//            helper.setTo(to);
//            helper.setSubject(subject);
//            String htmlContent = buildEmailContentFromTemplate(request);
//            helper.setText(htmlContent, true);
//            mailSender.send(message);
//
//            EmailResponse emailResponse = new EmailResponse(
//                    "Email sent successfully!",
//                    to,
//                    subject,
//                    LocalDateTime.now().toString()
//            );
//
//            response.setData(emailResponse);
//            response.setStatusCode(200);
//            response.setMessage("Email sent successfully!");
//
//        } catch (MessagingException e) {
//            throw new RuntimeException("Failed to send email", e);
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
//        return response;
//    }
//
//    private String buildEmailContentFromTemplate(EmailRequest request) {
//        Context context = new Context();
//        context.setVariable("name", request.getRecipientName());
//        context.setVariable("email", request.getEmail());
//        context.setVariable("mobile", request.getMobileNumber());
//        context.setVariable("message", request.getMessage());
//        context.setVariable("type", request.getMailType());
//        context.setVariable("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a")));
//
//        return templateEngine.process("email-template", context);
//    }
//}
