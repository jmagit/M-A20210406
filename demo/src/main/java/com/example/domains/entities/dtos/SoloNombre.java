package com.example.domains.entities.dtos;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

public interface SoloNombre {
	String getFirstName();
	String getLastName();
	
	@Value("#{target.lastName + ', ' + target.firstName}")
	String getNombre();
//	default String getNombre() {
//		return getLastName()  + ", " + getFirstName();
//	}
}
