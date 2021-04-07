package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.ioc.Componente;
import com.example.demo.ioc.Servicio;

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

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("Hola mundo");
//		cmp.setNombre("tu");
		System.out.println(cmp.getNombre());
		srv.getComponente().saluda();
		System.out.println("Soy el " + port);
	}

}
