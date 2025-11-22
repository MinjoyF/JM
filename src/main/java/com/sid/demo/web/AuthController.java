package com.sid.demo.web;

import com.sid.demo.entities.AppUser;
import com.sid.demo.repositories.AppUserRepository;
import com.sid.demo.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AppUserRepository repo;
  private final JwtService jwtService;
  private final PasswordEncoder encoder;

  public AuthController(AppUserRepository repo, JwtService jwtService, PasswordEncoder encoder) {
    this.repo = repo;
    this.jwtService = jwtService;
    this.encoder = encoder;
  }

  @PostMapping("/login")
  public String login(@RequestBody AppUser input) {

    AppUser user = repo.findByUsername(input.getUsername());
    if (user == null) return "User not found";

    if (!encoder.matches(input.getPassword(), user.getPassword()))
      return "Bad credentials";

    return jwtService.generateToken(user.getUsername());
  }
}



