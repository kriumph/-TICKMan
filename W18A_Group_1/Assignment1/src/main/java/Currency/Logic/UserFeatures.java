package Currency.Logic;

import java.util.ArrayList;

public interface UserFeatures{
    double exchangeCurrency(String fromCurrency, String toCurrency, double amount);
    ArrayList<String> getPopularCurrencies();
    ArrayList<ExchangeRate> getPopularRates();
}
