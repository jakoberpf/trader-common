package de.ginisolutions.trader.common.market;

import de.ginisolutions.trader.common.market.crawler.BinanceCrawler;

import de.ginisolutions.trader.common.messaging.BaseListener;
import de.ginisolutions.trader.common.messaging.TickListener;
import de.ginisolutions.trader.history.domain.enumeration.INTERVAL;
import de.ginisolutions.trader.history.domain.enumeration.MARKET;
import de.ginisolutions.trader.history.domain.enumeration.SYMBOL;
import org.apache.commons.lang3.NotImplementedException;

public class CrawlerImplFactory {
    public static CrawlerImpl buildCrawler(MARKET market, SYMBOL symbol, INTERVAL interval, TickListener listener) {
        switch (market) {
            case BINANCE:
                return new BinanceCrawler(symbol, interval, listener);
            case CRYPTO:
                throw new NotImplementedException("Not implemented " + market.getName());
            case COMDIRECT:
                throw new NotImplementedException("Not implemented " + market.getName());
            default:
                throw new IllegalArgumentException("Illegal input in Exchange Factory -> " + market.getName());
        }
    }
}
