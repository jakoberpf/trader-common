package de.ginisolutions.trader.common.market;

import de.ginisolutions.trader.history.domain.TickPackage;
import de.ginisolutions.trader.history.domain.enumeration.INTERVAL;
import de.ginisolutions.trader.history.domain.enumeration.SYMBOL;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface MarketImpl {
    List<TickPackage> getCandlesticks(@NotNull SYMBOL symbol, @NotNull INTERVAL interval);
}
