package com.assignment.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The type App config.
 */
@Configuration
public class AppConfig {

    /**
     * Gets model mapper.
     *
     * @return the model mapper
     */
    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
