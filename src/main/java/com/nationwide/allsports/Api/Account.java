package com.nationwide.allsports.Api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class Account {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FootballApiHeaders apiHeaders;

    @GetMapping("/status")
    public ResponseEntity<?> getStatus() {

        String url = apiHeaders.resourceURL + "/status";
        apiHeaders.setHttpHeaders();
        String response = restTemplate.exchange(url, HttpMethod.GET, apiHeaders.entity, String.class).getBody();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
