package com.example.demo.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {

    ClientEntity findById(Integer id);

    ClientEntity findByEmail(String email);

}
