package de.ginisolutions.trader.common.market;

import de.ginisolutions.trader.common.messaging.TickListener;

public interface CrawlerImpl extends Runnable {
    void run();

    void close();

    void subscribe(TickListener listener);
}
