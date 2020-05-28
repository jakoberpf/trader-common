package de.ginisolutions.trader.history.broker;

import de.ginisolutions.trader.history.domain.Tick;
import de.ginisolutions.trader.history.domain.TickDTO;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.config.IBusConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class TickPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(TickPublisher.class);

    private final MBassador<TickDTO> bus;

    /**
     * @param listener
     */
    public TickPublisher(TickListener listener) {
        LOGGER.info("Constructing TickPublisher");
        IBusConfiguration configuration;
        this.bus = new MBassador<>();
        this.bus.subscribe(listener);
    }

    /**
     * @param tick
     * @param aync
     * @return
     */
    public boolean publishTick(TickDTO tick, boolean aync) {
        if (aync) {
            this.bus.post(tick).asynchronously();
        } else {
            this.publishTick(tick);
        }

        return true;
    }

    /**
     * @param tick
     * @return
     */
    public boolean publishTick(TickDTO tick) {
        this.bus.post(tick).now();
        return true;
    }
}
