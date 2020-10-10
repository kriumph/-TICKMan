package Currency;

import Currency.Logic.CurrencyConverter;
import Currency.Logic.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    CurrencyConverter cc = new CurrencyConverter("src/main/java/Currency/Logic/RateTxt.txt");
    User user = new User(cc);

    @Test
    public void exchangeCurrencyTest() {

        double result= user.exchangeCurrency("USD","CNY",10);
        double result1= user.exchangeCurrency("CNY","USD",10);
        assertEquals((double) Math.round(result * 100)/100, 67.91);
        assertEquals((double) Math.round(result1 * 100) / 100, 1.47);
    }

}
