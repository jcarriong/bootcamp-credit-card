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

    /**
     * Consultar todas las tarjetas de crédito existentes
     **/
    @GetMapping("/findAll")
    public Flux<CreditCard> findAll() {
        log.info("All credit card were consulted");
        return creditCardService.findAll()
                .doOnNext(creditCard -> creditCard.toString());
    }
    /**
     * Consultar tarjeta de crédito por id
     **/
    @GetMapping("/findById/{id}")
    public Mono<ResponseEntity<CreditCard>> findById(@PathVariable("id") String id) {
        log.info("Credit card consulted by id " + id);
        return creditCardService.findById(id)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.error(() -> new RuntimeException("Datos de la tarjeta de crédito no encontrada")));
    }
    /**
     * Registrar datos de tarjeta de crédito
     **/
    @PostMapping("/saveCreditCard")
    public Mono<ResponseEntity<CreditCard>> save(@RequestBody CreditCard creditCard) {
        log.info("A credit card was created");
        creditCard.setCreationDatetime(LocalDateTime.now());
        return creditCardService.save(creditCard)
                .map(bc -> new ResponseEntity<>(bc, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.CONFLICT));
    }
    /**
     * Editar datos de tarjeta de crédito(se restringe la edición de llaves compuestas)
     **/
    @PutMapping("/updateCreditCard/{idCreditCard}")
    public Mono<ResponseEntity<CreditCard>> update(@RequestBody CreditCard creditCard,
                                                   @PathVariable("idCreditCard") String idCreditCard) {
        log.info("A credit card was changed");
        return creditCardService.updateCreditCard(creditCard, idCreditCard)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
    /**
     * Eliminar una tarjeta de crédito existente
     **/
    @DeleteMapping("/deleteCreditCard/{idCreditCard}")
    public Mono<ResponseEntity<Void>> deleteCreditCard(@PathVariable(name = "idCreditCard") String idCreditCard) {
        log.info("A credit card was deleted");
        return creditCardService.deleteCreditCard(idCreditCard)
                .map(bankCustomer -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }
}
