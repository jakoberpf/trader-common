package de.ginisolutions.trader.common.market.utils;

import com.binance.api.client.domain.market.Candlestick;
import de.ginisolutions.trader.common.model.tick.CommonTick;
import de.ginisolutions.trader.common.enumeration.INTERVAL;
import de.ginisolutions.trader.common.enumeration.MARKET;
import de.ginisolutions.trader.common.enumeration.SYMBOL;

import javax.validation.constraints.NotNull;

public class TickConverter {

    /**
     * Converts Candlestick (Binance) to TickDTO
     *
     * @param candlestick
     * @return
     */
    public static CommonTick entity2dto(@NotNull Candlestick candlestick, @NotNull MARKET market, @NotNull SYMBOL symbol, @NotNull INTERVAL interval, @NotNull boolean isFinal) {
        return new CommonTick(
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
