package com.maid.service.provider.repository;

import com.maid.service.provider.entity.ContactDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends MongoRepository<ContactDetails ,String > {


}
