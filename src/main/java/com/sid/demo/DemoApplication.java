package com.sid.demo;

import com.sid.demo.Repositories.ClientRepository;
import com.sid.demo.Repositories.CompteRepository;
import com.sid.demo.entities.Client;
import com.sid.demo.entities.Compte;
import com.sid.demo.entities.TypeCompte;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
 	CommandLineRunner start(CompteRepository compteRepository,
							RepositoryRestConfiguration restConfiguration,
							ClientRepository clientRepository
	){
		return args -> {
			restConfiguration.exposeIdsFor(Compte.class);
			Client c1 =clientRepository.save(new Client(null, "Ghislain",null));
			Client c2 =clientRepository.save(new Client(null, "Nicaise",null));
			Client c3 =clientRepository.save(new Client(null, "Ornella",null));


			compteRepository.save(new Compte(null, Math.random()*90000,new Date(), TypeCompte.EPARGNE,c1));
			compteRepository.save(new Compte(null, Math.random()*90000,new Date(), TypeCompte.COURANT,c2));
			compteRepository.save(new Compte(null, Math.random()*90000,new Date(), TypeCompte.EPARGNE,c3));
			compteRepository.findAll().forEach(c ->{
				System.out.println(c.getSolde());
			} );
		};
	}
}
