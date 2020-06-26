package de.ginisolutions.trader.common.enumeration;

import static de.ginisolutions.trader.common.enumeration.CURRENCY.*;
import static de.ginisolutions.trader.common.enumeration.CURRENCY.LTC;

/**
 * The Symbol enumeration.
 */
public enum SYMBOL {
    /**
     * Crypto Symbols
     */
    BTCUSDT("btcusdt", "BTCUSDT", BTC, USDT, ""),
    BTCEUR("btceur", "BTCEUR", BTC, EUR, ""),
    LTCUSDT("ltcusdt", "LTCUSDT", LTC, USDT, ""),
    ETHUSDT("ethusdt", "ETHUSDT", ETH, USDT, "");
    /**
     * Stock Symbols
     */

    // TODO Implement real currency symbols

    private final String nameLower;

    private final String nameUpper;

    private final CURRENCY asset;

    private final CURRENCY base;

    private final String description;

    SYMBOL(String nameLower, String nameUpper, CURRENCY asset, CURRENCY base, String description) {
        this.nameLower = nameLower;
        this.nameUpper = nameUpper;
        this.asset = asset;
        this.base = base;
        this.description = description;
    }

    public String getNameLower() {
        return nameLower;
    }

    public String getNameUpper() {
        return nameUpper;
    }

    public CURRENCY getAsset() {
        return asset;
    }

    public CURRENCY getBase() {
        return base;
    }

    public String getDescription() {
        return description;
    }
}
