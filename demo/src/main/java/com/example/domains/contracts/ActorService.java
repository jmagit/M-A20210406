package com.example.domains.contracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.domains.entities.Actor;
import com.example.exceptions.InvalidDataException;

public interface ActorService {

	List<Actor> getAll();

	<T> List<T> getAll(Class<T> type);

	<T> Page<T> getAll(Class<T> type, Pageable page);

	<T> List<T> getAll(Class<T> type, Sort sort);

	Optional<Actor> getOne(int id);

	Actor add(Actor item) throws InvalidDataException;

	Actor modify(Actor item) throws InvalidDataException;

	void delete(Actor item) throws InvalidDataException;

	void delete(int id);

}