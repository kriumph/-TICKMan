package Currency;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    CurrencyConverter cc = new CurrencyConverter("RateTxt.txt");
    User user = new User(cc);

    @Test
    public void exchangeCurrencyTest() {
        //assertEquals(user.exchangeCurrency("USD","CNY",10), 67.91);

    }
}