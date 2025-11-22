package com.sid.demo.repositories;

import com.sid.demo.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client,Long> {

}
