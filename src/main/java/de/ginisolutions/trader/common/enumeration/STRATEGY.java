package de.ginisolutions.trader.common.enumeration;

public enum STRATEGY {
    ADX(""),
    CCI(""),
    GE(""),
    MACD_RSI(""),
    MM(""),
    RSI("");

    private final String description;

    STRATEGY(String description) {
        this.description = description;
    }
}
