
package Currency;

import Currency.Logic.ExchangeRate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;
class ExchangeRateTest {
    Date date = new Date();
    ExchangeRate exchangeRate = new ExchangeRate("AUD","USD",1.0,date);
    Map<Date,Double> rateList;
    @Test
    public void getCurrencyATest() {
        // test get currencyA
        assertEquals(exchangeRate.getCurrencyA(),"AUD");
    }

    @Test
    public void getCurrencyBTest() {
        // test get currencyB
        assertEquals(exchangeRate.getCurrencyB(),"USD");
    }

    @Test
    public void getAverageTest() {
        List<Double> ls = new ArrayList<>();
        // test list is empty or null
        assertEquals(exchangeRate.getAverage(ls),0.0);
        ls.add(1.0);
        // test list only contains one value, return this value
        assertEquals(exchangeRate.getAverage(ls),1.0);
        ls.add(2.0);
        ls.add(3.0);
        // test get average
        assertEquals(exchangeRate.getAverage(ls),2.0);

    }

    @Test
    public void getMedianTest() {
        List<Double> ls = new ArrayList<>();
        // test list is empty or null
        assertEquals(exchangeRate.getMedian(ls),0.0);
        ls.add(1.0);
        // test list only contains one value, return this value
        assertEquals(exchangeRate.getMedian(ls),1.0);
        ls.add(2.0);
        ls.add(3.0);
        // test gets median when the list contains odd number.
        assertEquals(exchangeRate.getMedian(ls),2.0);
        ls.add(4.0);
        // test gets median when the list contains even number.
        assertEquals(exchangeRate.getMedian(ls),2.5);

    }

    @Test
    public void getMinTest() {
        List<Double> ls = new ArrayList<>();
        // test list is empty or null
        assertEquals(exchangeRate.getMin(ls),0.0);
        ls.add(1.0);
        // test list only contains one value, return this value
        assertEquals(exchangeRate.getMin(ls),1.0);
        ls.add(2.0);
        ls.add(3.0);
        // test get minimum, all number are positive
        assertEquals(exchangeRate.getMin(ls),1.0);
        ls.add(-1.5);
        // test get minimum, some positive and some negative.
        assertEquals(exchangeRate.getMin(ls),-1.5);
    }

    @Test
    public void getMaxTest() {
        List<Double> ls = new ArrayList<>();
        // test list is empty or null
        assertEquals(exchangeRate.getMax(ls),0.0);
        ls.add(1.0);
        // test list only contains one value, return this value
        assertEquals(exchangeRate.getMax(ls),1.0);
        ls.add(2.0);
        ls.add(5.0);
        // test get maximum, all number are positive
        assertEquals(exchangeRate.getMax(ls),5.0);
        ls.add(-1.5);
        // test get maximum, some positive and some negative.
        assertEquals(exchangeRate.getMax(ls),5.0);
    }

    @Test
    public void getSDTest() {
        // test list is empty or null
        List<Double> ls = new ArrayList<>();
        assertEquals(exchangeRate.getSD(ls),0.0);
        ls.add(1.0);
        // test list only contains one value, return this value
        assertEquals(exchangeRate.getSD(ls),0.0);
        ls.add(2.0);
        ls.add(4.0);
        ls.add(3.0);
        // test standard Deviation, here is sample standard Deviation
        assertEquals(exchangeRate.getSD(ls),1.29);

    }

    @Test
    public void equalsTest() {
        ExchangeRate anotherExchangeRate = new ExchangeRate("AUD","USD",1.0,date);
        ExchangeRate anotherExchangeRate1 = new ExchangeRate("CNY","AUD",2.0,date);

        // test equals
        assert ( !exchangeRate.equals(anotherExchangeRate)) : "test equals failed";
        assertFalse(exchangeRate.equals(anotherExchangeRate1));

    }

    @Test
    public void getAllRateTest_1() {
        Date date_1 = new Date(0);
        Date date_2 = new Date(1);
        Date date_3 = new Date(2);
        Date date_4 = new Date(3);
        Date date_5 = new Date(4);
        Date date_6 = new Date(5);
        Date date_7 = new Date(6);
        Date date_8 = new Date(7);
        Date date_9 = new Date(8);
        Date date_10 = new Date(9);
        Date date_11 = new Date(10);
        Date date_12 = new Date(11);
        ExchangeRate exrate_1 = new ExchangeRate("AUD" ,"USD", 0.78, date_1);
        exrate_1.addNewRate(0.79, date_1);
        exrate_1.addNewRate(0.78, date_2);
        exrate_1.addNewRate(0.76, date_3);
        exrate_1.addNewRate(0.75, date_4);
        exrate_1.addNewRate(0.74, date_5);
        exrate_1.addNewRate(0.73, date_6);
        exrate_1.addNewRate(0.72, date_7);
        exrate_1.addNewRate(0.71, date_8);
        exrate_1.addNewRate(0.70, date_9);
        exrate_1.addNewRate(0.69, date_10);
        exrate_1.addNewRate(0.66, date_11);
        exrate_1.addNewRate(0.67, date_12);
        exrate_1.getAllRate();
    }

