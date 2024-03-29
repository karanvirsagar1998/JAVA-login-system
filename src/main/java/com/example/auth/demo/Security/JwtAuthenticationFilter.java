package com.example.auth.demo.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtTokenHelper jwttokenHelper;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  )
    throws ServletException, IOException {
    String requestToken = request.getHeader("Authorization");
    String username = null;
    String token = null;
    if (requestToken != null && requestToken.startsWith("Bearer")) {
      token = requestToken.substring(7);
      try {
        username = this.jwttokenHelper.getUsernameFromToken(token);
      } catch (IllegalArgumentException e) {
        System.out.println("Token did not get exception");
      } catch (ExpiredJwtException e) {
        System.out.println("Token expired exception");
      } catch (MalformedJwtException e) {
        System.out.println("Invalid JWT exception");
      }
    } else {
      System.out.println(
        "Jwt not strats with Bearer, else condition triggered"
      );
    }
    // validate token after getting
    if (
      username != null &&
      SecurityContextHolder.getContext().getAuthentication() == null
    ) {
      UserDetails userDetails =
        this.userDetailsService.loadUserByUsername(username);
      if (this.jwttokenHelper.validateToken(token, userDetails)) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
          userDetails,
          null,
          userDetails.getAuthorities()
        );
        usernamePasswordAuthenticationToken.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder
          .getContext()
          .setAuthentication(usernamePasswordAuthenticationToken);
      } else {
        System.out.println("Invalid jwt token, else part of validation !! ");
      }
    } else {
      System.out.println("USername is null or Context in not null !! ");
    }
    filterChain.doFilter(request, response);
  }
}
