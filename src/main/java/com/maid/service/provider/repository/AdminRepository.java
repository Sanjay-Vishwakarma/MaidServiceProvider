package com.maid.service.provider.repository;

import com.maid.service.provider.dto.AdminDto;
import com.maid.service.provider.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin,String> {

    Optional<Admin> findByUsername(String username);
}
