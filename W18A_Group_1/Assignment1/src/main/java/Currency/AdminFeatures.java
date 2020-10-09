package Currency;

import java.util.Date;

public interface AdminFeatures {
    void addNewRate(String currencyA, String currencyB, double rate, Date date);
    void displayCurrencies();
    void addCurrency(String currencyName, Date date);
    void setPopularCurrency(String Currency);
}
