package com.nationwide.allsports.Api;

import lombok.Data;

import java.util.Map;

@Data
public class Response {
    private Integer remainingCount;

    private Map<String, Object> data;
}
