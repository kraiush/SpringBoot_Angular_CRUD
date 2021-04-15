package ru.kraiush.spring.api.controller.impl;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ru.kraiush.spring.api.controller.ProductController;
import ru.kraiush.spring.api.model.ProductDto;
import ru.kraiush.spring.domain.service.impl.ProductServiceImpl;

//@CrossOrigin(origins = "http://localhost:4201")
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductServiceImpl service;

    @Override
    public ResponseEntity<List<ProductDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<ProductDto> getById(final Long productId) {
        return ResponseEntity.ok().body(service.findById(productId));
    }

    @Override
    public ResponseEntity<Void> create(final ProductDto productDto) {
        service.createOrUpdate(productDto);
        return new ResponseEntity<>(CREATED);
    }

    @Override
    public ResponseEntity<Void> update(final ProductDto productDto) {
        service.createOrUpdate(productDto);
        return new ResponseEntity<>(NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void>  delete(final Long productId) {
        service.delete(productId);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
