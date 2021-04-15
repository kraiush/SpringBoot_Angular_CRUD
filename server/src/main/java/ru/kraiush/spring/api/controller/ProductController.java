package ru.kraiush.spring.api.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.kraiush.spring.api.model.ProductDto;

public interface ProductController {

	@GetMapping("/products")
	ResponseEntity<List<ProductDto>> findAll();

	@GetMapping("/products/{id}")
	ResponseEntity<ProductDto> getById(@PathVariable(value = "id") Long productId);

	@PostMapping("/products")
	ResponseEntity<Void> create(@Valid @RequestBody ProductDto productDto);

	@PutMapping("/products")
	ResponseEntity<Void> update(@Valid @RequestBody ProductDto productDto);

	@DeleteMapping("/products/{id}")
	ResponseEntity<Void> delete(@PathVariable(value = "id") Long productId);
}
