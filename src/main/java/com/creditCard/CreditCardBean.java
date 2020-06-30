package com.creditCard;

public class CreditCardBean {
    private String cardType;
    private String cardNum;
    private String exmonth;
    private String exyear;

    public CreditCardBean()
    { }

    public CreditCardBean(String cardType, String cardNum, String exmonth, String exyear) {
        this.cardType = cardType;
        this.cardNum = cardNum;
        this.exmonth = exmonth;
        this.exyear = exyear;
    }

    public String getExmonth() {
        return exmonth;
    }

    public void setExmonth(String exmonth) {
        this.exmonth = exmonth;
    }

    public String getExyear() {
        return exyear;
    }

    public void setExyear(String exyear) {
        this.exyear = exyear;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }


    @Override
    public String toString() {
        return "CreditCardDetails " + "\n" +
                "cardType='" + cardType + '\'' +
                ", cardNum=" + cardNum +
                ", expirymonth=" + exmonth +
                ", expiryyear=" + exyear ;
    }
}
