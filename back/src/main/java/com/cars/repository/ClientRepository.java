package com.cars.repository;

import com.cars.model.Car;
import com.cars.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
    Optional<Client> findByEmail(String email);

    List<Client> findAll();
}
