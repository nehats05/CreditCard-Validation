package com.creditCard.dao;

import com.creditCard.CreditCardBean;

public interface CreditCardDao {
    CreditCardBean validateCard(String cardnum,String month,String year);
}
