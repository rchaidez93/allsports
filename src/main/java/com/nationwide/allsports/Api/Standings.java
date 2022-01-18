package com.nationwide.allsports.Api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class Standings {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FootballApiHeaders apiHeaders;

    @GetMapping("/standings")
    public ResponseEntity<?> getStandings(@RequestParam(value = "league") Integer leagueId,
                                       @RequestParam(value="season") Integer season) throws JsonProcessingException {
        String url = apiHeaders.resourceURL + "/standings?league={queryLeague}&season={querySeason}";

        apiHeaders.setHttpHeaders();
        Map<String, Integer> uriParam = new HashMap<>();
        uriParam.put("queryLeague", leagueId);
        uriParam.put("querySeason", season);

        HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, apiHeaders.entity, String.class, uriParam);
        apiHeaders.setResponse(response);

        return new ResponseEntity<>(apiHeaders.response, HttpStatus.OK);
    }

}
