package com.sid.demo.web;

import com.sid.demo.repositories.CompteRepository;
import com.sid.demo.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banque")
public class CompteRestController {
    @Autowired
    private CompteRepository compteRepository;
    @GetMapping(path = "/comptes",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})

    public List<Compte> compteList(){
        return compteRepository.findAll();
    }

    @GetMapping("/comptes/{id}")
    public Compte getOne(@PathVariable(value = "id") Long id){
        return compteRepository.findById(id).get();
    }

    @PostMapping("/comptes")
    //@PreAuthorize("hasRole('ADMIN')")
    public Compte save (@RequestBody Compte compte){
        return compteRepository.save(compte);
    }

    @PutMapping("/comptes/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Compte updateCompte (@RequestBody Compte compte, @PathVariable("id") Long id){
        compte.setId(id);
        return compteRepository.save(compte);
    }

    @DeleteMapping("/comptes/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Long id){
        compteRepository.deleteById(id);
    }

}
