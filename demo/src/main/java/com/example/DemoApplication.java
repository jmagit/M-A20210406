package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.ioc.Componente;
import com.example.demo.ioc.Servicio;
import com.example.domains.entities.Actor;
import com.example.infraestructure.repositories.ActorRepository;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private Servicio srv;
	@Autowired
	@Qualifier("dinamico")
	private Componente cmp;
	
	@Value("${server.port}") 
	private String port;
	
	@Autowired
	private ActorRepository dao;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
////		System.out.println("Hola mundo");
////		cmp.setNombre("tu");
//		System.out.println(cmp.getNombre());
//		srv.getComponente().saluda();
//		System.out.println("Soy el " + port);
		
//		var actor = dao.findById(1);
//		if(actor.isPresent()) {
//			var item = actor.get();
//			item.setFirstName(item.getFirstName().toUpperCase());
//			dao.save(item);
//			System.out.println(actor.get());
//			
//		}
//		var item = new Actor(0, "Pepito", "Grillo");
//		dao.save(item);
//		dao.deleteById(235);
//		dao.findAll().stream().forEach(System.out::println);
//		dao.findByFirstNameStartingWith("p").stream().forEach(System.out::println);
		dao.findByFirstNameStartingWithOrderByFirstNameDesc("p").stream().forEach(System.out::println);
		dao.getByFirstNameStartingWith("p", Sort.by("firstName").descending()).stream().forEach(System.out::println);
//		dao.findActores(5).stream().forEach(System.out::println);
//		dao.findById(1).get().getFilmActors().forEach(peli -> System.out.println(peli.getFilm()));
		dao.findPelis(1).forEach(peli -> System.out.println(peli.getFilm()));
	}

}