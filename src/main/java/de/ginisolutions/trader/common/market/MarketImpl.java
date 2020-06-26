package de.ginisolutions.trader.common.market;

import de.ginisolutions.trader.common.model.tick.CommonTick;
import de.ginisolutions.trader.common.enumeration.INTERVAL;
import de.ginisolutions.trader.common.enumeration.SYMBOL;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface MarketImpl {
    List<CommonTick> getCandlesticks(@NotNull SYMBOL symbol, @NotNull INTERVAL interval);
}
