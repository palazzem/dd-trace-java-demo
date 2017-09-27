package com.datadog.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class PairingController {
  private final BeerRepository beerRepo;
  private final DonutRepository donutRepo;
  private final TasteClient client;

  public PairingController(BeerRepository beerRepo, DonutRepository donutRepo) {
    this.beerRepo = beerRepo;
    this.donutRepo = donutRepo;

    client =
        Feign.builder()
            .client(new OkHttpClient())
            .decoder(new JacksonDecoder())
            .target(TasteClient.class, "http://taster:5001");
  }

  @RequestMapping("/pair/beer")
  public @ResponseBody ResponseEntity<Map<String, Object>> pair(@RequestParam("name") String name) {
    List<Beer> byName = beerRepo.findByName(name);
    if (byName.isEmpty()) {
      return new ResponseEntity<>(NOT_FOUND);
    }

    Beer beer = byName.get(0);
    return new ResponseEntity<>(Collections.singletonMap("match", bestMatch(beer)), OK);
  }

  private JsonNode bestMatch(Beer beer) {
    List<String> donuts =
        StreamSupport.stream(donutRepo.findAll().spliterator(), false)
            .map(donut -> donut.getName())
            .collect(Collectors.toList());

    JsonNode pairing = client.findPairing(beer.getName(), donuts);
    return pairing.get("candidate");
  }
}
