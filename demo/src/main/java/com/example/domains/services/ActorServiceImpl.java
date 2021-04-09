package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.ActorService;
import com.example.domains.entities.Actor;
import com.example.exceptions.InvalidDataException;
import com.example.infraestructure.repositories.ActorRepository;

@Service
public class ActorServiceImpl implements ActorService {
	@Autowired
	private ActorRepository dao;
	
	@Override
	public List<Actor> getAll() {
		return dao.findAll();
	}
	public <T> List<T> getAll(Class<T> type) {
		return dao.findByActorIdIsNotNull(type);
	}

	public <T> Page<T> getAll(Class<T> type, Pageable page) {
		return dao.findByActorIdIsNotNull(type, page);
	}

	public <T> List<T> getAll(Class<T> type, Sort sort) {
		return dao.findByActorIdIsNotNull(type, sort);
	}

	@Override
	public Optional<Actor> getOne(int id) {
		return dao.findById(id);
	}
	@Override
	public Actor add(@Valid Actor item) throws InvalidDataException {
		if(item == null)
			throw new InvalidDataException("Faltan los datos");
		if(item.isInvalid())	
			throw new InvalidDataException("Errores de validación");
		item = dao.save(item);
		return item;
	}
	@Override
	public Actor modify(Actor item) throws InvalidDataException {
		if(item == null)
			throw new InvalidDataException("Faltan los datos");
		if(item.isInvalid())	
			throw new InvalidDataException("Errores de validación");
		item = dao.save(item);
		return item;
	}
	@Override
	public void delete(Actor item) throws InvalidDataException {
		if(item == null)
			throw new InvalidDataException("Faltan los datos");
		delete(item.getActorId());
	}
	@Override
	public void delete(int id) {
		dao.deleteById(id);
	}
	
	// ...
}
