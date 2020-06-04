package de.ginisolutions.trader.common.messaging;

import de.ginisolutions.trader.history.domain.Tick;

public class TickMessage {
    final Tick tick;

    public TickMessage(Tick tick) {
        this.tick = tick;
    }

    public Tick getTick() {
        return tick;
    }
}
