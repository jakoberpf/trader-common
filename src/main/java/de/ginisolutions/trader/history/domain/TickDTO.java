package de.ginisolutions.trader.history.domain;

import de.ginisolutions.trader.history.domain.enumeration.INTERVAL;
import de.ginisolutions.trader.history.domain.enumeration.MARKET;
import de.ginisolutions.trader.history.domain.enumeration.SYMBOL;

import javax.validation.constraints.NotNull;

public class TickDTO {

    @NotNull
    private final MARKET market;
    @NotNull
    private final SYMBOL symbol;
    @NotNull
    private final INTERVAL interval;
    @NotNull
    private final Long eventTime; // Time of tick event publication
    @NotNull
    private final Long openTime;
    @NotNull
    private final Long closeTime;
    @NotNull
    private final Number open;
    @NotNull
    private final Number close;
    @NotNull
    private final Number high;
    @NotNull
    private final Number low;
    @NotNull
    private final Number volume;
    @NotNull
    private final boolean isFinal;

    public TickDTO(@NotNull MARKET market, @NotNull SYMBOL symbol, @NotNull INTERVAL interval, @NotNull Long eventTime, @NotNull Long openTime, @NotNull Long closeTime, @NotNull Number open, @NotNull Number close, @NotNull Number high, @NotNull Number low, @NotNull Number volume, @NotNull boolean isFinal) {
        this.market = market;
        this.symbol = symbol;
        this.interval = interval;
        this.eventTime = eventTime;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
        this.isFinal = isFinal;
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

    public Long getEventTime() {
        return eventTime;
    }

    public Long getOpenTime() {
        return openTime;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public Number getOpen() {
        return open;
    }

    public Number getClose() {
        return close;
    }

    public Number getHigh() {
        return high;
    }

    public Number getLow() {
        return low;
    }

    public Number getVolume() {
        return volume;
    }

    public boolean isFinal() {
        return isFinal;
    }

    @Override
    public String toString() {
        return "TickDTO{" +
                "market=" + market +
                ", symbol=" + symbol +
                ", interval=" + interval +
                ", eventTime=" + eventTime +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", volume=" + volume +
                ", isFinal=" + isFinal +
                '}';
    }
}

