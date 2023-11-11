package com.smartcode.ecommerce.service.card;


import com.smartcode.ecommerce.model.card.CardRequestDto;
import com.smartcode.ecommerce.model.card.CardResponseDto;

import java.util.List;

public interface CardService {

    CardResponseDto create(CardRequestDto cardRequestDto);

    void deleteAllByOwnerId(Integer ownerId);

    void deleteByCardId(Integer cardId);

    List<CardResponseDto> findByOwnerId(Integer userId);
}
