package Currency.Logic;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;;

public class ExchangeRate {
    // Name of first currency
    private String currencyA;
    // Name of second currency
    private String currencyB;
    // Map each rate to the date it's updated
    private Map<Date,Double> rateList;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    // The format of exchange rate creation should follow:
    // 1. rate indicates 1 unit of A to x unit of B.
    // 2. date object should be Date(int year, int month, int date)
    public ExchangeRate(String currencyA, String currencyB, double rate, Date date){
        rateList = new HashMap<Date, Double>();
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



    public Map.Entry<Date, Double> getNewestRate() {
        Set<Map.Entry<Date,Double>> exchangeRateEntrySet = rateList.entrySet();
        Map.Entry<Date,Double> newestEntry = null ;
        Date newestDate = new Date(0L);
        
        for(Map.Entry<Date,Double> eachEntry : exchangeRateEntrySet ){
            if (eachEntry.getKey().after(newestDate)){
                newestEntry = eachEntry;
                newestDate = eachEntry.getKey();
            }
        }
        return newestEntry;
        
    }
    
    

    public void getAllRate() {
        Set<Map.Entry<Date,Double>> exchangeRateEntrySet = rateList.entrySet();

        for(Map.Entry<Date,Double> eachEntry : exchangeRateEntrySet ){
            System.out.printf("DATE: %s  RATE:%f\n",formatter.format(eachEntry.getKey()),eachEntry.getValue());
        }
    }



    public void addNewRate(double rate, Date date){
        rateList.put(date, rate);
/*
        Date lastUpdatedDate = this.getNewestRate().getKey();
        double oldRate = this.getNewestRate().getValue();
        Calendar myCalendar = new GregorianCalendar();
        myCalendar.setTime(lastUpdatedDate);

        while (myCalendar.getTime().before(date)){
            Date result = myCalendar.getTime();
            rateList.putIfAbsent(result,oldRate);
            myCalendar.add(Calendar.DATE, 1);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Double result = rateList.putIfAbsent(date, rate);
        if (result!=null){
            System.out.printf("The rate %f has already been added\n",result);
            return;
        }
        System.out.printf("CurrencyA: %s   CurrencyB: %s    Rate:%.2f    Date:%s\n",
                this.getCurrencyA(),this.getCurrencyB(),this.getNewestRate().getValue(),formatter.format(this.getNewestRate().getKey()));
        System.out.println("Exchange rate successfully added.");
*/
    }
    
    

    public boolean decreasedFromLastRate(){
        if(rateList.keySet().size() < 2){
            return false;
        }
        Map.Entry<Date, Double> newestEntry = getNewestRate();
        Date newestDate = newestEntry.getKey();
        Date lastDate = new Date(0L);
        
        for(Date dt : rateList.keySet()){
            if (dt.after(lastDate) && dt != newestEntry){
                lastDate = dt;
            } 
        }
        return rateList.get(lastDate) > newestEntry.getValue() ;
        
    }


    public boolean increasedFromLastRate(){
        if(rateList.keySet().size() < 2){
            return false;
        }
        Map.Entry<Date, Double> newestEntry = getNewestRate();
        Date newestDate = newestEntry.getKey();
        Date lastDate = new Date(0L);

        for(Date dt : rateList.keySet()){
            if (dt.after(lastDate) && dt != newestEntry){
                lastDate = dt;
            }
        }
        return rateList.get(lastDate) < newestEntry.getValue() ;

    }


    
    public List<Double> getHistoricalRates(Date start, Date finish){
        
        //get the designated period of time;
        List<Date> dateSet = rateList.keySet().stream().filter(dt -> dt.after(start) && dt.before(finish)).collect(Collectors.toList());
        
        // sort the dateset from the oldest to the newest
        Collections.sort(dateSet, (Date dt1, Date dt2 )-> (dt2.compareTo(dt1)));
        
        // add the relative exchanges rates in the designated period 
        List<Double> historicalRates = new ArrayList<Double>();
        for(Date dt : dateSet){
            historicalRates.add(this.rateList.get(dt));
        }
        return historicalRates;
        
    }



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
    }



    public double getMedian(List<Double> historicalRates){
        // check list whether is empty or null, default 0.0 which means not historicalRates
        if (historicalRates == null || historicalRates.size() == 0) {
            return 0.0;
        }
        //check if the list only contain one value
        if(historicalRates.size() == 1){
            return historicalRates.get(0);
        }
        // make a new sorted list
        Collections.sort(historicalRates);
        List<Double> sortedHistoricalRates = new ArrayList<Double>(historicalRates);
        int mid = sortedHistoricalRates.size() / 2;

        if (sortedHistoricalRates.size() % 2 == 0) {
            return (sortedHistoricalRates.get(mid - 1) + sortedHistoricalRates.get(mid)) /2.0;
        } else {
            return sortedHistoricalRates.get(mid);
        }

    }



    public double getMin(List<Double> historicalRates){
        // check list whether is empty or null, default 0.0 which means not historicalRates
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
    }
    
    

    public double getMax(List<Double> historicalRates){
        // check list whether is empty or null, default 0.0 which means not historicalRates
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
    }
    
    

    public Double getSD(List<Double> historicalRates){
        // check list whether is empty or null, default 0.0 which means not historicalRates
        if (historicalRates == null || historicalRates.size() == 0) {
            return 0.0;
        }
        //check if the list only contain one value
        if(historicalRates.size() == 1){
            return 0.0;
        }

        double sum = 0.0;
        double average = getAverage(historicalRates);

        // sum of (x_i - mean)^2
        for (double i : historicalRates) {
            sum +=  Math.pow((i - average),2);
        }
        // here is sample standard Deviation, take the square root of sum value divide by sample

        double result = Math.sqrt( sum / (historicalRates.size()-1) );
        return (double) Math.round(result * 100)/100;
    }
    
    
    
    public Double getMaxRecordInThePeriod(Date start, Date end){
        return getMax(getHistoricalRates(start, end));
    }



    public Double getMinRecordInThePeriod(Date start, Date end){
        return getMin(getHistoricalRates(start, end));
    }
    
    
    
    public Double getGlobalReocordMax(){
        return getMaxRecordInThePeriod( new Date(0), new Date());
    }
    
    
    
    public Double getGlobalReocordMin(){
        return getMinRecordInThePeriod( new Date(0), new Date());
    }



    @Override
    public String toString(){
        Double rate = getNewestRate().getValue();
        boolean desc = this.decreasedFromLastRate();
        boolean asc = this.increasedFromLastRate();
        if(!asc && !desc){
            return rate + "=";
        } 
        return rate + ((desc) ? "↓" : "↑"); 
    }
}
