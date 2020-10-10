package Currency;


import Currency.Logic.Admin;
import Currency.Logic.CurrencyConverter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdminTest{
    CurrencyConverter cc = new CurrencyConverter("src/main/java/Currency/Logic/RateTxt.txt");
    Admin admin = new Admin(cc);
    ArrayList<String> PopularCurrencies = new ArrayList<String>();

    @Test
    public void exchangeCurrencyTest() {
        double result= admin.exchangeCurrency("USD","CNY",10);
        double result1= admin.exchangeCurrency("CNY","USD",10);
        assertEquals((double) Math.round(result * 100)/100, 67.91);
        assertEquals((double) Math.round(result1 * 100) / 100, 1.47);
    }

    @Test
    public void setPopularCurrencyTest() {
        admin.setPopularCurrency("AUD");
    }

    @Test
    public void displayCurrenciesTest() {
        admin.displayCurrencies();
    }
    @Test
    public void addCurrencyTest() {
        Date date = new Date(120,9,7);
        admin.addCurrency("AUD",date);
    }

    @Test
    public void addNewRateTest() {
        Date date = new Date(120,9,7);
        admin.addNewRate("AUD","USD",1.0,date);
    }

    @Test
    public void getPopularCurrenciesTest() {
        assertEquals(admin.getPopularCurrencies(),PopularCurrencies);

    }

    @Test
    public void getPopularRatesTest() {
    }

}
