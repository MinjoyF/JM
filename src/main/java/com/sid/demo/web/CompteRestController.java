package com.sid.demo.web;

import com.sid.demo.repositories.CompteRepository;
import com.sid.demo.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/banque")
public class CompteRestController {

  @Autowired
  private CompteRepository compteRepository;

  // -----------------------
  // LIST ALL COMPTES
  // -----------------------
  @GetMapping(path = "/comptes", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  public ResponseEntity<List<Compte>> compteList() {
    List<Compte> comptes = compteRepository.findAll();
    return ResponseEntity.ok(comptes);
  }

  // -----------------------
  // GET ONE COMPTE
  // -----------------------
  @GetMapping("/comptes/{id}")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  public ResponseEntity<Compte> getOne(@PathVariable Long id) {
    Compte compte = compteRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compte not found"));
    return ResponseEntity.ok(compte);
  }

  // -----------------------
  // CREATE COMPTE
  // -----------------------
  @PostMapping("/comptes")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Compte> save(@Valid @RequestBody Compte compte) {
    Compte saved = compteRepository.save(compte);
    return ResponseEntity.status(HttpStatus.CREATED).body(saved);
  }

  // -----------------------
  // UPDATE COMPTE
  // -----------------------
  @PutMapping("/comptes/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Compte> updateCompte(@PathVariable Long id, @Valid @RequestBody Compte compteRequest) {
    Compte existing = compteRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compte not found"));

    existing.setClient(compteRequest.getClient());

    Compte updated = compteRepository.save(existing);
    return ResponseEntity.ok(updated);
  }

  // -----------------------
  // DELETE COMPTE
  // -----------------------
  @DeleteMapping("/comptes/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!compteRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compte not found");
    }
    compteRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}

