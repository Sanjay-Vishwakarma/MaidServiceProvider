package com.maid.service.provider.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;


@Component
@Slf4j
public class HearBeat {

    @Value("${health.check}")
    private  String healthCheckUrl;

//    @Scheduled(fixedRate = 300000) // every 5 minutes
//    @Scheduled(fixedRate = 10000) // every 10 seconds
    public void heartBeat() {
        try {
            URL url = new URL(healthCheckUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.getResponseCode();
            conn.disconnect();
            log.info("heart beat running.....");
//            System.out.println("heart beat running.....");
        } catch (Exception e) {
            log.error("heart beat failing .....",e);
        }
    }
}