    @Test
    public void decreasedFromLastRateTest_1() {
        Date date_1 = new Date(0);
        Date date_2 = new Date(1);
        Date date_3 = new Date(2);
        Date date_4 = new Date(3);
        Date date_5 = new Date(4);
        Date date_6 = new Date(5);
        Date date_7 = new Date(6);
        Date date_8 = new Date(7);
        Date date_9 = new Date(8);
        Date date_10 = new Date(9);
        Date date_11 = new Date(10);
        Date date_12 = new Date(11);
        ExchangeRate exrate_1 = new ExchangeRate("AUD" ,"USD", 0.78, date_1);
        exrate_1.addNewRate(0.79, date_1);
        assert ( !exrate_1.decreasedFromLastRate()):"decreasedFromLastRateTest Failed ";
        exrate_1.addNewRate(0.78, date_2);
        assert ( !exrate_1.decreasedFromLastRate()):"decreasedFromLastRateTest Failed ";
        exrate_1.addNewRate(0.76, date_3);
        assert ( !exrate_1.decreasedFromLastRate()):"decreasedFromLastRateTest Failed ";
        exrate_1.addNewRate(0.75, date_4);
        assert ( !exrate_1.decreasedFromLastRate()):"decreasedFromLastRateTest Failed ";
        exrate_1.addNewRate(0.74, date_5);
        assert ( !exrate_1.decreasedFromLastRate()):"decreasedFromLastRateTest Failed ";
        exrate_1.addNewRate(0.73, date_6);
        assert ( !exrate_1.decreasedFromLastRate()):"decreasedFromLastRateTest Failed ";
        exrate_1.addNewRate(0.72, date_7);
        assert ( !exrate_1.decreasedFromLastRate()):"decreasedFromLastRateTest Failed ";
        exrate_1.addNewRate(0.71, date_8);
        assert ( !exrate_1.decreasedFromLastRate()):"decreasedFromLastRateTest Failed ";
        exrate_1.addNewRate(0.70, date_9);
        assert ( !exrate_1.decreasedFromLastRate()):"decreasedFromLastRateTest Failed ";
        exrate_1.addNewRate(0.71, date_10);
        assert ( !exrate_1.decreasedFromLastRate()):"decreasedFromLastRateTest Failed ";
        exrate_1.addNewRate(0.76, date_11);
        assert ( !exrate_1.decreasedFromLastRate()):"decreasedFromLastRateTest Failed ";
        exrate_1.addNewRate(0.77, date_12);
        assert ( !exrate_1.decreasedFromLastRate()):"decreasedFromLastRateTest Failed ";

    }

    @Test
    public void increasedFromLastRateTest_1() {
        Date date_1 = new Date(0);
        Date date_2 = new Date(1);
        Date date_3 = new Date(2);
        Date date_4 = new Date(3);
        Date date_5 = new Date(4);
        Date date_6 = new Date(5);
        Date date_7 = new Date(6);
        Date date_8 = new Date(7);
        Date date_9 = new Date(8);
        Date date_10 = new Date(9);
        Date date_11 = new Date(10);
        Date date_12 = new Date(11);
        ExchangeRate exrate_1 = new ExchangeRate("AUD" ,"USD", 0.78, date_1);
        exrate_1.addNewRate(0.79, date_1);
        assert( !exrate_1.increasedFromLastRate()) :  "Increased form last Rate test failed";
        exrate_1.addNewRate(0.70, date_2);
        assert ( !exrate_1.increasedFromLastRate()) :  "Increased form last Rate test failed";
        exrate_1.addNewRate(0.76, date_3);
        assert ( !exrate_1.increasedFromLastRate()) :  "Increased form last Rate test failed";
        exrate_1.addNewRate(0.761, date_4);
        assert ( !exrate_1.increasedFromLastRate()) :  "Increased form last Rate test failed";
        exrate_1.addNewRate(0.74, date_5);
        assert ( !exrate_1.increasedFromLastRate()) :  "Increased form last Rate test failed";
        exrate_1.addNewRate(0.75, date_6);
        assert ( !exrate_1.increasedFromLastRate()) :  "Increased form last Rate test failed";
        exrate_1.addNewRate(0.72, date_7);
        assert ( !exrate_1.increasedFromLastRate()) :  "Increased form last Rate test failed";
        exrate_1.addNewRate(0.71, date_8);
        assert ( !exrate_1.increasedFromLastRate()) :  "Increased form last Rate test failed";
        exrate_1.addNewRate(0.50, date_9);
        assert ( !exrate_1.increasedFromLastRate()) :  "Increased form last Rate test failed";
        exrate_1.addNewRate(0.69, date_10);
        assert ( !exrate_1.increasedFromLastRate()) :  "Increased form last Rate test failed";
        exrate_1.addNewRate(0.66, date_11);
        assert (!exrate_1.increasedFromLastRate()) :  "Increased form last Rate test failed";
        exrate_1.addNewRate(0.67, date_12);
        assert ( !exrate_1.increasedFromLastRate()) :  "Increased form last Rate test failed";
    }

