package com.example.infraestructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.domains.entities.Actor;
import com.example.domains.entities.FilmActor;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.domains.entities.dtos.SoloNombre;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RepositoryRestResource(exported = false)
public interface ActorRepository extends JpaRepository<Actor, Integer> {
	List<SoloNombre> findByFirstNameStartingWith(String prefijo);
	List<Actor> getByFirstNameStartingWith(String prefijo, Sort orden);
	List<Actor> findByFirstNameStartingWithOrderByFirstNameDesc(String prefijo);
	
	@Query("from Actor a where a.actorId < ?1")
	List<ActorDTO> findActores(int min);
	@Query("select a.filmActors from Actor a where a.actorId = ?1")
	List<FilmActor> findPelis(int id);
	
	<T> List<T> findByActorIdIsNotNull(Class<T> type);
	<T> Page<T> findByActorIdIsNotNull(Class<T> type, Pageable page);
	<T> List<T> findByActorIdIsNotNull(Class<T> type, Sort sort);
}
