package com.maid.service.provider.repository;

import com.maid.service.provider.entity.FeedBackDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends MongoRepository<FeedBackDetails , String> {


}
