package com.aleksandr.shortener.infrastructure.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class ApplicationConfiguration {

    @Bean
    DynamoDbEnhancedClient dynamoDBEnhancedClient() {
        DynamoDbClient client = DynamoDbClient.builder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .region(Region.US_EAST_1)
                .build();

        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(client)
                .build();
    }

}
