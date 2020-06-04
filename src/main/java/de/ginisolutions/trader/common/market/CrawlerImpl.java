package de.ginisolutions.trader.common.market;

import de.ginisolutions.trader.common.messaging.BaseListener;

public interface CrawlerImpl extends Runnable {
    void run();

    void subscribe(BaseListener listener);
}
