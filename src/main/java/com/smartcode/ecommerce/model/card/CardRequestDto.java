package com.smartcode.ecommerce.model.card;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CardRequestDto {
        private String cardName;
        private String ownerName;
        private String number;
        private String expDate;
        private Integer ownerId;
}
