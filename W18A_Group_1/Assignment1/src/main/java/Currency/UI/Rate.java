package Currency.UI;

public class Rate{

    public String date;
    public String rate;

    public Rate(String date, String rate) {
        this.date = date;
        this.rate = rate;
    }
    public String getDate() {
        return date;
    }

    public String getRate() {
        return rate;
    }
}
