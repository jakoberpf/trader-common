package de.ginisolutions.trader.common.market;

import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.account.Trade;
import de.ginisolutions.trader.common.enumeration.SYMBOL;
import de.ginisolutions.trader.common.enumeration.ORDER;

import java.util.List;

public interface AccountImpl {
    List<Trade> getTrades(SYMBOL marketEnum);

    Account getBalance();

    NewOrderResponse makeOrder(SYMBOL symbol, double amount, ORDER order);

    NewOrderResponse makeOrder(SYMBOL symbol, double amount, ORDER order, boolean isTest);
}
