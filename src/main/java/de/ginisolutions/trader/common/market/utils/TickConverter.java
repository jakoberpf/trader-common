package de.ginisolutions.trader.common.market.utils;

import com.binance.api.client.domain.market.Candlestick;
import de.ginisolutions.trader.history.domain.Tick;
import de.ginisolutions.trader.history.domain.TickPackage;
import de.ginisolutions.trader.history.domain.enumeration.INTERVAL;
import de.ginisolutions.trader.history.domain.enumeration.MARKET;
import de.ginisolutions.trader.history.domain.enumeration.SYMBOL;

import javax.validation.constraints.NotNull;

public class TickConverter {

    /**
     * Converts Candlestick (Binance) to Tick
     *
     * @param candlestick
     * @return
     */
    public static Tick dto2entity(@NotNull Candlestick candlestick) {
        return new Tick(
                candlestick.getOpenTime(),
                Double.valueOf(candlestick.getOpen()),
                Double.valueOf(candlestick.getClose()),
                Double.valueOf(candlestick.getHigh()),
                Double.valueOf(candlestick.getLow()),
                Double.valueOf(candlestick.getVolume()));
    }

    /**
     * Converts Candlestick (Binance) to TickDTO
     *
     * @param candlestick
     * @return
     */
    public static TickPackage entity2dto(@NotNull Candlestick candlestick, @NotNull MARKET market, @NotNull SYMBOL symbol, @NotNull INTERVAL interval, @NotNull boolean isFinal) {
        return new TickPackage(
                market, symbol, interval,
                candlestick.getOpenTime(),
                candlestick.getOpenTime(),
                candlestick.getCloseTime(),
                Double.valueOf(candlestick.getOpen()),
                Double.valueOf(candlestick.getClose()),
                Double.valueOf(candlestick.getHigh()),
                Double.valueOf(candlestick.getLow()),
                Double.valueOf(candlestick.getVolume()),
                isFinal);
    }
}
