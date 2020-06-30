package com.creditCard.dao;

import com.creditCard.CreditCardBean;
import org.springframework.stereotype.Component;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;


@Component
public class CreditCardDaoImpl implements CreditCardDao {

    private CreditCardBean cardBean = new CreditCardBean();
    private String regExMasterCard = "^[5][1-5]";  //Reg expression for master card
    private String regExVisaCard = "4";         // Reg expression for Visa card
    //Blacklisted cards
    private static final String[] blacklist = new String[]{"4788384538552446", "5144385438523845"};

    @Override
    public CreditCardBean validateCard(String cardnum, String month, String year) {
        String card = cardnum.replaceAll("\\s","");   //remove space from card input
        if(card.substring(0,2).matches(regExMasterCard) || card.startsWith(regExVisaCard))
           cardBean.setCardNum(card);
        else {
            cardBean.setCardType("Neither Visa or Master card");
            return cardBean;
        }
        //validate the card against Luhn algorithm
        luhnValidation(card);

        //Validate the expiry date
        DateTimeFormatter expiryMonthYear = DateTimeFormatter.ofPattern("MM/uu");
        String expiryDate = month.concat("/").concat(year);
        System.out.println(expiryDate);
        YearMonth expiry = YearMonth.parse(expiryDate,expiryMonthYear);
        System.out.println(expiry);
        cardBean.setExmonth(month);
        cardBean.setExyear(year);

        if(YearMonth.now().isAfter(expiry)) {
            System.out.println("enter valid expiry date");
            cardBean.setExmonth("Invalid date");
            cardBean.setExyear("Invalid date");
        }

        //validation of blacklisted card
        blacklist(card);
        return cardBean;
    }

    private void blacklist(String cardnumber)
    {
        if(cardnumber.matches(blacklist[0]))
            cardBean.setCardType("Blacklisted Visa Card");
        else if(cardnumber.matches(blacklist[1]))
            cardBean.setCardType("Blacklisted Master Card");
        /*else cardBean.setCardType("Not a Blacklisted Card");*/
    }


    //Luhn algorithm to validate last digit of card
    private void luhnValidation(String card) {
        String newCard = card.substring(0,card.length()-1);        //Remove the last digit of card
        int lastDigit = Integer.parseInt(card.substring(card.length()-2,card.length()-1));  //get the last digit of card
        System.out.println("lastDigit"+lastDigit);
        System.out.println(newCard);

        //reverse the card number
        StringBuilder rev =  new StringBuilder();
        rev.append(newCard);
        rev = rev.reverse();
        System.out.println(rev);
        int[] ints = new int[rev.length()];      //convert to int array
        for (int i=0;i<rev.length();i++)
            ints[i] = Integer.parseInt(rev.substring(i,i+1));
        for (int i = 0; i < ints.length ; i+=2) {
              int j=ints[i];
              int s1 = j*2;             //multiply digits at even index by 2
              if(s1>9) s1-= 9;          //subtract by 9 if value becomes 2 digit
              ints[i] = s1;
        }
        int sum =0;
        for (int i = 0; i < ints.length ; i++)
            sum += ints[i];             //add all digits
        if(sum % 10 == lastDigit) {
            System.out.println(card + " is valid card");
            cardBean.setCardType("Valid Card number by Luhn Algorithm");
        }
        else {
            System.out.println(card + " is invalid card");
         cardBean.setCardType("Invalid card number by Luhn Algorithm");
        }
    }
}
