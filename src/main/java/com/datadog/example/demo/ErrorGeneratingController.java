package com.datadog.example.demo;

import java.io.FileNotFoundException;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gen-error")
public class ErrorGeneratingController {

  @RequestMapping
  public String index() throws Exception {
    int select = ThreadLocalRandom.current().nextInt() % 3;
    switch (select) {
      case 0:
        throw new NullPointerException();
      case 1:
        throw new FileNotFoundException();
      default:
        throw new RuntimeException();
    }
  }
}
