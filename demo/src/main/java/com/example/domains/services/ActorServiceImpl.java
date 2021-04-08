package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.ActorService;
import com.example.domains.entities.Actor;
import com.example.infraestructure.repositories.ActorRepository;

@Service
public class ActorServiceImpl implements ActorService {
	@Autowired
	private ActorRepository dao;
	
	@Override
	public List<Actor> getAll() {
		return dao.findAll();
	}
	@Override
	public Optional<Actor> getOne(int id) {
		return dao.findById(id);
	}
	@Override
	public Actor add(Actor item) {
		item = dao.save(item);
		return item;
	}
	@Override
	public Actor modify(Actor item) {
		item = dao.save(item);
		return item;
	}
	@Override
	public void delete(Actor item) {
		delete(item.getActorId());
	}
	@Override
	public void delete(int id) {
		dao.deleteById(id);
	}
	
	// ...
}
