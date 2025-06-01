package com.maid.service.provider.repository;

import com.maid.service.provider.entity.JobApplication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobApplicationRepository extends MongoRepository<JobApplication, String> {
}
