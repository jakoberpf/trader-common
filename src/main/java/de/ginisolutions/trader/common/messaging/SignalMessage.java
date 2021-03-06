package de.ginisolutions.trader.common.messaging;

import de.ginisolutions.trader.common.enumeration.INTERVAL;
import de.ginisolutions.trader.common.enumeration.MARKET;
import de.ginisolutions.trader.common.enumeration.SYMBOL;
import de.ginisolutions.trader.common.enumeration.SIGNAL;
import de.ginisolutions.trader.common.enumeration.STRATEGY;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class SignalMessage {

    @NotNull
    private final LocalDateTime timestamp;

    @NotNull
    private final SIGNAL signal;

    @NotNull
    private final MARKET market;

    @NotNull
    private final SYMBOL symbol;

    @NotNull
    private final INTERVAL interval;

    @NotNull
    private final STRATEGY strategy;

    public SignalMessage(@NotNull SIGNAL signal, @NotNull MARKET market, @NotNull SYMBOL symbol, @NotNull INTERVAL interval, @NotNull STRATEGY strategy) {
        this.timestamp = LocalDateTime.now();
        this.signal = signal;
        this.market = market;
        this.symbol = symbol;
        this.interval = interval;
        this.strategy = strategy;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public SIGNAL getSignal() {
        return signal;
    }

    public MARKET getMarket() {
        return market;
    }

    public SYMBOL getSymbol() {
        return symbol;
    }

    public INTERVAL getInterval() {
        return interval;
    }

    public STRATEGY getStrategy() {
        return strategy;
    }

    @Override
    public String toString() {
        return "SignalMessage{" +
            "timestamp=" + timestamp +
            ", signal=" + signal +
            ", market=" + market +
            ", symbol=" + symbol +
            ", interval=" + interval +
            ", strategy=" + strategy +
            '}';
    }
}
