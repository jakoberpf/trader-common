package de.ginisolutions.trader.history.broker;

import de.ginisolutions.trader.history.domain.TickDTO;
import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Listener;
import net.engio.mbassy.listener.References;

/**
 * The TickTackListener handles all the incoming messages from the crawlers and contains the logic of inserting them into
 * the repository. He will also put out the alert to the trader to update strategies.
 */
@Listener(references = References.Strong)
public class TickListener {

    /**
     * TODO
     * @param tickDTO
     */
//    @Handler(delivery = Invoke.Asynchronously)
    @Handler()
    public void expensiveOperation(TickDTO tickDTO) {
        // TODO handle tack accordingly
    }

    /**
     * TODO
     * @param tick
     */
    private void publishTickEvent(TickDTO tick) {
        // TODO publish new "final" tick event to the trading service
    }
}
