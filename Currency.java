import java.util.Map;

public class Currency {
    private CurrencyType currentCurrency;
    private static Map<CurrencyType, Double> ratesToUSD = Map.of(CurrencyType.USD, 1.0, CurrencyType.EURO, 1.08, CurrencyType.CNY, 0.14);

    public Currency(CurrencyType currrency) {
        this.currentCurrency = currrency;
    }

    public double getRatetoUSD(CurrencyType currency) {
        return ratesToUSD.get(currency); 
    }

    public void setRatetoUSD(CurrencyType currency, double rate) {
        ratesToUSD.put(currency, rate);
    }

    public double convert(Currency ToCurrency, double amount) {
        CurrencyType ToCurrencyType = ToCurrency.getCurrencyType();
        if (currentCurrency == CurrencyType.USD) {
            return amount * ratesToUSD.get(ToCurrencyType);
        } else {
            //convert to usd first, then convert to desired type
            double amtInUSD = ratesToUSD.get(currentCurrency) * amount;
            return amtInUSD/ratesToUSD.get(ToCurrencyType);
        }
    }

    public CurrencyType getCurrencyType() {
        return currentCurrency;
    }

    public String toString() {
        return currentCurrency.toString();
    }

}
