package com.example.infraestructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domains.entities.Actor;
import com.example.domains.entities.FilmActor;

import org.springframework.data.domain.Sort;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
	List<Actor> findByFirstNameStartingWith(String prefijo);
	List<Actor> getByFirstNameStartingWith(String prefijo, Sort orden);
	List<Actor> findByFirstNameStartingWithOrderByFirstNameDesc(String prefijo);
	
	@Query("from Actor a where a.actorId < ?1")
	List<Actor> findActores(int min);
	@Query("select a.filmActors from Actor a where a.actorId = ?1")
	List<FilmActor> findPelis(int id);
}
