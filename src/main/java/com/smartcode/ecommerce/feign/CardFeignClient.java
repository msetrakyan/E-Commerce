package com.smartcode.ecommerce.feign;

import com.smartcode.ecommerce.configuration.CardFeignConfig;
import com.smartcode.ecommerce.model.card.CardRequestDto;
import com.smartcode.ecommerce.model.card.CardResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@FeignClient(value = "cardService", url = "http://localhost:8081/cards", configuration = CardFeignConfig.class)
public interface CardFeignClient {


    @PostMapping("/card")
    ResponseEntity<CardResponseDto> createCard(@RequestBody @Valid CardRequestDto cardRequestDto);

    @GetMapping("/{ownerId}")
    ResponseEntity<List<CardResponseDto>> findByOwnerId(@PathVariable @Positive Integer ownerId);

    @DeleteMapping("/{ownerId}")
    ResponseEntity<Void> deleteAllByOwnerId(@PathVariable @Positive Integer ownerId);

    @DeleteMapping("/{cardId}")
    ResponseEntity<Void> deleteByCardId(@PathVariable @Positive Integer cardId);

}
