package com.aleksandr.shortener.integration;

import com.aleksandr.test.infrastructure.helper.LocalInfrastructureHelper;
import com.aleksandr.test.infrastructure.spring.BaseTestConfiguration;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import static com.aleksandr.test.constant.AwsConstants.CREDENTIALS_PROVIDER;
import static com.aleksandr.test.constant.AwsConstants.REGION;
import static com.aleksandr.test.container.TestLocalStackContainer.getLocalUri;

public class TestIntegrationConfiguration extends BaseTestConfiguration {

    @Override
    public LocalInfrastructureHelper getLocalInfrastructureHelper() {
        return LocalInfrastructureHelper.builder()
                .startCommand(TestIntegrationCommand.START_COMMAND)
                .deploySamCommand(TestIntegrationCommand.DEPLOY_COMMAND)
                .purgeCommand(TestIntegrationCommand.PURGE_COMMAND)
                .build();
    }

    @Bean
    DynamoDbEnhancedClient dynamoDBEnhancedClient() {
        DynamoDbClient client = DynamoDbClient.builder()
                .overrideConfiguration(ClientOverrideConfiguration.builder().build())
                .endpointOverride(getLocalUri())
                .region(REGION)
                .credentialsProvider(CREDENTIALS_PROVIDER)
                .build();

        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(client)
                .build();
    }

}
