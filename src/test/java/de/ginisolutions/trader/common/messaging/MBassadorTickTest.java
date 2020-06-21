package de.ginisolutions.trader.common.messaging;

import de.ginisolutions.trader.history.domain.TickPackage;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.listener.Handler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static de.ginisolutions.trader.history.domain.enumeration.INTERVAL.ONE_MINUTE;
import static de.ginisolutions.trader.history.domain.enumeration.MARKET.BINANCE;
import static de.ginisolutions.trader.history.domain.enumeration.SYMBOL.BTCUSDT;
import static org.junit.jupiter.api.Assertions.*;

public class MBassadorTickTest {

    private final MBassador<TickPackage> dispatcher = new MBassador<>();

    private static final TickPackage tickSend = new TickPackage(BINANCE, BTCUSDT, ONE_MINUTE, 1591529802383L, 1591529760000L, 1591529819999L, 9596.08, 9600.02, 9602.17, 9596.08, 10.992936, false);

    private TickPackage tickReceived;

    @BeforeEach
    public void prepareTests() {
        dispatcher.subscribe(this);
    }

    @Test
    public void whenTickDispatched_thenHandleTick() {
        assertNull(tickReceived);

        dispatcher.post(tickSend).now();

        assertNotNull(tickReceived);
        assertEquals(tickSend, tickReceived);

        System.out.println(tickReceived.toString());
    }

    @Handler
    public void handleTick(TickPackage tickPackage) {
        tickReceived = tickPackage;
    }

}
