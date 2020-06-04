package de.ginisolutions.trader.trading.domain.enumeration;

public enum SIGNAL {
    ENTER("You should enter"),
    EXIT("You should exit"),
    HOLD("You should hold");

    private final String description;

    SIGNAL(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
