package com.datadog.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donuts")
public class DonutsController {

  private final DonutRepository repo;

  public DonutsController(DonutRepository repo) {
    this.repo = repo;
  }

  @RequestMapping
  public @ResponseBody Iterable<Donut> index() {
    return repo.findAll();
  }

  @RequestMapping("/{name}")
  public @ResponseBody Iterable<Donut> byName(@PathVariable("name") String name) {
    return repo.findByName(name);
  }
}
