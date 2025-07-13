package com.maid.service.provider.service;

import com.maid.service.provider.dto.JobApplicationDTO;
import com.maid.service.provider.entity.JobApplication;
import com.maid.service.provider.helper.PageableResponse;

public interface JobApplicationService {

    void saveJobApplication(JobApplication jobApplication);

    public PageableResponse<JobApplicationDTO> getAllJobApplications(int page, int size);

}
