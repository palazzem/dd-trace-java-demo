package com.datadog.example.demo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BeerRepository extends CrudRepository<Beer, Long> {

  List<Beer> findByName(@Param("name") String name);
}
