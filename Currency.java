import java.util.Map;

public class Currency {
    private CurrencyType currentCurrency;
    private static Map<CurrencyType, Double> ratesToUSD = Map.of(CurrencyType.USD, 1.0, CurrencyType.EURO, 1.08, CurrencyType.CNY, 0.14);

    public double getRatetoUSD(CurrencyType currency) {
        return ratesToUSD.get(currency); 
    }

    public void setRatetoUSD(CurrencyType currency, double rate) {
        ratesToUSD.put(currency, rate);
    }

    public double convert(CurrencyType ToCurrency, double amount) {
        if (currentCurrency == CurrencyType.USD) {
            return amount * ratesToUSD.get(ToCurrency);
        } else {
            //convert to usd first, then convert to desired type
            double amtInUSD = ratesToUSD.get(currentCurrency) * amount;
            return amtInUSD/ratesToUSD.get(ToCurrency);
        }
    }
}
