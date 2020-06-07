package de.ginisolutions.trader.common.market.crawler;

import de.ginisolutions.trader.common.messaging.TickListener;
import de.ginisolutions.trader.history.domain.TickDTO;
import de.ginisolutions.trader.history.domain.enumeration.INTERVAL;
import de.ginisolutions.trader.history.domain.enumeration.SYMBOL;
import net.engio.mbassy.listener.Handler;
import org.junit.jupiter.api.Test;

import static de.ginisolutions.trader.history.domain.enumeration.INTERVAL.ONE_MINUTE;
import static de.ginisolutions.trader.history.domain.enumeration.SYMBOL.BTCUSDT;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BinanceCrawlerTest implements TickListener {

    private static final SYMBOL symbol = BTCUSDT;

    private static final INTERVAL interval = ONE_MINUTE;

    private final BinanceCrawler crawler = new BinanceCrawler(symbol, interval, this);

    private TickDTO tickReceived;

    @Test
    public void whenTickDispatched_thenHandleTick() {
        assertNull(tickReceived);

        this.crawler.run();

        await().until(() -> tickReceived != null);

        System.out.println(tickReceived.toString());
    }

    @Handler
    public void handleTick(TickDTO tickDTO) {
        this.tickReceived = tickDTO;
    }
}
