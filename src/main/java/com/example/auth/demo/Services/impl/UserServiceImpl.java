package com.example.auth.demo.Services.impl;

import com.example.auth.demo.Entities.User;
import com.example.auth.demo.Exceptions.*;
import com.example.auth.demo.Payloads.UserDto;
import com.example.auth.demo.Repositories.UserRepo;
import com.example.auth.demo.Services.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private ModelMapper modelmapper;

  @Override
  public UserDto createUser(UserDto userDto) {
    User user = this.dtoToUser(userDto);
    User savedUser = this.userRepo.save(user);
    return this.userToDto(savedUser);
  }

  @Override
  public UserDto updateUser(UserDto userDto, String userId) {
    User user =
      this.userRepo.findById(userId)
        .orElseThrow(
          () -> new ResourceNotFoundException("User", " id ", userId)
        );
    user.setUsername(userDto.getUsername());
    user.setPassword(userDto.getPassword());
    User updatedUser = this.userRepo.save(user);
    UserDto userDto1 = this.userToDto(updatedUser);
    return userDto1;
  }

  @Override
  public UserDto getUserById(String userId) {
    User user =
      this.userRepo.findById(userId)
        .orElseThrow(
          () -> new ResourceNotFoundException("User", " id ", userId)
        );
    return this.userToDto(user);
  }

  @Override
  public List<UserDto> getAllUsers() {
    List<User> users = this.userRepo.findAll();
    List<UserDto> userDtos = users
      .stream()
      .map(user -> this.userToDto(user))
      .collect(Collectors.toList());
    return userDtos;
  }

  @Override
  public void deleteUser(String userId) {
    User user =
      this.userRepo.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    this.userRepo.delete(user);
  }

  public User dtoToUser(UserDto userDto) {
    User user = this.modelmapper.map(userDto, User.class);
    return user;
  }

  public UserDto userToDto(User user) {
    UserDto userDto = this.modelmapper.map(user, UserDto.class);
    return userDto;
  }
}
