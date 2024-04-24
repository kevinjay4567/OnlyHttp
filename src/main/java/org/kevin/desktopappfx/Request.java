package org.kevin.desktopappfx;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private final Map<String, String> response = new HashMap<>();

    public Map<String, String> get(String url) {
        HttpResponse<JsonNode> httpResponse = Unirest.get(url).asJson();

        response.put("body", httpResponse.getBody().toPrettyString());
        response.put("status", String.valueOf(httpResponse.getStatus()));

        return response;
    }

    public Map<String, String> post(String url, String requestBody) {
        HttpResponse<JsonNode> httpResponse = Unirest.post(url).header("Content-Type", "application/json")
                .body(requestBody).asJson();

        response.put("body", httpResponse.getBody().toPrettyString());
        response.put("status", String.valueOf(httpResponse.getStatus()));

        return response;
    }
}
