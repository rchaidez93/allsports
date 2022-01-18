package com.nationwide.allsports.Api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.Map;

public class FootballApiHeaders {

    @Value("${resource.football_api}")
    protected String resourceURL;

    @Value("${resource.host}")
    protected String host;

    @Value("${resource.key}")
    protected String key;

    HttpEntity<String> entity;

    Response response = new Response();

    protected void setHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", host);
        headers.set("x-rapidapi-key", key);
        entity = new HttpEntity<>(headers);
    }

    protected void setResponse(HttpEntity<String> apiResponse) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String apiResult = apiResponse.getBody();
        HttpHeaders headers = apiResponse.getHeaders();

        response.setRemainingCount(Integer.valueOf(headers.get("x-ratelimit-requests-remaining").get(0)));
        Map<String, Object> map = objectMapper.readValue(apiResult, new TypeReference<Map<String, Object>>() {});
        response.setData(map);
    }
}
