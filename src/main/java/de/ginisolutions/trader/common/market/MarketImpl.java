package de.ginisolutions.trader.common.market;

import de.ginisolutions.trader.history.domain.Tick;
import de.ginisolutions.trader.history.domain.TickDTO;
import de.ginisolutions.trader.history.domain.enumeration.INTERVAL;
import de.ginisolutions.trader.history.domain.enumeration.MARKET;
import de.ginisolutions.trader.history.domain.enumeration.SYMBOL;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface MarketImpl {
    List<TickDTO> getCandlesticks(@NotNull SYMBOL symbol, @NotNull INTERVAL interval);
}
