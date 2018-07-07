package com.hungerstation.grpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Waleed Arafa on 18/06/2018.
 */
@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    AutomatedIssuesServiceGrpcClient client;

    @Override
    public void run(String... strings) throws Exception {
        try {
            //client.create();
            //  client.fetch();
            // client.filter();
            client.publishEvent();
        } finally {
            client.shutdown();
        }
    }
}
