package com.bootcamp.creditcard.service;

import com.bootcamp.creditcard.model.CreditCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditCardService {
    Flux<CreditCard> findAll();

    Mono<CreditCard> findById(String id);

    Mono<CreditCard> save(CreditCard creditCard);

    Mono<CreditCard> updateCreditCard(CreditCard creditCard, String idCreditCard);

    Mono<CreditCard> deleteCreditCard(String idCreditCard);
}
