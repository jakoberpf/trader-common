package de.ginisolutions.trader.trading.domain;

import de.ginisolutions.trader.common.market.AccountImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * The TraderPackage entity contains the Trader entity and some simple business logic.
 * Is is instantiated for every Trader in the database at application startup and handles
 * the business logic of entering/exciting assets.
 */
public class TraderPackage implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(TraderPackage.class);

    private final Trader trader;

    private final AccountImpl accountImpl;

    public TraderPackage(Trader trader, AccountImpl accountImpl) {
        this.trader = trader;
        this.accountImpl = accountImpl;
    }
}
