package com.creditCard.controller;

import com.creditCard.CreditCardBean;
import com.creditCard.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public String validateCreditCard(@RequestParam("cardnumber") String cardnum,@RequestParam("expirydate") String month,
                                     @RequestParam("year") String year)
    {
        CreditCardBean cardBean = creditCardService.validateCard(cardnum,month,year);
        if(cardBean.getCardType()!=null && cardBean.getCardNum()!=null)
        return cardBean.toString();
        else
            return "Enter the accurate Card details(Only Visa and Master Card accepted)";
    }

}
