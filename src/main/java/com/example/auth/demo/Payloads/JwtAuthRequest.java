package com.example.auth.demo.Payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {

  private String username;
  private String password;
}
