package Currency;
import Currency.UI.Rate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;
import java.util.*;
public class RateTest {

    Rate rate = new Rate("10/10/2020","1.0");
    @Test
    public void getDateTest() {
        assertEquals(rate.getDate(),"10/10/2020");
    }

    @Test
    public void getRateTest() {
        assertEquals(rate.getRate(),"1.0");
    }
}
