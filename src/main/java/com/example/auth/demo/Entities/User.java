package com.example.auth.demo.Entities;

import java.util.*;
import java.util.stream.Collectors;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User implements UserDetails {

  @Id
  private String id;

  private String username;
  private String password;

  // @DBRef
  // private Set<Role> roles = new HashSet<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // List<SimpleGrantedAuthority> authorities =
    //   this.roles.stream()
    //     .map(role -> new SimpleGrantedAuthority(role.getName()))
    //     .collect(Collectors.toList());
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
