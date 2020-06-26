package de.ginisolutions.trader.common.market.crawler;

import de.ginisolutions.trader.common.messaging.TickListener;
import de.ginisolutions.trader.common.model.tick.CommonTick;
import de.ginisolutions.trader.common.enumeration.INTERVAL;
import de.ginisolutions.trader.common.enumeration.SYMBOL;
import net.engio.mbassy.listener.Handler;
import org.junit.jupiter.api.Test;

import static de.ginisolutions.trader.common.enumeration.INTERVAL.ONE_MINUTE;
import static de.ginisolutions.trader.common.enumeration.SYMBOL.BTCUSDT;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BinanceCrawlerTest implements TickListener {

    private static final SYMBOL symbol = BTCUSDT;

    private static final INTERVAL interval = ONE_MINUTE;

    private final BinanceCrawler crawler = new BinanceCrawler(symbol, interval, this);

    private CommonTick tickReceived;

    @Test
    public void whenTickDispatched_thenHandleTick() {
        assertNull(tickReceived);

        this.crawler.run();

        await().until(() -> tickReceived != null);

        System.out.println(tickReceived.toString());
    }

    @Handler
    public void handleTick(CommonTick commonTick) {
        this.tickReceived = commonTick;
    }
}
