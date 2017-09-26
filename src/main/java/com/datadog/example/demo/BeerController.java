package com.datadog.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/beers")
public class BeerController {

  private final BeerRepository repo;

  public BeerController(BeerRepository repo) {
    this.repo = repo;
  }

  @RequestMapping
  public @ResponseBody Iterable<Beer> index() {
    return repo.findAll();
  }

  @RequestMapping("/{name}")
  public @ResponseBody Iterable<Beer> byName(@PathVariable("name") String name) {
    return repo.findByName(name);
  }
}
