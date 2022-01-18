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
public class Fixtures {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FootballApiHeaders apiHeaders;

    HttpEntity<String> response;

    @GetMapping("/fixtures")
    public ResponseEntity<?> getFixtures(@RequestParam(value = "league") String leagueId,
                                      @RequestParam(value="season") String season,
                                      @RequestParam(value = "live", required = false) String live,
                                      @RequestParam(value="team", required = false) String teamId,
                                      @RequestParam(value = "from", required = false) String from,
                                      @RequestParam(value = "to", required = false) String to) throws JsonProcessingException {
        String url = apiHeaders.resourceURL + "/fixtures?league={queryLeague}&season={querySeason}";

        apiHeaders.setHttpHeaders();
        Map<String, String> uriParam = new HashMap<>();
        uriParam.put("queryLeague", leagueId);
        uriParam.put("querySeason", season);

        if(live != null){
            url += "&live=all";
        }
        if(teamId != null){
            url += "&team={queryTeam}";
            uriParam.put("queryTeam", teamId);
        }
        if(from != null){
            url += "&from={queryFrom}";
            uriParam.put("queryFrom", from);
        }
        if(to != null){
            url += "&to={queryTo}";
            uriParam.put("queryTo", to);
        }

        response = restTemplate.exchange(url, HttpMethod.GET, apiHeaders.entity, String.class, uriParam);
        apiHeaders.setResponse(response);
        return new ResponseEntity<>(apiHeaders.response, HttpStatus.OK);
    }

    @GetMapping("/fixtures/events")
    public ResponseEntity<?> getFixtureEvents(@RequestParam(value = "fixtureId") Integer fixtureId, @RequestParam(value= "teamId") Integer teamId) throws JsonProcessingException {
        String url = apiHeaders.resourceURL + "/fixtures/events?fixture={queryFixtureId}&team={queryTeamId}";
        apiHeaders.setHttpHeaders();
        Map<String, Integer> uriParam = new HashMap<>();
        uriParam.put("queryFixtureId", fixtureId);
        uriParam.put("queryTeamId", teamId);

        response = restTemplate.exchange(url, HttpMethod.GET, apiHeaders.entity, String.class, uriParam);
        apiHeaders.setResponse(response);

        return new ResponseEntity<>(apiHeaders.response, HttpStatus.OK);
    }

    @GetMapping("/fixtures/statistics")
    public ResponseEntity<?> getFixtureStatistics(@RequestParam(value = "fixtureId") Integer fixtureId) throws JsonProcessingException {
        String url = apiHeaders.resourceURL + "/fixtures/statistics?fixture={queryFixtureId}";
        apiHeaders.setHttpHeaders();
        Map<String, Integer> uriParam = new HashMap<>();
        uriParam.put("queryFixtureId", fixtureId);
//        uriParam.put("queryTeamId", teamId);

        response = restTemplate.exchange(url, HttpMethod.GET, apiHeaders.entity, String.class, uriParam);
        apiHeaders.setResponse(response);

        return new ResponseEntity<>(apiHeaders.response, HttpStatus.OK);
    }

}
