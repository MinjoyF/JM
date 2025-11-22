package com.sid.demo;

import com.sid.demo.entities.AppUser;
import com.sid.demo.repositories.AppUserRepository;
import com.sid.demo.repositories.ClientRepository;
import com.sid.demo.repositories.CompteRepository;
import com.sid.demo.entities.Client;
import com.sid.demo.entities.Compte;
import com.sid.demo.entities.TypeCompte;
import lombok.Builder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Set;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  CommandLineRunner initUsers(AppUserRepository repo, PasswordEncoder encoder) {
    return args -> {
      AppUser u = new AppUser();
      u.setUsername("admin");
      u.setPassword(encoder.encode("1234"));
      u.setRoles(Set.of("ROLE_ADMIN"));
      repo.save(u);
    };
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
