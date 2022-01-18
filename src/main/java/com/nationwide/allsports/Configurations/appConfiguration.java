package com.nationwide.allsports.Configurations;

import com.nationwide.allsports.Api.FootballApiHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class appConfiguration {

    @Bean
    public FootballApiHeaders apiHeaders() {
        return new FootballApiHeaders();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
