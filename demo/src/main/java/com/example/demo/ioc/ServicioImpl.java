package com.example.demo.ioc;

import org.springframework.stereotype.Service;

@Service
public class ServicioImpl implements Servicio {
	private Componente componente;

	public ServicioImpl(Componente componente) {
		this.componente = componente;
	}

	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}
	
	
}
