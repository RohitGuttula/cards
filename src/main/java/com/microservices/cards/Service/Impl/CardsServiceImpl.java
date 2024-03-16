package com.microservices.cards.Service.Impl;

import com.microservices.cards.Constants.CardsConstants;
import com.microservices.cards.DTO.CardsDTO;
import com.microservices.cards.Entity.Cards;
import com.microservices.cards.Exception.CardsAlreadyExistsException;
import com.microservices.cards.Exception.ResourceNoFoundException;
import com.microservices.cards.Mapper.CardsMapper;
import com.microservices.cards.Repository.CardsRepository;
import com.microservices.cards.Service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {
    private CardsRepository cardsRepository;
    @Override
    public void createCards(String mobileNumber) {
         Optional<Cards> cards=cardsRepository.findByMobileNumber(mobileNumber);
         if(cards.isPresent()){
             throw new CardsAlreadyExistsException("Cards already exits for given mobile number",mobileNumber);
         }
         else{
             Cards cardsEntity=createNewCards(mobileNumber);
             cardsRepository.save(cardsEntity);
         }
    }

    @Override
    public CardsDTO fetchCards(String mobileNumber) {
        Cards cards=cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNoFoundException("Cards","MobileNumber",mobileNumber)
        );
        return CardsMapper.mapToCardsDto(cards,new CardsDTO());
    }

    @Override
    public boolean updateCard(CardsDTO cardsDto) {
        Cards cards=cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                ()-> new ResourceNoFoundException("CARDS", "Card Number",cardsDto.getCardNumber())
        );
        CardsMapper.mapToCards(cardsDto,cards);
        cardsRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards=cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNoFoundException("CARDS","Mobile Number",mobileNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }

    private Cards createNewCards(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }
}
