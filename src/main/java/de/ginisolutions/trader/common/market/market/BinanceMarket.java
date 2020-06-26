package de.ginisolutions.trader.common.market.market;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.general.*;
import com.binance.api.client.domain.market.Candlestick;
import de.ginisolutions.trader.common.market.MarketImpl;
import de.ginisolutions.trader.common.market.utils.TickConverter;
import de.ginisolutions.trader.common.model.tick.CommonTick;
import de.ginisolutions.trader.common.enumeration.INTERVAL;
import de.ginisolutions.trader.common.enumeration.MARKET;
import de.ginisolutions.trader.common.enumeration.SYMBOL;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static de.ginisolutions.trader.common.enumeration.MARKET.BINANCE;

/**
 *
 */
public class BinanceMarket implements MarketImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinanceMarket.class);

    private static final MARKET market = BINANCE;

    private final BinanceApiRestClient client;

    public BinanceMarket() {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
        this.client = factory.newRestClient();
    }

    /**
     *
     */
    public void getExchangeInfo(){
        // Exchange info
        ExchangeInfo exchangeInfo = client.getExchangeInfo();
        System.out.println(exchangeInfo.getTimezone());
        System.out.println(exchangeInfo.getSymbols());
    }

    /**
     *
     */
    public void getAssetInfo(){
        // Obtain symbol information
        ExchangeInfo exchangeInfo = client.getExchangeInfo();
        SymbolInfo symbolInfo = exchangeInfo.getSymbolInfo("BTCUSDT");
        System.out.println(symbolInfo.getStatus());
    }

    /**
     *
     */
    public void getFilters(){
        ExchangeInfo exchangeInfo = client.getExchangeInfo();
        SymbolInfo symbolInfo = exchangeInfo.getSymbolInfo("BTCUSDT");
        SymbolFilter priceFilter = symbolInfo.getSymbolFilter(FilterType.PRICE_FILTER);
        System.out.println(priceFilter.getMinPrice());
        System.out.println(priceFilter.getTickSize());
    }

    /**
     *
     */
    public void getAllAssets(){
        // Obtain asset information
        List<Asset> allAssets = client.getAllAssets();
        System.out.println(allAssets.stream().filter(asset -> asset.getAssetCode().equals("BNB")).findFirst().get());
    }

    /**
     *
     * @param symbol
     * @param interval
     * @return
     */
    @Override
    public List<CommonTick> getCandlesticks(@NotNull SYMBOL symbol, @NotNull INTERVAL interval) {
        List<Candlestick> candlesticks = client.getCandlestickBars(symbol.getNameUpper(), interval.getBinanceInterval());
        List<CommonTick> ticks = new ArrayList<>();
        for (Candlestick stick : candlesticks) {
            ticks.add(TickConverter.entity2dto(stick, BINANCE, symbol, interval, true));
        }
        return ticks;
    }
}
