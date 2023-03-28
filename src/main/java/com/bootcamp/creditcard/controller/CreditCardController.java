package com.bootcamp.creditcard.controller;

import com.bootcamp.creditcard.model.CreditCard;
import com.bootcamp.creditcard.service.CreditCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1")
@Slf4j
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/findAll")
    public Flux<CreditCard> findAll() {
        log.info("All credit card were consulted");
        return creditCardService.findAll()
                .doOnNext(creditCard -> creditCard.toString());
    }

    @GetMapping("/findById/{id}")
    public Mono<CreditCard> findById(@PathVariable("id") String id) {
        log.info("Credit card consulted by id " + id);
        return creditCardService.findById(id);

    }

    @PostMapping("/save")
    public Mono<ResponseEntity<CreditCard>> save(@RequestBody CreditCard creditCard) {
        log.info("A credit card was created");
        creditCard.setCreationDatetime(LocalDateTime.now());
        return creditCardService.save(creditCard)
                .map(bc -> new ResponseEntity<>(bc, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.CONFLICT));
    }
}
