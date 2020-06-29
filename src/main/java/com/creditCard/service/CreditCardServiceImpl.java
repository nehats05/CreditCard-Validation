package com.creditCard.service;

import com.creditCard.dao.CreditCardDao;
import com.creditCard.CreditCardBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {

@Autowired
 private CreditCardDao cardDao;

    @Override
    @Transactional(readOnly = true)
    public CreditCardBean validateCard(String cardnum, String month, String year) {
        return cardDao.validateCard(cardnum,month,year);
    }
}
