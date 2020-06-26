package de.ginisolutions.trader.common.messaging;

import de.ginisolutions.trader.common.enumeration.*;
import de.ginisolutions.trader.common.model.tick.CommonTick;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TickMessage {

    @NotNull
    private final LocalDateTime timestamp;

    @NotNull
    private final MARKET market;

    @NotNull
    private final SYMBOL symbol;

    @NotNull
    private final CommonTick commonTick;

    public TickMessage(@NotNull LocalDateTime timestamp, @NotNull MARKET market, @NotNull SYMBOL symbol, @NotNull CommonTick commonTick) {
        this.timestamp = timestamp;
        this.market = market;
        this.symbol = symbol;
        this.commonTick = commonTick;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public MARKET getMarket() {
        return market;
    }

    public SYMBOL getSymbol() {
        return symbol;
    }

    public CommonTick getCommonTick() {
        return commonTick;
    }

    @Override
    public String toString() {
        return "TickMessage{" +
                "timestamp=" + timestamp +
                ", market=" + market +
                ", symbol=" + symbol +
                ", tickPackage=" + commonTick +
                '}';
    }
}
