package de.ginisolutions.trader.common.messaging;

import de.ginisolutions.trader.common.model.tick.CommonTick;
import net.engio.mbassy.bus.IMessagePublication;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.error.PublicationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class TickPublisher implements BasePublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(TickPublisher.class);

    private final MBassador<CommonTick> bus;

    public TickPublisher() {
        LOGGER.info("Constructing TickPublisher");
        this.bus = new MBassador<>();
    }

    /**
     * This method subscribes the provided listener to the event bus
     * @param listener defines the listener to subscribe to the event bus
     */
    public void subscribe(TickListener listener) {
        this.bus.subscribe(listener);
    }

    /**
     * @param tick
     * @param async
     * @return
     */
    public IMessagePublication publishTick(CommonTick tick, boolean async) {
        if (async) {
            return this.bus.post(tick).asynchronously();
        } else {
            return this.publishTick(tick);
        }
    }

    /**
     * @param tick
     * @return
     */
    public IMessagePublication publishTick(CommonTick tick) {
        return this.bus.post(tick).now();
    }

    @Override
    public void handleError(PublicationError error) {
        LOGGER.error(error.getMessage());
    }
}
