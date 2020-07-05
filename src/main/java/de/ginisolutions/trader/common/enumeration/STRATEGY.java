package de.ginisolutions.trader.common.enumeration;

public enum STRATEGY {
    ADX(""),
    BBB(""),
    BBB_MACD(""),
    CCIC(""),
    GE(""),
    MACD_RSI(""),
    MM(""),
    RSI("");

    private final String description;

    STRATEGY(String description) {
        this.description = description;
    }
}
