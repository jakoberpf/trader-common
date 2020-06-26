package de.ginisolutions.trader.common.market;

import de.ginisolutions.trader.common.market.market.BinanceMarket;
import de.ginisolutions.trader.common.enumeration.MARKET;
import org.apache.commons.lang3.NotImplementedException;

public class MarketImplFactory {
    public static MarketImpl buildApi(MARKET market) {
        switch (market) {
            case BINANCE:
                return new BinanceMarket();
            case CRYPTO:
                throw new NotImplementedException("Not implemented " + market.getName());
            case COMDIRECT:
                throw new NotImplementedException("Not implemented " + market.getName());
            default:
                throw new IllegalArgumentException("Illegal input in Exchange Factory -> " + market.getName());
        }
    }
}
