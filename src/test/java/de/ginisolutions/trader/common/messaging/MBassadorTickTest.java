package de.ginisolutions.trader.common.messaging;

import de.ginisolutions.trader.history.domain.TickDTO;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.listener.Handler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static de.ginisolutions.trader.history.domain.enumeration.INTERVAL.ONE_MINUTE;
import static de.ginisolutions.trader.history.domain.enumeration.MARKET.BINANCE;
import static de.ginisolutions.trader.history.domain.enumeration.SYMBOL.BTCUSDT;
import static org.junit.jupiter.api.Assertions.*;

public class MBassadorTickTest {

    private final MBassador<TickDTO> dispatcher = new MBassador<>();

    private static final TickDTO tickSend = new TickDTO(BINANCE, BTCUSDT, ONE_MINUTE, 1591529802383L, 1591529760000L, 1591529819999L, 9596.08, 9600.02, 9602.17, 9596.08, 10.992936, false);

    private TickDTO tickReceived;

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
    public void handleTick(TickDTO tickDTO) {
        tickReceived = tickDTO;
    }

}
