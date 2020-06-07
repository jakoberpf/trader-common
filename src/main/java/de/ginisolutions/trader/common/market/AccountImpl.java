package de.ginisolutions.trader.common.market;

import de.ginisolutions.trader.history.domain.enumeration.SYMBOL;

public interface AccountImpl {
    void getTrades(SYMBOL marketEnum);

//    AccountBalance getBalance();

    void makeOrder(SYMBOL symbol, double amount, String buyOrSell);
}
