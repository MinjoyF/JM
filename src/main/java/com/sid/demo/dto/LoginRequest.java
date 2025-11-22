package com.sid.demo.dto;

public record LoginRequest(String username, String password) {
  public Object getUsername() {
    return username;
  }

  public Object getPassword() {
    return password;
  }
}
