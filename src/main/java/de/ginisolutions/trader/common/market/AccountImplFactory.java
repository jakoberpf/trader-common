package de.ginisolutions.trader.common.market;

import de.ginisolutions.trader.common.market.account.BinanceAccount;
import de.ginisolutions.trader.common.enumeration.MARKET;
import org.apache.commons.lang3.NotImplementedException;

public class AccountImplFactory {
    public static AccountImpl buildAccount(MARKET market, String key, String secret) {
        switch (market) {
            case BINANCE:
                return new BinanceAccount(key, secret);
            case CRYPTO:
                throw new NotImplementedException("Not implemented " + market.getName());
            case COMDIRECT:
                throw new NotImplementedException("Not implemented " + market.getName());
            default:
                throw new IllegalArgumentException("Illegal input in Exchange Factory -> " + market.getName());
        }
    }
}
