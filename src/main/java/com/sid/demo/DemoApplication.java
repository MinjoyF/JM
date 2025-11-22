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
      AppUser admin = new AppUser();
      admin.setUsername("admin");
      admin.setPassword(encoder.encode("1234"));
      admin.setRoles(Set.of("ADMIN"));
      repo.save(admin);

      AppUser user = new AppUser();
      user.setUsername("user");
      user.setPassword(encoder.encode("4567"));
      user.setRoles(Set.of("USER"));
      repo.save(user);
    };
  }

  @Bean
  CommandLineRunner start(
    CompteRepository compteRepository,
    RepositoryRestConfiguration restConfiguration,
    ClientRepository clientRepository
  ) {
    return args -> {
      // IDs für REST exposed machen
      restConfiguration.exposeIdsFor(Compte.class);

      // Clients erstellen
      Client c1 = clientRepository.save(new Client("Ghislain", null));
      Client c2 = clientRepository.save(new Client("Nicaise", null));
      Client c3 = clientRepository.save(new Client("Ornella", null));

      // Comptes erstellen
      Compte cp1 = new Compte(Math.random() * 90000, new Date(), TypeCompte.EPARGNE, c1);
      Compte cp2 = new Compte(Math.random() * 90000, new Date(), TypeCompte.COURANT, c2);
      Compte cp3 = new Compte(Math.random() * 90000, new Date(), TypeCompte.EPARGNE, c3);

      compteRepository.save(cp1);
      compteRepository.save(cp2);
      compteRepository.save(cp3);

      // Alle Comptes ausgeben
      compteRepository.findAll().forEach(c ->
        System.out.println("Compte ID: " + c.getId() + ", Solde: " + c.getSolde() + ", Client: " + c.getClient().getName())
      );
    };
  }


}
