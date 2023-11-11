package com.smartcode.ecommerce.service.card.impl;

import com.smartcode.ecommerce.exception.ValidationException;
import com.smartcode.ecommerce.feign.CardFeignClient;
import com.smartcode.ecommerce.model.action.ActionType;
import com.smartcode.ecommerce.model.card.CardRequestDto;
import com.smartcode.ecommerce.model.card.CardResponseDto;
import com.smartcode.ecommerce.service.action.ActionService;
import com.smartcode.ecommerce.service.card.CardService;
import com.smartcode.ecommerce.util.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardFeignClient cardFeignClient;
    private final ActionService actionService;

    @Override
    @Transactional
    public void deleteByCardId(Integer cardId) {
        /*RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(String.format("http://localhost:8081/cards/delete/%d",cardId));*/
        cardFeignClient.deleteByCardId(cardId);
        actionService.createAction(ActionType.DELETE, "Card", CurrentUser.getId());
    }

    @Override
    @Transactional
    public void deleteAllByOwnerId(Integer userId) {
        /*RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(String.format("http://localhost:8081/cards/delete/owner/%d",userId));*/
        cardFeignClient.deleteAllByOwnerId(userId);
        actionService.createAction(ActionType.DELETE, "Card", CurrentUser.getId());
    }


    @Override
    @Transactional
    public CardResponseDto create(CardRequestDto cardRequestDto) {
        String number = cardRequestDto.getNumber();
        cardRequestDto.setOwnerId(CurrentUser.getId());
        int length = number.replace(" ", "").toCharArray().length;
        if (length != 16) {
            throw new ValidationException("Card not found");
        }
        /*RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(
                "http://localhost:8081/cards/create",
                cardRequestDto,
                CardResponseDto.class).getBody();*/

        actionService.createAction(ActionType.CREATE, "Card", CurrentUser.getId());
        return cardFeignClient.createCard(cardRequestDto).getBody();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CardResponseDto> findByOwnerId(Integer userId) {

        /*RestTemplate restTemplate = new RestTemplate();
        return List.of(Objects.requireNonNull(restTemplate.getForEntity(
                String.format("http://localhost:8081/cards/find/%d", userId),
                CardResponseDto[].class).getBody()));*/

        return cardFeignClient.findByOwnerId(userId).getBody();
    }
}
