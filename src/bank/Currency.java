package src.bank;

import java.util.HashMap;
import java.util.Map;

public class Currency {
    private CurrencyType currentCurrency;
    private static Map<String, CurrencyType> convertToCurrencyType = getconvertToCurrencyType();
    private static Map<CurrencyType, Double> ratesToUSD = get_ratesToUSD();


    
    public static Map<CurrencyType, Double> get_ratesToUSD() {
        Map<CurrencyType, Double> ret = new HashMap<>();
        ret.put(CurrencyType.USD, 1.0);
        ret.put(CurrencyType.EURO, 1.08);
        ret.put(CurrencyType.CNY, 0.14);
        return ret;
    }


    public static Map<String, CurrencyType> getconvertToCurrencyType() {
        Map<String, CurrencyType>  ret = new HashMap<>();
        ret.put("USD", CurrencyType.USD);
        ret.put("EURO", CurrencyType.EURO);
        ret.put("CNY", CurrencyType.CNY);
        return ret;
    }


    public Currency(String currrency) {
        this.currentCurrency = convertToCurrencyType.get(currrency.toUpperCase());
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
