package com.sid.demo.entities;

import com.sid.demo.entities.Compte;
import org.springframework.data.rest.core.config.Projection;

@Projection(name ="solde",types = Compte.class)
public interface CompteProjection1 {
    double getSolde();
}


