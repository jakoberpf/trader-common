package de.ginisolutions.trader.common.market.account;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static de.ginisolutions.trader.common.enumeration.SYMBOL.BTCUSDT;
import static de.ginisolutions.trader.common.enumeration.ORDER.BUY;
import static de.ginisolutions.trader.common.enumeration.ORDER.SELL;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BinanceAccountTest {

    private static final String apiKey = "TnEYzUjKLkrRqHd8XcRcgbalM68acP7ldlpK0vTd7TEsjVBUUMtpU5ExgJHi2m92 ";

    private static final String apiSecret = "r7gGje4BXoBi1pRCBOpp1AGOUCkosbmywblWicGyJi4J5BhMwJWbTYCDRA4U2GuM";

    private static BinanceAccount account;

    @BeforeAll
    static void setUp() {
        account = new BinanceAccount(apiKey, apiSecret);
        assertNotNull(account);
    }

    @Test
    public void makeBuyOrder() {
        account.makeOrder(BTCUSDT, 0.005, BUY, true);
    }

    @Test
    public void makeSellOrder() {
        account.makeOrder(BTCUSDT, 0.005, SELL, true);
    }

}
