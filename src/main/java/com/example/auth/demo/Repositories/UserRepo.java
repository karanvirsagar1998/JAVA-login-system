package com.example.auth.demo.Repositories;

import com.example.auth.demo.Entities.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);
}
