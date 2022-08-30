package com.example.auth.demo.Controllers;

import com.example.auth.demo.Payloads.ApiResponse;
import com.example.auth.demo.Payloads.UserDto;
import com.example.auth.demo.Services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/")
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
    UserDto createdUserDto = this.userService.createUser(userDto);
    return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable String userId){
    UserDto updatedUser = this.userService.updateUser(userDto, userId);
    return ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId){
    this.userService.deleteUser(userId);
    return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
  }

  @GetMapping()
  public ResponseEntity<List<UserDto>> getAllUsers(){
    return ResponseEntity.ok(this.userService.getAllUsers());
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserDto> getSingleUser(@PathVariable String userId){
    return ResponseEntity.ok(this.userService.getUserById(userId));
  }
}
