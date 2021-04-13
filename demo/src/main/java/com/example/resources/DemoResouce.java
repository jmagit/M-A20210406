package com.example.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.domains.entities.dtos.ActorCatalogoDTO;
import com.example.exceptions.NotFoundException;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@RestController
public class DemoResouce {
	@Autowired
	RestTemplate srvRest;

	@Value
	static class Respuesta {
		private String tipo, mensaje;
	}
	
	@GetMapping
	public String raiz() {
		return "Raiz del sitio";
	}

	@GetMapping("/demos/params/{id}")
	public String cotilla(
	        @PathVariable String id,
	        @RequestParam String nom,
	        @RequestHeader("Accept-Language") String language, 
	        @CookieValue(name = "XSRF-TOKEN", required = false, defaultValue = "Sin valor en la cookie") String cookie) { 
	    StringBuilder sb = new StringBuilder();
	    sb.append("id: " + id + "\n");
	    sb.append("nom: " + nom + "\n");
	    sb.append("language: " + language + "\n");
	    sb.append("cookie: " + cookie + "\n");
	    return sb.toString();
	}

	@GetMapping(path = "/demos/saluda") 
	public Respuesta saluda(@RequestParam(required = false, defaultValue = "mundo") String nombre) {
		return new Respuesta("Sin tipo", "Hola " + nombre);
	}
	@GetMapping(path = "/demos/saluda", produces = { MediaType.TEXT_PLAIN_VALUE }) 
	public String saludaComoTexto(@RequestParam(required = false, defaultValue = "mundo") String nombre) {
		return (new Respuesta("Sin tipo", "Hola " + nombre)).toString();
	}
	@GetMapping(path = "/demos/saluda", produces = { MediaType.TEXT_HTML_VALUE}) 
	public String saludaComoHtml(@RequestParam(required = false, defaultValue = "mundo") String nombre) {
		String cad = "<html><head><title>Demo</title></head><body><p>" +
					"<b>Tipo</b> " + nombre + 
				"</body></html>";
		return cad;
	}
	
	@PostMapping(path = "/demos/saluda") 
	public String saludaPost(@RequestBody Respuesta item) throws NotFoundException {
		if(item.getTipo().compareToIgnoreCase("error") == 0)
			throw new NotFoundException("Error forzado");
		return item.toString();
	}

	@GetMapping(path = "/demos/pagina") 
	public Pageable pagina(Pageable pag) {
		return pag;
	}
	@GetMapping(path = "/pasarela") 
	public String pasarela() {
		return srvRest.getForObject("http://localhost:8010/", String.class);
	}
	@GetMapping(path = "/pasarela/actores") 
	public Page<ActorCatalogoDTO> pasarelaActores() {
		ResponseEntity<Page<ActorCatalogoDTO>> response = srvRest.exchange("http://localhost:8010/actores", HttpMethod.GET,
				HttpEntity.EMPTY, new ParameterizedTypeReference<Page<ActorCatalogoDTO>>() {
				});
		return response.getBody();
	}

}