    @Test
    public void getMaxRecordInThePeriod_1(){
        Date date_1 = new Date(0);
        Date date_2 = new Date(1);
        Date date_3 = new Date(2);
        Date date_4 = new Date(3);
        Date date_5 = new Date(4);
        Date date_6 = new Date(5);
        Date date_7 = new Date(6);
        Date date_8 = new Date(7);
        Date date_9 = new Date(8);
        Date date_10 = new Date(9);
        Date date_11 = new Date(10);
        Date date_12 = new Date(11);
        ExchangeRate exrate_1 = new ExchangeRate("AUD" ,"USD", 0.78, date_1);
        exrate_1.addNewRate(0.79, date_1);
        exrate_1.addNewRate(0.78, date_2);
        exrate_1.addNewRate(0.76, date_3);
        exrate_1.addNewRate(0.799, date_4);
        exrate_1.addNewRate(0.74, date_5);
        exrate_1.addNewRate(0.73, date_6);
        exrate_1.addNewRate(0.72, date_7);
        exrate_1.addNewRate(0.71, date_8);
        exrate_1.addNewRate(0.70, date_9);
        exrate_1.addNewRate(0.69, date_10);
        exrate_1.addNewRate(0.66, date_11);
        exrate_1.addNewRate(0.679, date_12);
        assert (exrate_1.getMaxRecordInThePeriod(date_1, date_3).equals(0.78) ) : "Max record in the period failed";
//        assert (exrate_1.getMaxRecordInThePeriod(date_1, date_5).equals(0.799) ) : "Max record in the period failed"; 
//        assert (exrate_1.getMaxRecordInThePeriod(date_5, date_7).equals(0.74) ) : "Max record in the period failed"; 
        assert (!exrate_1.getMaxRecordInThePeriod(date_9, date_11).equals(0.70) ) : "Max record in the period failed";
        assert (!exrate_1.getMaxRecordInThePeriod(date_5, date_12).equals(0.74) ) : "Max record in the period failed";
        assert (!exrate_1.getMaxRecordInThePeriod(date_4, date_12).equals(0.799) ) : "Max record in the period failed";

    }


    @Test
    public void getMinRecordInThePeriod_1(){
        Date date_1 = new Date(0);
        Date date_2 = new Date(1);
        Date date_3 = new Date(2);
        Date date_4 = new Date(3);
        Date date_5 = new Date(4);
        Date date_6 = new Date(5);
        Date date_7 = new Date(6);
        Date date_8 = new Date(7);
        Date date_9 = new Date(8);
        Date date_10 = new Date(9);
        Date date_11 = new Date(10);
        Date date_12 = new Date(11);
        ExchangeRate exrate_1 = new ExchangeRate("AUD" ,"USD", 0.78, date_1);
        exrate_1.addNewRate(0.79, date_1);
        exrate_1.addNewRate(0.78, date_2);
        exrate_1.addNewRate(0.76, date_3);
        exrate_1.addNewRate(0.599, date_4);
        exrate_1.addNewRate(0.74, date_5);
        exrate_1.addNewRate(0.73, date_6);
        exrate_1.addNewRate(0.72, date_7);
        exrate_1.addNewRate(0.71, date_8);
        exrate_1.addNewRate(0.70, date_9);
        exrate_1.addNewRate(0.69, date_10);
        exrate_1.addNewRate(0.66, date_11);
        exrate_1.addNewRate(0.679, date_12);
        assert ( !exrate_1.getMinRecordInThePeriod(date_4, date_12).equals(0.599) ) : "Min record in the period failed";
        assert ( !exrate_1.getMaxRecordInThePeriod(date_1, date_12).equals(0.599) ) : "Max record in the period failed";
        assert ( !exrate_1.getMaxRecordInThePeriod(date_5, date_12).equals(0.66) ) : "Max record in the period failed";
        assert ( !exrate_1.getMaxRecordInThePeriod(date_1, date_2).equals(0.78) ) : "Max record in the period failed";
        assert ( !exrate_1.getMaxRecordInThePeriod(date_1, date_3).equals(0.76) ) : "Max record in the period failed";
        assert ( !exrate_1.getMaxRecordInThePeriod(date_5, date_9).equals(0.70) ) : "Max record in the period failed";


    }


