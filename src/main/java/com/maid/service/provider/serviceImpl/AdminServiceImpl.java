package com.maid.service.provider.serviceImpl;

import com.maid.service.provider.dto.AdminDto;
import com.maid.service.provider.entity.Admin;
import com.maid.service.provider.exception.AppException;
import com.maid.service.provider.repository.AdminRepository;
import com.maid.service.provider.service.AdminService;
import com.maid.service.provider.util.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Response adminLogin(AdminDto adminDto) {
        Response response = new Response();
        try {
            Optional<Admin> byUsername = adminRepository.findByUsername(adminDto.getUsername());
            if (byUsername.isPresent()) {
                Admin admin = byUsername.get();

                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                boolean isPasswordMatch = encoder.matches(adminDto.getPassword(), admin.getPassword());
                if (!isPasswordMatch) {
                    response.setStatusCode(401);
                    response.setMessage("Invalid password.");
                    return response;
                }
                response.setStatusCode(200);
                response.setMessage("Login successful.");
                response.setData(admin.getUsername());
            } else {
                response.setStatusCode(401);
                response.setMessage("Invalid username.");
                return response;
            }
        } catch (Exception e) {
            throw new AppException("Login failed.", e.getMessage());
        }
        return response;
    }

    @Override
    public Response saveAdmin(AdminDto adminDto) {
        Response response = new Response();
        try {
            Admin adminMapped = modelMapper.map(adminDto, Admin.class);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            adminMapped.setPassword(encoder.encode(adminMapped.getPassword()));
            adminRepository.save(adminMapped);
            response.setMessage("Admin saved successfully.");
            response.setStatusCode(200);
            response.setData(adminMapped);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("Failed to save admin: " , e.getMessage());
        }
        return response;
    }

}
