package com.creditCard.dao;

import com.creditCard.CreditCardBean;
import org.springframework.stereotype.Component;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;


@Component
public class CreditCardDaoImpl implements CreditCardDao {

    private CreditCardBean cardBean = new CreditCardBean();
    private String regExMasterCard = "^[5][1-5]";
    private String regExVisaCard = "^[4]";
    private static final String[] blacklist = new String[]{"4788384538552446", "5144385438523845"};

    @Override
    public CreditCardBean validateCard(String cardnum, String month, String year) {
        String card =cardnum.replaceAll("\\s","");
        cardBean.setCardNum(card);
        boolean validflag = luhnValidation(card);
        DateTimeFormatter exmonyr = DateTimeFormatter.ofPattern("MM/uu");
        String expiryDate = month.concat("/").concat(year);
        System.out.println(expiryDate);
        YearMonth ex = YearMonth.parse(expiryDate,exmonyr);
        System.out.println(ex);
        cardBean.setExmonth(month);
        cardBean.setExyear(year);
        if(YearMonth.now().isAfter(ex)) {
            System.out.println("enter valid expiry date");
            cardBean.setCardType("Card with invalid expiry date.Please enter valid date");
           return cardBean;
        }
        if(card.matches(regExVisaCard))
        {
            if(card.matches(blacklist[0]))
                cardBean.setCardType("Blacklisted Visa Card");
            else
                cardBean.setCardType("Valid Visa Card");
        }
        else if(card.substring(0,2).matches(regExMasterCard)) {
            if (card.matches(blacklist[1])) {
                cardBean.setCardType("Blacklisted Master Card");
            } else {
                cardBean.setCardType("Valid Master Card");
            }
        }
        else
            cardBean.setCardType("Not a Visa/Master card");
        return cardBean;
    }

    private boolean luhnValidation(String card) {
        String newCard = card.substring(0,card.length()-1);
        int lastDigit = Integer.parseInt(card.substring(card.length()-2,card.length()-1));
        System.out.println("lastDigit"+lastDigit);
        System.out.println(newCard);
        StringBuilder rev =  new StringBuilder();
        rev.append(newCard);
        rev = rev.reverse();
        System.out.println(rev);
        int[] ints = new int[rev.length()];
        for (int i=0;i<rev.length();i++)
            ints[i] = Integer.parseInt(rev.substring(i,i+1));
        for (int i = 0; i < ints.length ; i+=2) {
              int j=ints[i];
              int s1 = j*2;
              if(s1>9) s1-= 9;
              ints[i] = s1;
        }
        int sum =0;
        for (int i = 0; i < ints.length ; i++)
            sum += ints[i];
        if(sum % 10 == lastDigit) {
            System.out.println(card + " is valid card");
            return true;
        }
        else {
            System.out.println(card + " is invalid card");
         return false;
        }
    }
}
