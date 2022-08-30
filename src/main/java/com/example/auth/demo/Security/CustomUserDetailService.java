package com.example.auth.demo.Security;

import com.example.auth.demo.Entities.User;
import com.example.auth.demo.Exceptions.ResourceNotFoundException;
import com.example.auth.demo.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    // getting user from DB
    User user =
      this.userRepo.findByUsername(username)
        .orElseThrow(
          () -> new ResourceNotFoundException("User", "email", username)
        );
    return user;
  }
}
