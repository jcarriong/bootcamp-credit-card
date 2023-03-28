package com.bootcamp.creditcard.service;

import com.bootcamp.creditcard.model.CreditCard;
import com.bootcamp.creditcard.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditCardImpl implements CreditCardService {
    @Autowired
    CreditCardRepository creditCardRepository;

    @Override
    public Flux<CreditCard> findAll() {
        return creditCardRepository.findAll();
    }

    @Override
    public Mono<CreditCard> findById(String id) {
        return creditCardRepository.findById(id);
    }

    @Override
    public Mono<CreditCard> save(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }
}
