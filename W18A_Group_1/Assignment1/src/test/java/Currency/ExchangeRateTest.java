
package Currency;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
class ExchangeRateTest {
    Date date = new Date();
    ExchangeRate exchangeRate = new ExchangeRate("AUD","USD",1.0,date);

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
        assertEquals(exchangeRate.equals(anotherExchangeRate),true);
        assertFalse(exchangeRate.equals(anotherExchangeRate1));

    }

    @Test
    public void decreasedFromLastRateTest() {

    }
}