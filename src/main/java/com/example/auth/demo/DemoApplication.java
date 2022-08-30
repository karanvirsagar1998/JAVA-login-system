package com.example.auth.demo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

  @Autowired
  private PasswordEncoder passwordWEncoder;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  public ModelMapper mapperMapper() {
    return new ModelMapper();
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println(this.passwordWEncoder.encode("123"));
  }
}
