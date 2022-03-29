package com.security.jwtlearning.services;

import com.security.jwtlearning.domen.Card;
import com.security.jwtlearning.repository.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    public Card findByCardId(Long cardId){
        return cardRepository.findByCardId(cardId);
    }

    public void save(Card currentCard) {
        cardRepository.save(currentCard);
    }

}
