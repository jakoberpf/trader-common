package de.ginisolutions.trader.common.messaging;

import de.ginisolutions.trader.history.domain.TickPackage;

/**
 * The TickListener handles all the incoming messages from the crawlers and contains the logic of inserting them into
 * the repository. He will also put out the alert to the trader to update strategy.
 */
public interface TickListener extends BaseListener {
    void handleTick(TickPackage tickPackage);
}
