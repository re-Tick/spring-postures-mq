package com.example.springpostgresmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@SpringBootApplication
public class SpringPostgresMqApplication {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        // junk call to simulate MSIL failure case
        try {
            String response = restTemplate.getForObject("http://localhost:1000/your-endpoint", String.class);
            System.out.println(response);
        } catch (Exception e) {
            System.err.println("API call failed: " + e.getMessage());
        }


        SpringApplication.run(SpringPostgresMqApplication.class, args);
    }

}
