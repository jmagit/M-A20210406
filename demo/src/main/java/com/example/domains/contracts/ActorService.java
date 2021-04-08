package com.example.domains.contracts;

import java.util.List;
import java.util.Optional;

import com.example.domains.entities.Actor;
import com.example.exceptions.InvalidDataException;

public interface ActorService {

	List<Actor> getAll();

	Optional<Actor> getOne(int id);

	Actor add(Actor item) throws InvalidDataException;

	Actor modify(Actor item) throws InvalidDataException;

	void delete(Actor item) throws InvalidDataException;

	void delete(int id);

}