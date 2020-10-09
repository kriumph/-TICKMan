package Currency;

import java.util.ArrayList;

public class User implements UserFeatures{
    CurrencyConverter cc;
    Admin admin;
    public User(CurrencyConverter cc){
        this.cc = cc;
    }

    @Override
    public double exchangeCurrency(String fromCurrency, String toCurrency, double amount) {
        return cc.getRate(fromCurrency,toCurrency)*amount;
    }

    @Override
    public ArrayList<String> getPopularCurrencies() {return admin.PopularCurrencies;}

    @Override
    public void getPopularRates(){
        ArrayList<Double> PopularRates = new ArrayList<Double>();
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
                PopularRates.add(er.getNewestRate().getValue());
            }
            else if(er.getCurrencyA().equals(popularCurrencyA) && er.getCurrencyB().equals(popularCurrencyC)){
                PopularRates.add(er.getNewestRate().getValue());
            }
            else if(er.getCurrencyA().equals(popularCurrencyA) && er.getCurrencyB().equals(popularCurrencyD)){
                PopularRates.add(er.getNewestRate().getValue());
            }
            else if(er.getCurrencyA().equals(popularCurrencyB) && er.getCurrencyB().equals(popularCurrencyA)){
                PopularRates.add(er.getNewestRate().getValue());
            }
            else if(er.getCurrencyA().equals(popularCurrencyB) && er.getCurrencyB().equals(popularCurrencyC)){
                PopularRates.add(er.getNewestRate().getValue());
            }
            else if(er.getCurrencyA().equals(popularCurrencyB) && er.getCurrencyB().equals(popularCurrencyD)){
                PopularRates.add(er.getNewestRate().getValue());
            }
            else if(er.getCurrencyA().equals(popularCurrencyC) && er.getCurrencyB().equals(popularCurrencyA)){
                PopularRates.add(er.getNewestRate().getValue());
            }
            else if(er.getCurrencyA().equals(popularCurrencyC) && er.getCurrencyB().equals(popularCurrencyB)){
                PopularRates.add(er.getNewestRate().getValue());
            }
            else if(er.getCurrencyA().equals(popularCurrencyC) && er.getCurrencyB().equals(popularCurrencyD)){
                PopularRates.add(er.getNewestRate().getValue());
            }
            else if(er.getCurrencyA().equals(popularCurrencyD) && er.getCurrencyB().equals(popularCurrencyA)){
                PopularRates.add(er.getNewestRate().getValue());
            }
            else if(er.getCurrencyA().equals(popularCurrencyD) && er.getCurrencyB().equals(popularCurrencyB)){
                PopularRates.add(er.getNewestRate().getValue());
            }
            else if(er.getCurrencyA().equals(popularCurrencyD) && er.getCurrencyB().equals(popularCurrencyC)){
                PopularRates.add(er.getNewestRate().getValue());
            }
        }
    }
}

