package com.example.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domains.entities.dtos.CategoriaDTO;

@FeignClient(name = "catalogo-service") 
public interface CatalogoProxy {
	@GetMapping(path = "/")
	String dameEnlaces();
	
	@GetMapping(path = "/categorias")
	List<CategoriaDTO> dameTodasLasCategorias();
	@GetMapping(path = "/categorias/{id}")
	CategoriaDTO dameUnaCategoria(@PathVariable int id);
	@PostMapping(path = "/categorias")
	CategoriaDTO addCategoria(CategoriaDTO item);
	
}
