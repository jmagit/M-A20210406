package com.example.demo.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurador {
	@Bean
	@Qualifier("dinamico")
	public Componente getMiComponente() {
		var cmp = new Componente();
		cmp.setNombre("Otro Nombre");
		return cmp;
	}
}
