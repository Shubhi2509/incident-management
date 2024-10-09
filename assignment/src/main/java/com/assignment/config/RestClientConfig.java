package com.assignment.config;

import com.assignment.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * The type Rest client config.
 */
@Configuration
public class RestClientConfig {

    @Value("${postcode.base.url}")
    private String BASE_URL;

    /**
     * Gets pincode rest client.
     *
     * @return the pincode rest client
     */
    @Bean
    RestClient getPincodeRestClient() {
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, (req, res) -> {
                    throw new ResourceNotFoundException("Server error occurred");
                })
                .build();
    }


}

