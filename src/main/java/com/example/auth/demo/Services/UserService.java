package com.example.auth.demo.Services;

import com.example.auth.demo.Payloads.UserDto;
import java.util.List;

public interface UserService {
  UserDto createUser(UserDto user);
  UserDto updateUser(UserDto user, String userId);
  UserDto getUserById(String userId);
  List<UserDto> getAllUsers();
  void deleteUser(String userId);
}
