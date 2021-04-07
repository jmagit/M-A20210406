package com.example.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domains.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

}
