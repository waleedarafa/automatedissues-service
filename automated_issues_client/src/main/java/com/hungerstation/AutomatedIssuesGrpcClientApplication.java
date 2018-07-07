package com.hungerstation;

import com.hungerstation.grpc.AutomatedIssuesServiceGrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutomatedIssuesGrpcClientApplication {


    public static void main(String[] args) {

        SpringApplication.run(AutomatedIssuesGrpcClientApplication.class, args);
    }
}
