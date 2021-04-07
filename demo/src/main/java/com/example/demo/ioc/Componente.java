package com.example.demo.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Qualifier("estatico")
//@Scope("prototype")
public class Componente {
	private String nombre = "mundo";

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void saluda() {
		System.out.println("Hola " + nombre);
	}

	@Override
	public String toString() {
		return "Componente [nombre=" + nombre + "]";
	}
	
	
}
