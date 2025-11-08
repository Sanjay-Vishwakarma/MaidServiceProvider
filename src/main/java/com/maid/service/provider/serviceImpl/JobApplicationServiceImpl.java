package com.maid.service.provider.serviceImpl;

import com.maid.service.provider.dto.EmailRequest;
import com.maid.service.provider.dto.JobApplicationDTO;
import com.maid.service.provider.entity.JobApplication;
import com.maid.service.provider.helper.PageableResponse;
import com.maid.service.provider.repository.JobApplicationRepository;
import com.maid.service.provider.service.EmailService;
import com.maid.service.provider.service.JobApplicationService;import com.maid.service.provider.util.Functions;
import com.maid.service.provider.util.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JobApplicationServiceImpl  implements JobApplicationService {


    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailService emailService;

    @Override
    public void saveJobApplication(JobApplication jobApplication) {
        jobApplicationRepository.save(jobApplication);
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setMobileNumber(jobApplication.getMobile());
        emailRequest.setEmail("For job application");
        emailRequest.setRecipientName(jobApplication.getFullName());
        emailRequest.setMessage(jobApplication.getWorkLocation());
        emailRequest.setMailType("Job");
        // send the email
//        Response response = emailService.sendMail(emailRequest);
//            System.out.println("email contact : "+response);
    }


    @Override
    public PageableResponse<JobApplicationDTO> getAllJobApplications(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<JobApplication> applicationsPage = jobApplicationRepository.findAll(pageable);

        List<JobApplicationDTO> dtoList = applicationsPage
                .getContent()
                .stream()
                .map(app -> modelMapper.map(app, JobApplicationDTO.class))
                .toList();

        return PageableResponse.<JobApplicationDTO>builder()
                .content(dtoList)
                .pageNumber(applicationsPage.getNumber())
                .pageSize(applicationsPage.getSize())
                .totalElements(applicationsPage.getTotalElements())
                .totalPages(applicationsPage.getTotalPages())
                .lastPage(applicationsPage.isLast())
                .build();
    }

}
