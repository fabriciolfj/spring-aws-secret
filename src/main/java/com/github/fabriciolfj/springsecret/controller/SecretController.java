package com.github.fabriciolfj.springsecret.controller;


import com.github.fabriciolfj.springsecret.config.AwsSecretConfig;
import com.github.fabriciolfj.springsecret.dto.Secret;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/secret")
@RequiredArgsConstructor
public class SecretController {

    private final AwsSecretConfig awsSecretConfig;

    @GetMapping
    public Secret find() {
        return awsSecretConfig.getSecret();
    }
}
