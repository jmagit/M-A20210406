package com.example.resources;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.websocket.server.PathParam;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.contracts.ActorService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import lombok.experimental.var;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/actores")
public class ActorResource {
	@Autowired
	private ActorService srv;
	
	@GetMapping
	public Page<ActorDTO> getAll(Pageable page) {
		return srv.getAll(ActorDTO.class, page);
	}

//	@GetMapping
//	public List<ActorDTO> getAll() {
//		return srv.getAll(ActorDTO.class);
//	}
//
	@GetMapping(path = "/{id}")
	public ActorDTO getOne(@PathVariable int id) throws NotFoundException {
		Optional<Actor> item = srv.getOne(id);
		if(item.isEmpty())
			throw new NotFoundException();
		return ActorDTO.from(item.get());
	}
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ActorDTO> create(@Valid @RequestBody ActorDTO item) throws BadRequestException, InvalidDataException {
		if(srv.getOne(item.getActorId()).isPresent())
			throw new BadRequestException("Clave duplicada");
		Actor newItem = srv.add(ActorDTO.from(item));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(newItem.getActorId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update(@PathVariable int id, @Valid @RequestBody ActorDTO item) throws BadRequestException, NotFoundException, InvalidDataException {
		if(item.getActorId() != id)
			throw new BadRequestException("Invalid identifier");
		if(srv.getOne(item.getActorId()).isEmpty())
			throw new NotFoundException();
		srv.modify(ActorDTO.from(item));		
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srv.delete(id);
	}

}
