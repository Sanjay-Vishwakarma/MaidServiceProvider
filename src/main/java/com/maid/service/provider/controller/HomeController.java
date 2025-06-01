package com.maid.service.provider.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/systemCheck")
    public ResponseEntity<?> systemCheck() {
        Map <String, String> map = new HashMap<>();
        map.put("Time", LocalDateTime.now().toString());
        map.put("System", "System is running");
        map.put("Status", "OK");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
