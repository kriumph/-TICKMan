package Currency;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExchangeRate {
    // Name of first currency
    private String currencyA;
    // Name of second currency
    private String currencyB;
    // Map each rate to the date it's updated
    Map<Date,Double> rateList;

    // The format of exchange rate creation should follow:
    // 1. rate indicates 1 unit of A to x unit of B.
    // 2. date object should be Date(int year, int month, int date)
    public ExchangeRate(String currencyA, String currencyB, double rate, Date date){
        this.currencyA = currencyA;
        this.currencyB = currencyB;
        rateList.put(date,rate);
    }

    public String getCurrencyA() {
        return currencyA;
    }

    public String getCurrencyB() {
        return currencyB;
    }

    // TODO: return the newest entry based on Date;
    public Map.Entry getNewestRate() {return null;};

    // TODO: add new entry to rateList;
    // NOTE: check if the date has already existed in rateList
    public void addNewRate(double rate, Date date){};

    // TODO: check if the newest rate is decreased from the last updated rate
    public boolean decreasedFromLastRate(){return false;};

    // TODO: return a sorted list of rates within a period of time, (maybe) oldest to newest
    public List<Double> getHistoricalRates(Date start, Date finish){return null;};

    // TODO: the following get methods will use the list returned from the above method
    public double getAverage(List<Double> historicalRates){
        // check list whether is empty or null, defalut 0.0 which means not historicalRates
        if (historicalRates == null || historicalRates.size() == 0) {
            return 0.0;
        }
        //check if the list only contain one value
        if(historicalRates.size() == 1){
            return historicalRates.get(0);
        }

        double sum = 0.0;
        for (Double rates : historicalRates) {
            sum += rates;
        }
        return sum / historicalRates.size();
    };

    public double getMedian(List<Double> historicalRates){
        // check list whether is empty or null, defalut 0.0 which means not historicalRates
        if (historicalRates == null || historicalRates.size() == 0) {
            return 0.0;
        }
        //check if the list only contain one value
        if(historicalRates.size() == 1){
            return historicalRates.get(0);
        }
        // make a new sorted list
        Collections.sort(sortedHistoricalRates);
        List<Double> sortedHistoricalRates = new ArrayList<>(historicalRates);
        double mid = sortedHistoricalRates.size() / 2;

        if (sortedHistoricalRates.size() % 2 == 0) {
            return (sortedHistoricalRates.get(mid - 1) + sortedHistoricalRates.get(mid)) /2.0;
        } else {
            return sortedHistoricalRates.get(mid + 1);
        }

    };

    public double getMin(List<Double> historicalRates){
    // check list whether is empty or null, defalut 0.0 which means not historicalRates
        if (historicalRates == null || historicalRates.size() == 0) {
            return 0.0;
        }
        //check if the list only contain one value
        if(historicalRates.size() == 1){
            return historicalRates.get(0);
        }

        List<Double> sortedHistoricalRates = new ArrayList<>(historicalRates);
        Collections.sort(sortedHistoricalRates);
        return sortedHistoricalRates.get(0);
    };

    public double getMax(List<Double> historicalRates){
        // check list whether is empty or null, defalut 0.0 which means not historicalRates
        if (historicalRates == null || historicalRates.size() == 0) {
            return 0.0;
        }
        //check if the list only contain one value
        if(historicalRates.size() == 1){
            return historicalRates.get(0);
        }

        List<Double> sortedHistoricalRates = new ArrayList<>(historicalRates);
        Collections.sort(sortedHistoricalRates);
        return sortedHistoricalRates.get(sortedHistoricalRates.size() - 1);
    };

    public double getSD(List<Double> historicalRates){
        // check list whether is empty or null, defalut 0.0 which means not historicalRates
        if (historicalRates == null || historicalRates.size() == 0) {
            return 0.0;
        }
        //check if the list only contain one value
        double sum = 0.0;
        double average = getAverage(historicalRates);
        // sum of (x_i - mean)^2
        for (double i : historicalRates) {
            sum +=  Math.pow((i - average),2);
        }
        // take the square root of sum value divide by sample
        return Math.sqrt( sum / (historicalRates.size()-1) );
    };

    // IGNORE: Not needed, kept for now
    @Override
    public boolean equals(Object anotherExchangeRate) {
        if (this == anotherExchangeRate) { // <-- reference identity test.
            return true;
        }
        if (anotherExchangeRate instanceof ExchangeRate) {
            ExchangeRate x = (ExchangeRate) anotherExchangeRate;
            return ((this.getCurrencyA().equals(x.getCurrencyB()) && this.getCurrencyB().equals(x.getCurrencyA())) ||
                    (this.getCurrencyA().equals(x.getCurrencyA()) && this.getCurrencyB().equals(x.getCurrencyB())));
        }
        return false;
    }
}
