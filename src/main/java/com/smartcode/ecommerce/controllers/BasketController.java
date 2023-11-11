package com.smartcode.ecommerce.controllers;

import com.smartcode.ecommerce.model.basket.BasketItemResponseDto;
import com.smartcode.ecommerce.service.basket.BasketItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/basket")
public class BasketController {

    private final BasketItemService basketItemService;

    @PostMapping("/{productId}")
    @PreAuthorize("hasRole('" + "USER_ROLE" + "')")
    public ResponseEntity<Void> addToBasket(@PathVariable @Positive Integer productId) {
        basketItemService.addOrUpdate(productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('" +"USER_ROLE" + "')")
    public ResponseEntity<List<BasketItemResponseDto>> getBasket() {
        List<BasketItemResponseDto> basket = basketItemService.getBasket();
        return ResponseEntity.ok(basket);
    }

    @DeleteMapping("/{basketItemId}")
    @PreAuthorize("hasRole('" + "USER_ROLE" + "')")
    public ResponseEntity<Void> deleteFromBasket(@PathVariable @Positive Integer basketItemId) {
        basketItemService.deleteFromBasket(basketItemId);
        return ResponseEntity.ok().build();
    }

}
