package com.microservices.cards.Service;

import com.microservices.cards.DTO.CardsDTO;

public interface ICardsService {
    void createCards(String mobileNumber);

    CardsDTO fetchCards(String mobileNumber);

    boolean updateCard(CardsDTO cardsDto);

    boolean deleteCard(String mobileNumber);
}
