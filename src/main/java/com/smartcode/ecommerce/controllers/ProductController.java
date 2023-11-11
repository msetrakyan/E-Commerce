package com.smartcode.ecommerce.controllers;


import com.smartcode.ecommerce.model.product.ProductEntity;
import com.smartcode.ecommerce.model.product.dto.ProductCreateRequest;
import com.smartcode.ecommerce.model.product.dto.ProductDto;
import com.smartcode.ecommerce.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping
    @RolesAllowed({"USER", "ADMIN"})
    public ResponseEntity<List<ProductDto>> get() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping(path = "/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    public ResponseEntity<ProductDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    @RolesAllowed({"USER", "ADMIN"})
    public ResponseEntity<ProductDto> create(@RequestBody ProductCreateRequest productCreateRequest) {
        return ResponseEntity.ok(productService.create(productCreateRequest));
    }

    @DeleteMapping
    @RolesAllowed({"USER", "ADMIN"})
    public void delete(@RequestParam Integer id) {
        productService.delete(id);
    }

    @PutMapping
    @RolesAllowed({"USER", "ADMIN"})
    public ResponseEntity<ProductDto> update(@RequestBody ProductEntity productEntity) {
        return ResponseEntity.ok(productService.update(productEntity));
    }

    @GetMapping(path = "/buy/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    public void buy(@PathVariable Integer id) {
        productService.buy(id);
    }








}
