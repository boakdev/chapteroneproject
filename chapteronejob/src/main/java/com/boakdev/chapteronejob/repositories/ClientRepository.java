package com.boakdev.chapteronejob.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boakdev.chapteronejob.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
