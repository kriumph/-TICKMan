package Currency;


import Currency.Logic.CurrencyConverter;
import org.junit.jupiter.api.Test;

import java.util.*;

class CurrencyConverterTest{
    CurrencyConverter cc = new CurrencyConverter("src/main/java/Currency/RateTxt.txt");

    @Test
    public void getCurrencyIndexTest() {
        //get "first" AUD index
//       assertEquals(cc.getCurrencyIndex("AUD"),0);
//        //get "first" USD index
//       assertEquals(cc.getCurrencyIndex("USD"),2);
    }

    @Test
    public void addExchangeRateTest() {
        Date date = new Date(120,9,7);
        cc.addExchangeRate("AUD","USD",1.0,date);
    }


    @Test
    public void displayExchangeRateTest() {
        cc.displayExchangeRate();
    }

    @Test
    public void getAllRateTest() {
        cc.getAllRate("AUD","USD");
    }

    @Test
    public void addCurrencyTest() {
        Date date = new Date(120,9,7);
        cc.addCurrency("AUD",date);
    }
}
