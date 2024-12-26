package com.challenge2.api.controllers;

import com.challenge2.api.models.payload.requests.ProductRequest;
import com.challenge2.api.models.payload.responses.ProductResponse;
import com.challenge2.api.services.abstracts.ProductServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServices productServices;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return new ResponseEntity<>(productServices.products(),
                HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<?> save(@Valid @RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productServices.save(productRequest),
                HttpStatus.CREATED);
    }

    @PutMapping("/put")
    public ResponseEntity<?> update(@Valid @RequestBody ProductRequest productRequest,
                                    @RequestParam("id") long id) {
        return new ResponseEntity<>(productServices.update(productRequest, id),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") long id) {
        return new ResponseEntity<>(productServices.delete(id), HttpStatus.OK);
    }
}
