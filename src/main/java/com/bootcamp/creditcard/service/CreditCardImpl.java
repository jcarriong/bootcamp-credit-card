package com.bootcamp.creditcard.service;

import com.bootcamp.creditcard.model.CreditCard;
import com.bootcamp.creditcard.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

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

    @Override
    public Mono<CreditCard> updateCreditCard(CreditCard creditCard, String idCreditCard) {

        return creditCardRepository.findById(idCreditCard)
                .flatMap(currentCreditCard -> {
                    currentCreditCard.setMainAccount(creditCard.getMainAccount());
                    currentCreditCard.setAdditionalAccounts(creditCard.getAdditionalAccounts());
                    currentCreditCard.setAvailableCredit(creditCard.getAvailableCredit());
                    currentCreditCard.setAmountConsumed(creditCard.getAmountConsumed());
                    currentCreditCard.setUpdateDatetime(LocalDateTime.now());
                    return creditCardRepository.save(currentCreditCard);
                    //Edición del resto de campos deshabilitada por relación e identidad
                });
    }

    @Override
    public Mono<CreditCard> deleteCreditCard(String idCreditCard) {
        return creditCardRepository.findById(idCreditCard)
                .flatMap(existingCreditCard -> creditCardRepository.delete(existingCreditCard)
                        .then(Mono.just(existingCreditCard)));
    }
}
