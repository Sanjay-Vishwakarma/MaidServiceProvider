package com.maid.service.provider;

import com.maid.service.provider.dto.AdminDto;
import com.maid.service.provider.service.AdminService;
import com.maid.service.provider.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("*")
public class MaidServiceProviderApplication implements CommandLineRunner {


    @Autowired
    private AdminService adminService;

    public static void main(String[] args) {
        SpringApplication.run(MaidServiceProviderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try{
//            AdminDto  adminDto = new AdminDto();
//            adminDto.setUsername("admin");
//            adminDto.setPassword("admin@123");
//            Response response = adminService.saveAdmin(adminDto);
//            System.out.println("response ::"+response);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
