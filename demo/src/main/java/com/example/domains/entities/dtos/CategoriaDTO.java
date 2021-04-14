package com.example.domains.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data @NoArgsConstructor @AllArgsConstructor
public class CategoriaDTO {
	int categoryId;
	String name;
}
