package com.maid.service.provider.serviceImpl;

import com.maid.service.provider.entity.JobApplication;
import com.maid.service.provider.repository.JobApplicationRepository;
import com.maid.service.provider.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class JobApplicationServiceImpl  implements JobApplicationService {


    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Override
    public void saveJobApplication(JobApplication jobApplication) {
        jobApplicationRepository.save(jobApplication);
    }

}
