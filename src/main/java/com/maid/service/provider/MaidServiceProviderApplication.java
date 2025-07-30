package com.maid.service.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;


@Slf4j
@SpringBootApplication
@CrossOrigin("*")
@EnableScheduling
public class MaidServiceProviderApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(MaidServiceProviderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
