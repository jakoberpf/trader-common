package de.ginisolutions.trader.common.messaging;

import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.listener.Handler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MBassadorTest {

    private MBassador<Object> dispatcher = new MBassador<>();

    private static final String messageSend = "TickDTO{market=BINANCE, symbol=BTCUSDT, interval=ONE_MINUTE, eventTime=1591529802383, openTime=1591529760000, closeTime=1591529819999, open=9596.08, close=9600.02, high=9602.17, low=9596.08, volume=10.992936, isFinal=false}";

    private String messageReceived;

    @BeforeEach
    public void prepareTests() {
        dispatcher.subscribe(this);
    }

    @Test
    public void whenStringDispatched_thenHandleString() {
        assertNull(messageReceived);

        dispatcher.post(messageSend).now();

        assertNotNull(messageReceived);
        assertEquals(messageSend, messageReceived);

        System.out.println(messageReceived);
    }

    @Handler
    public void handleString(String message) {
        messageReceived = message;
    }

}
