package com.datadog.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import feign.Param;
import feign.RequestLine;
import java.util.List;

public interface TasteClient {
  @RequestLine("GET /taste?beer={beer}&donuts={donuts}")
  JsonNode findPairing(@Param("beer") String beer, @Param("donuts") List<String> donuts);
}
