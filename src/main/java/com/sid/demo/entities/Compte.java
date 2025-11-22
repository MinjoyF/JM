package com.sid.demo.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Compte {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private double solde;

  @Temporal(TemporalType.DATE)
  private Date dateCreation;

  @Enumerated(EnumType.STRING)
  @Column(length = 10)
  private TypeCompte type;

  @ManyToOne
  private Client client;

  // ------------------------
  // Constructors
  // ------------------------
  public Compte() {
  }

  public Compte(double solde, Date dateCreation, TypeCompte type, Client client) {
    this.solde = solde;
    this.dateCreation = dateCreation;
    this.type = type;
    this.client = client;
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

  public double getSolde() {
    return solde;
  }

  public void setSolde(double solde) {
    this.solde = solde;
  }

  public Date getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(Date dateCreation) {
    this.dateCreation = dateCreation;
  }

  public TypeCompte getType() {
    return type;
  }

  public void setType(TypeCompte type) {
    this.type = type;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }
}