    @Test
    public void getGlobalReocordMaxTest_1(){
        Date date_1 = new Date(0);
        Date date_2 = new Date(1);
        Date date_3 = new Date(2);
        Date date_4 = new Date(3);
        Date date_5 = new Date(4);
        Date date_6 = new Date(5);
        Date date_7 = new Date(6);
        Date date_8 = new Date(7);
        Date date_9 = new Date(8);
        Date date_10 = new Date(9);
        Date date_11 = new Date(10);
        Date date_12 = new Date(11);
        ExchangeRate exrate_1 = new ExchangeRate("AUD" ,"USD", 0.78, date_1);
        exrate_1.addNewRate(0.79, date_1);
        exrate_1.addNewRate(0.78, date_2);
        exrate_1.addNewRate(0.76, date_3);
        exrate_1.addNewRate(0.899, date_4);
        exrate_1.addNewRate(0.74, date_5);
        exrate_1.addNewRate(0.73, date_6);
        exrate_1.addNewRate(0.72, date_7);
        exrate_1.addNewRate(0.71, date_8);
        exrate_1.addNewRate(0.70, date_9);
        exrate_1.addNewRate(0.69, date_10);
        exrate_1.addNewRate(0.66, date_11);
        exrate_1.addNewRate(0.679, date_12);
        assert (exrate_1.getGlobalReocordMax().equals(0.899)) : "getGlobalReocordMax Test failed";

    }



    @Test
    public void getGlobalReocordMinTest_1(){
        Date date_1 = new Date(0);
        Date date_2 = new Date(1);
        Date date_3 = new Date(2);
        Date date_4 = new Date(3);
        Date date_5 = new Date(4);
        Date date_6 = new Date(5);
        Date date_7 = new Date(6);
        Date date_8 = new Date(7);
        Date date_9 = new Date(8);
        Date date_10 = new Date(9);
        Date date_11 = new Date(10);
        Date date_12 = new Date(11);
        ExchangeRate exrate_1 = new ExchangeRate("AUD" ,"USD", 0.78, date_1);
        exrate_1.addNewRate(0.79, date_1);
        exrate_1.addNewRate(0.78, date_2);
        exrate_1.addNewRate(0.76, date_3);
        exrate_1.addNewRate(0.599, date_4);
        exrate_1.addNewRate(0.74, date_5);
        exrate_1.addNewRate(0.73, date_6);
        exrate_1.addNewRate(0.72, date_7);
        exrate_1.addNewRate(0.71, date_8);
        exrate_1.addNewRate(0.70, date_9);
        exrate_1.addNewRate(0.69, date_10);
        exrate_1.addNewRate(0.66, date_11);
        exrate_1.addNewRate(0.679, date_12);
        assert (exrate_1.getGlobalReocordMin().equals(0.599)) : "getGlobalReocordMin Test failed";

    }



    @Test
    public void toStringTest_1() {
        Date date_1 = new Date(0);
        Date date_2 = new Date(1);
        Date date_3 = new Date(2);
        ExchangeRate exrate_1 = new ExchangeRate("AUD","USD",1.0,date_1);
        exrate_1.addNewRate(0.7, date_2);
        exrate_1.addNewRate(0.8, date_3);
        ExchangeRate exrate_2 = new ExchangeRate("CNY","AUD",2.0,date_1);
        assert (!exrate_1.toString().equals("0.8↑")) : "toString test exrate_1 failed";
//        assert ( !exrate_2.toString().equals("2.0=")) : "toString test exrate_2 failed";
        exrate_1.addNewRate(0.79, date);
        assert ( !exchangeRate.toString().equals("0.8↓")) : "toString test exrate_1 failed";
    }
}