package com.sid.demo.web;

import com.sid.demo.Repositories.CompteRepository;
import com.sid.demo.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public Compte save (@RequestBody Compte compte){
        return compteRepository.save(compte);
    }

    @PutMapping("/comptes/{id}")
    public Compte updateCompte (@RequestBody Compte compte, @PathVariable("id") Long id){
        compte.setId(id);
        return compteRepository.save(compte);
    }

    @DeleteMapping("/comptes/{id}")
    public void delete(@PathVariable("id") Long id){
        compteRepository.deleteById(id);
    }

}
