package com.maid.service.provider.repository;

import com.maid.service.provider.entity.InquiryDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InquiryDetailsRepository extends MongoRepository<InquiryDetails, String> {
}
