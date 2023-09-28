package com.smartcode.ecommerce.controllers;


import com.smartcode.ecommerce.model.product.ProductEntity;
import com.smartcode.ecommerce.model.product.dto.ProductCreateRequest;
import com.smartcode.ecommerce.model.product.dto.ProductDto;
import com.smartcode.ecommerce.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductDto>> get() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductCreateRequest productCreateRequest) {
        return ResponseEntity.ok(productService.create(productCreateRequest));
    }

    @DeleteMapping
    public void delete(@RequestParam Integer id) {
        productService.delete(id);
    }

    @PutMapping
    public ResponseEntity<ProductDto> update(@RequestBody ProductEntity productEntity) {
        return ResponseEntity.ok(productService.update(productEntity));
    }









}
