package com.github.fabriciolfj.springsecret.config;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClient;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.springsecret.dto.Secret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSecretConfig {

    @Value("${aws.secret.name}")
    private String value;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public Secret getSecret() {
        final AWSSecretsManager client = AWSSecretsManagerClient.builder().withRegion("us-east-2").build();
        final GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(value);

        try {
            final GetSecretValueResult result = client.getSecretValue(getSecretValueRequest);
            return objectMapper.readValue(result.getSecretString(), Secret.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
