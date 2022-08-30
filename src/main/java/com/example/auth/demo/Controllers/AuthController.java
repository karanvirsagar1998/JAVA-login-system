package com.example.auth.demo.Controllers;

import com.example.auth.demo.Payloads.ApiResponse;
import com.example.auth.demo.Payloads.JwtAuthRequest;
import com.example.auth.demo.Payloads.JwtAuthResponse;
import com.example.auth.demo.Security.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth/")
public class AuthController {

  @Autowired
  private JwtTokenHelper jwtTokenHelper;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/login")
  public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) {
    JwtAuthResponse response = new JwtAuthResponse();
    try{
      this.authenticate(request.getUsername(), request.getPassword());
    }catch(Exception e){
      response.setToken(e.getMessage());
      return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.BAD_REQUEST);
    }
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
    String token = this.jwtTokenHelper.generateToken(userDetails);
    response.setToken(token);
    return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
  }

  private void authenticate(String username, String password) {
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
    this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
  }
}
