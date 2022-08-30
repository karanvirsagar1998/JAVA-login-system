package com.example.auth.demo.Entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Role {

  @Id
  private String id;

  private String name;
}
