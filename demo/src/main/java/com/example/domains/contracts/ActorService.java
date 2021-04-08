package com.example.domains.contracts;

import java.util.List;
import java.util.Optional;

import com.example.domains.entities.Actor;

public interface ActorService {

	List<Actor> getAll();

	Optional<Actor> getOne(int id);

	Actor add(Actor item);

	Actor modify(Actor item);

	void delete(Actor item);

	void delete(int id);

}