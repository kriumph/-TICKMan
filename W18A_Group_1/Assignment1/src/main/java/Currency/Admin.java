package Currency;

import java.util.Date;
import java.util.ArrayList;

public class Admin implements AdminFeatures, UserFeatures{
    CurrencyConverter cc;
    ArrayList<String> PopularCurrencies = new ArrayList<String>();

    public Admin(CurrencyConverter cc){
        this.cc = cc;
    }

    @Override
    public void setPopularCurrency(String Currency) {
        //determine whether currency in our database
        for(String keyCurrency: cc.currencyCoordinates.keySet()){
            if(Currency.equals(keyCurrency) && PopularCurrencies.size() <= 4){
                PopularCurrencies.add(Currency);
                System.out.printf("%s has been updated as popular currency successfully.\n", Currency);
                //print all popular currencies in list in one line
                for(String popularCurrency: PopularCurrencies){
                    System.out.print(popularCurrency + " ");
                }
                //print which currencies are popular now
                if(PopularCurrencies.size() == 1){
                    System.out.println("is popular currency now.");
                }else if(PopularCurrencies.size() == 0){
                    System.out.println("There is no popular currency now.");
                }else{
                    System.out.println("are popular currencies now.");
                }
                //print how many popular currencies can be specified now
                if(PopularCurrencies.size() == 0){
                    System.out.println("Four new popular currencies can be specified as trending.");
                }
                if(PopularCurrencies.size() == 1){
                    System.out.println("Three new popular currencies can be specified as trending.");
                }
                if(PopularCurrencies.size() == 2){
                    System.out.println("Two new popular currencies can be specified as trending.");
                }
                if(PopularCurrencies.size() == 3){
                    System.out.println("One new popular currency can be specified as trending.");
                }
                if(PopularCurrencies.size() == 4){
                    System.out.println("No more blank spaces for new popular currency can be specified as trending.");
                    System.out.println("You can see the trending table by clicking 'Trending' button left side.");
                }
            }
        }
        //If our database does not include this currency now.
        System.out.printf("Our database does not include %s.\n", Currency);
        System.out.println("You can add this currency into our database at first.");
    }

    @Override
    public double exchangeCurrency(String fromCurrency, String toCurrency, double amount) {
        return cc.getRate(fromCurrency,toCurrency)*amount;
    }

    @Override
    public ArrayList<String> getPopularCurrencies() {return PopularCurrencies;}

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
