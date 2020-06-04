package de.ginisolutions.trader.common.market.account;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.Trade;
import de.ginisolutions.trader.common.market.AccountImpl;
import de.ginisolutions.trader.history.domain.enumeration.MARKET;
import de.ginisolutions.trader.history.domain.enumeration.SYMBOL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.binance.api.client.domain.account.NewOrder.marketBuy;
import static com.binance.api.client.domain.account.NewOrder.marketSell;
import static de.ginisolutions.trader.history.domain.enumeration.MARKET.BINANCE;

/**
 * TODO
 */
public class BinanceAccount implements AccountImpl {

    private static final Logger logger = LoggerFactory.getLogger(BinanceAccount.class);
    private static final MARKET exchange = BINANCE;

    private final BinanceApiRestClient client;

    public BinanceAccount(String key, String secret) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(key, secret);
        this.client = factory.newRestClient();

//        // Get withdraw history
//        System.out.println(client.getWithdrawHistory("ETH"));
//
//        // Get deposit history
//        System.out.println(client.getDepositHistory("ETH"));
//
//        // Get deposit address
//        System.out.println(client.getDepositAddress("ETH"));
//
//        // Withdraw
//        client.withdraw("ETH", "0x123", "0.1", null, null);
//
//        // Getting list of open orders
//        List<Order> openOrders = client.getOpenOrders(new OrderRequest("LINKETH"));
//        System.out.println(openOrders);
//
//        // Getting list of all orders with a limit of 10
//        List<Order> allOrders = client.getAllOrders(new AllOrdersRequest("LINKETH").limit(10));
//        System.out.println(allOrders);
//
//        // Get status of a particular order
//        Order order = client.getOrderStatus(new OrderStatusRequest("LINKETH", 751698L));
//        System.out.println(order);
//
//        // Canceling an order
//        try {
//            CancelOrderResponse cancelOrderResponse = client.cancelOrder(new CancelOrderRequest("LINKETH", 756762l));
//            System.out.println(cancelOrderResponse);
//        } catch (BinanceApiException e) {
//            System.out.println(e.getError().getMsg());
//        }
//
//        // Placing a test LIMIT order
//        client.newOrderTest(limitBuy("LINKETH", TimeInForce.GTC, "1000", "0.0001"));
//
//        // Placing a real LIMIT order
//        NewOrderResponse newOrderResponse = client.newOrder(limitBuy("LINKETH", TimeInForce.GTC, "1000", "0.0001").newOrderRespType(NewOrderResponseType.FULL));
//        System.out.println(newOrderResponse);
    }

//    public void getTrades(MarketEnum marketEnum) {
//        // Get list of trades
//        List<Trade> myTrades = client.getMyTrades(marketEnum.getBinanceID());
//        System.out.println(myTrades);
//    }
//
//    public AccountBalance getBalance() {
//        // Get account balances
//        Account account = client.getAccount(60_000L, System.currentTimeMillis());
//        final AccountBalance accountBalance = new AccountBalance(Binance, 0.0);
//        account.getBalances().forEach(balance -> {
//            if (CurrencyEnum.byCode(balance.getAsset()) != null)
//                accountBalance.getBalances().put(
//                        CurrencyEnum.byCode(balance.getAsset()),
//                        new AccountAsset(
//                                CurrencyEnum.byCode(balance.getAsset()),
//                                Double.parseDouble(balance.getFree()),
//                                Double.parseDouble(balance.getLocked())
//                        )
//                );
//        });
//        return accountBalance;
//    }

    /**
     * TODO
     * @param market
     * @param amount
     * @param buyOrSell
     */
    public void makeOrder(SYMBOL market, double amount, String buyOrSell) {
        if (buyOrSell.equals("buy")) {
            client.newOrder(marketBuy(market.getNameLower(), String.valueOf(amount)));
        } else if (buyOrSell.equals("sell")) {
            client.newOrder(marketSell(market.getNameLower(), String.valueOf(amount)));
        } else {
            throw new IllegalArgumentException("Argument " + buyOrSell + " is not 'buy' or 'sell'");
        }
    }
}
