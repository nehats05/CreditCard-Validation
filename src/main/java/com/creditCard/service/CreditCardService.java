package com.creditCard.service;

import com.creditCard.CreditCardBean;

public interface CreditCardService {
    CreditCardBean validateCard(String cardnum,String month, String year);
}
