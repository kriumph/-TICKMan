package Currency;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CurrencyConverter {

    List<ExchangeRate> exchangeRateList;
    Map<String,Integer> currencyCoordinates;
    Date creationDate = new Date(120,9,7);
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    int counter = 0;

    // TODO: read each line from file and format them into ExchangeRate
    public void readERFromFile(String filepath) {
        try {
            File ER = new File(filepath);
            Scanner ERReader = new Scanner(ER);
            while (ERReader.hasNextLine()) {
                String info = ERReader.nextLine();
                String splitInfo[] = info.split(",");
                if (currencyCoordinates.putIfAbsent(splitInfo[0], this.counter) == null) {
                    this.counter++;
                }
                if (currencyCoordinates.putIfAbsent(splitInfo[1], this.counter) == null) {
                    this.counter++;
                }
                exchangeRateList.add(new ExchangeRate(splitInfo[0], splitInfo[1], Double.parseDouble(splitInfo[2]), creationDate));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file.");
            e.printStackTrace();
        }
    }

    // TODO: first create a ER object and check if it exists in exchangeRateList, add if not exists
    // NOTE: .equals() for ER object is manually overridden, should compare two ER with no problem
    public void addExchangeRate(String currencyA, String currencyB, double rate, Date date){
        ExchangeRate er = new ExchangeRate(currencyA, currencyB, rate, date);
        for (ExchangeRate exRate : exchangeRateList){
            if (er.equals(exRate)){
                exRate.addNewRate(rate, date);
                return;
            }
        }
        System.out.println("One of the currency has not been added.");
    }

    public void displayExchangeRate(){
        for (ExchangeRate er : exchangeRateList){
            System.out.printf("CurrencyA: %s   CurrencyB: %s    Rate:%.2f    Date:%s\n",
                    er.getCurrencyA(),er.getCurrencyB(),er.getNewestRate().getValue(),formatter.format(er.getNewestRate().getKey()));
        }
    }

    public double getRate(String A, String B){
        ExchangeRate er = new ExchangeRate(A,B,0,new Date());
        for (ExchangeRate exRate : exchangeRateList){
            if (er.equals(exRate)){
                return exRate.getNewestRate().getValue();
            }
        }
        return -1;
    }

    public void getAllRate(String A, String B){
        ExchangeRate er = new ExchangeRate(A,B,0,new Date());
        for (ExchangeRate exRate : exchangeRateList){
            if (er.equals(exRate)){
                exRate.getAllRate();
            }
        }
    }

    public void addCurrency(String newCurrency, Date date){
        Set <String> currencySet = currencyCoordinates.keySet();

        if (currencySet.contains(newCurrency)){
            System.out.println("This currency has already been added.");
            return;
        }
        currencyCoordinates.put(newCurrency,this.counter++);
        Scanner inputScanner = new Scanner(System.in);

        for (String toCurrency : currencySet){
            System.out.printf("Please input exchange rate from %s to %s:\n",newCurrency,toCurrency);
            try{
                String userInput = inputScanner.nextLine();
                double newRate = Double.parseDouble(userInput);
                exchangeRateList.add(new ExchangeRate(newCurrency,toCurrency,newRate,date));
                System.out.printf("%s to %s at %f has been added.\n",newCurrency,toCurrency,newRate);
            } catch (Exception e) {
                System.out.println("You have entered an invalid input.\nPlease try again.");
                e.printStackTrace();
            }
        }
    }

    // TODO: get the corresponding index based on currency
    public int getCurrencyIndex(String currency){return currencyCoordinates.get(currency);}

    public CurrencyConverter(String filepath){
        currencyCoordinates = new HashMap<>();
        exchangeRateList = new ArrayList<>();

        readERFromFile(filepath);
//        displayExchangeRate();
    }
}
