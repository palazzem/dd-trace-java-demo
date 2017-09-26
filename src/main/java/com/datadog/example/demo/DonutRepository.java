package com.datadog.example.demo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DonutRepository extends CrudRepository<Donut, Long> {

  List<Donut> findByName(@Param("name") String name);
}
