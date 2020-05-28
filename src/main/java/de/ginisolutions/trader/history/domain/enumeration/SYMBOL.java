package de.ginisolutions.trader.history.domain.enumeration;

import java.util.Currency;

import static de.ginisolutions.trader.history.domain.enumeration.CURRENCY.*;
import static de.ginisolutions.trader.history.domain.enumeration.CURRENCY.LTC;

/**
 * The Symbol enumeration.
 */
public enum SYMBOL {
    SAMPLE_ENUM("sample", "SAMPLE", CURRENCY.SAMPLE_ENUM, CURRENCY.SAMPLE_ENUM, "sample description"),
    /**
     * Crypto Symbols
     */
    BTCUSDT("btcusdt", "BTCUSDT", BTC, USDT, ""),
    LTCUSDT("ltcusdt", "LTCUSDT", LTC, USDT, ""),
    ETHUSDT("ethusdt", "ETHUSDT", ETH, USDT, "");
    /**
     * Stock Symbols
     */
    // TODO Implement

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

    @Override
    public String toString() {
        return "SYMBOL{" +
                "nameLower='" + nameLower + '\'' +
                ", nameUpper='" + nameUpper + '\'' +
                ", asset=" + asset +
                ", base=" + base +
                ", description='" + description + '\'' +
                '}';
    }
}
