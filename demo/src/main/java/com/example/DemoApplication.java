package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.demo.ioc.Componente;
import com.example.demo.ioc.Servicio;
import com.example.domains.contracts.ActorService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.infraestructure.repositories.ActorRepository;
import com.example.resources.ActorResource;

import springfox.documentation.oas.annotations.EnableOpenApi;

import org.springframework.data.domain.Sort;

@EnableOpenApi
@EnableDiscoveryClient
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
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

	@Autowired
	ActorService servicio;
	
//	@Autowired
//	ActorResource servicio;
	
	
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
//		dao.findByFirstNameStartingWithOrderByFirstNameDesc("p").stream().forEach(System.out::println);
//		dao.getByFirstNameStartingWith("p", Sort.by("firstName").descending()).stream().forEach(System.out::println);
////		dao.findActores(5).stream().forEach(System.out::println);
////		dao.findById(1).get().getFilmActors().forEach(peli -> System.out.println(peli.getFilm()));
//		dao.findPelis(1).forEach(peli -> System.out.println(peli.getFilm()));
//		servicio.getAll().stream().map(item -> ActorDTO.from(item)).forEach(System.out::println);
		//dao.findActores(10).stream().map(item -> item.getFirstName()).forEach(System.out::println);
//		dao.findByFirstNameStartingWith("p").stream().map(item -> item.getNombre()).forEach(System.out::println);
//		var item = new Actor(0, "12345678z", " ");
//		servicio.add(item);
//		if(item.isInvalid()) {
//			item.getErrors().forEach(System.out::println);
//		}
//		item.setFirstName("Pedroooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
//		if(item.isInvalid()) {
//			item.getErrors().forEach(System.out::println);
//		}
//		item.setFirstName("Pedro");
//		if(item.isInvalid()) {
//			item.getErrors().forEach(System.out::println);
//		}
//		item.setLastName("G");
//		if(item.isInvalid()) {
//			item.getErrors().forEach(System.out::println);
//		}
//		item.setLastName("Gru");
//		if(item.isInvalid()) {
//			item.getErrors().forEach(System.out::println);
//		} else 
//			System.out.println("Valido");
	}

}
