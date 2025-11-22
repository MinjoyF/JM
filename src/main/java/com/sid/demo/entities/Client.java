package com.sid.demo.entities;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.ArrayList;

@Entity
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
  private Collection<Compte> comptes = new ArrayList<>();

  // ------------------------
  // Constructors
  // ------------------------
  public Client() {
  }

  public Client(String name, Collection<Compte> comptes) {
    this.name = name;
    this.comptes = comptes;
  }

  // ------------------------
  // Getters & Setters
  // ------------------------
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Collection<Compte> getComptes() {
    return comptes;
  }

  public void setComptes(Collection<Compte> comptes) {
    this.comptes = comptes;
  }
}

