package com.boakdev.chapteronejob.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boakdev.chapteronejob.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
