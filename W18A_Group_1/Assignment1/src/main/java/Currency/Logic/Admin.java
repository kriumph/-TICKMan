package Currency.Logic;

import java.util.Date;
import java.util.ArrayList;

public class Admin implements AdminFeatures, UserFeatures{
    CurrencyConverter cc;
    ArrayList<String> PopularCurrencies = new ArrayList<String>();

    public Admin(CurrencyConverter cc){
        this.cc = cc;
    }

    @Override
    public void setPopularCurrency(String Currency, int indexOfPopular) {
        this.PopularCurrencies.add(indexOfPopular, Currency);
    }

    @Override
    public double exchangeCurrency(String fromCurrency, String toCurrency, double amount) {
        return cc.getRate(fromCurrency,toCurrency)*amount;
    }

    @Override
    public ArrayList<String> getPopularCurrencies() {return this.PopularCurrencies;}

    @Override
    public ArrayList<ExchangeRate> getPopularRates(){
        ArrayList<ExchangeRate> PopularRates = new ArrayList<ExchangeRate>();
        ArrayList<String> PopularCurrencies = this.getPopularCurrencies();
        //Name of our popular currencies
        String popularCurrencyA = PopularCurrencies.get(0);
        String popularCurrencyB = PopularCurrencies.get(1);
        String popularCurrencyC = PopularCurrencies.get(2);
        String popularCurrencyD = PopularCurrencies.get(3);
        //Find 12 popular rates with order
        //A-B, A-C, A-D
        //B-A, B-C, B-D
        //C-A, C-B, C-D
        //D-A, D-B, D-C
        for(ExchangeRate er : cc.exchangeRateList){
            if(er.getCurrencyA().equals(popularCurrencyA) && er.getCurrencyB().equals(popularCurrencyB)){
                PopularRates.add(er);
            }
            else if(er.getCurrencyA().equals(popularCurrencyA) && er.getCurrencyB().equals(popularCurrencyC)){
                PopularRates.add(er);
            }
            else if(er.getCurrencyA().equals(popularCurrencyA) && er.getCurrencyB().equals(popularCurrencyD)){
                PopularRates.add(er);
            }
            else if(er.getCurrencyA().equals(popularCurrencyB) && er.getCurrencyB().equals(popularCurrencyA)){
                PopularRates.add(er);
            }
            else if(er.getCurrencyA().equals(popularCurrencyB) && er.getCurrencyB().equals(popularCurrencyC)){
                PopularRates.add(er);
            }
            else if(er.getCurrencyA().equals(popularCurrencyB) && er.getCurrencyB().equals(popularCurrencyD)){
                PopularRates.add(er);
            }
            else if(er.getCurrencyA().equals(popularCurrencyC) && er.getCurrencyB().equals(popularCurrencyA)){
                PopularRates.add(er);
            }
            else if(er.getCurrencyA().equals(popularCurrencyC) && er.getCurrencyB().equals(popularCurrencyB)){
                PopularRates.add(er);
            }
            else if(er.getCurrencyA().equals(popularCurrencyC) && er.getCurrencyB().equals(popularCurrencyD)){
                PopularRates.add(er);
            }
            else if(er.getCurrencyA().equals(popularCurrencyD) && er.getCurrencyB().equals(popularCurrencyA)){
                PopularRates.add(er);
            }
            else if(er.getCurrencyA().equals(popularCurrencyD) && er.getCurrencyB().equals(popularCurrencyB)){
                PopularRates.add(er);
            }
            else if(er.getCurrencyA().equals(popularCurrencyD) && er.getCurrencyB().equals(popularCurrencyC)){
                PopularRates.add(er);
            }
        }
        return PopularRates;
    }

    @Override
    public void addNewRate(String currencyA, String currencyB, double rate, Date date) {
        cc.addExchangeRate(currencyA,currencyB,rate,date);
    }

    @Override
    public void addCurrency(String currencyName, Date date){
        cc.addCurrency(currencyName, date);
    }

    @Override
    public void displayCurrencies() {
        cc.displayExchangeRate();
    }
}
