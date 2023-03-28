package com.bootcamp.creditcard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "creditCard")
public class CreditCard extends BaseAuditDto{

    @Id
    private String id_credit_card;
    private String id_product;
    private String id_customer;
    private String main_account; //numero de cuenta (14 digits)
    private List<String> additionalAccounts; //cuentas externas de otros bancos
    private Float availableCredit; //saldo disponible
    private Float amountConsumed; //monto consumido
    private List<String> bankMovements; //lista de movimientos bancarios

}
