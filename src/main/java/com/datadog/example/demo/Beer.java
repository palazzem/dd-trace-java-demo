package com.datadog.example.demo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Data
@Entity
public class Beer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Length(max = 80)
  private String name;

  @Range(min = 1, max = 10)
  private int hops;
}
