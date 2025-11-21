package com.sid.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
@XmlRootElement
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Compte {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double solde;
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private TypeCompte type;
    @ManyToOne
    private Client client;

  public Compte(Object o, double solde, Date dateCreation, TypeCompte type, Client c1) {
    this.solde = solde;
    this.dateCreation = dateCreation;
    this.type = type;
    this.client = c1;
  }
  public Compte() {
  }


  public double getSolde() {
    return solde;
  }

  public void setId(Long id) {
  }
}
