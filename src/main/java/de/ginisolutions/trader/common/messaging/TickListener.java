package de.ginisolutions.trader.common.messaging;

import de.ginisolutions.trader.common.messaging.BaseListener;
import de.ginisolutions.trader.history.domain.TickDTO;
import net.engio.mbassy.listener.Handler;
import org.apache.commons.lang3.NotImplementedException;

/**
 * The TickListener handles all the incoming messages from the crawlers and contains the logic of inserting them into
 * the repository. He will also put out the alert to the trader to update strategy.
 */
public class TickListener implements BaseListener {

    /**
     * TODO
     * @param tickDTO
     */
//    @Handler(delivery = Invoke.Asynchronously)
    @Handler()
    public void expensiveOperation(TickDTO tickDTO) {
        // TODO handle tack accordingly
        this.publishTickEvent(tickDTO);
    }

    /**
     * TODO
     * @param tick
     */
    private void publishTickEvent(TickDTO tick) {
        // TODO publish new "final" tick event to the trading service
        throw new NotImplementedException("Publishing of Tick is not implemented yet!");
    }
}
