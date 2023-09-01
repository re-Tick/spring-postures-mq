package com.example.springpostgresmq.scheduler;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.nio.file.Paths;

@Component
public class RabbitMQScheduler {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String BUCKET_NAME = "keploy-junk";
    private static final String AWS_ACCESS_KEY = "PLEASE_SET_THIS";
    private static final String AWS_SECRET_KEY = "PLEASE_SET_THIS";

    private static final String FILE_PATH = "/Users/shubham/projects/keploy/java/s3Uploader/s3Uploader/sample.txt";


    @Scheduled(fixedRate = 5000)  // 5 seconds
    public void sendMessage() {
        rabbitTemplate.convertAndSend("myQueue", "Hello from the scheduled task!");
        System.out.println("Message sent to RabbitMQ!");

        // also run the S3 upload job
        uploadS3();
    }

    public void uploadS3() {
        String filePath = FILE_PATH;

        // Initialize the S3 Client
        S3Client s3Client = S3Client.builder()
                .region(Region.AP_SOUTH_1) // Change this to your desired region
                .credentialsProvider(() -> AwsBasicCredentials.create(AWS_ACCESS_KEY, AWS_SECRET_KEY))
                .build();

        // Upload the file to S3
        PutObjectResponse response = s3Client.putObject(
                PutObjectRequest.builder().bucket(BUCKET_NAME).key(Paths.get(filePath).getFileName().toString()).build(),
                Paths.get(filePath)
        );

        System.out.println("File uploaded with ETag: " + response.eTag());
    }
}

