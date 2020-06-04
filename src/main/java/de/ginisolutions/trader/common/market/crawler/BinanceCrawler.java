package de.ginisolutions.trader.common.market.crawler;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.CandlestickEvent;
import de.ginisolutions.trader.common.market.CrawlerImpl;
import de.ginisolutions.trader.common.messaging.BaseListener;
import de.ginisolutions.trader.common.messaging.TickListener;
import de.ginisolutions.trader.common.messaging.TickPublisher;
import de.ginisolutions.trader.history.domain.TickDTO;
import de.ginisolutions.trader.history.domain.enumeration.INTERVAL;
import de.ginisolutions.trader.history.domain.enumeration.SYMBOL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.ginisolutions.trader.history.domain.enumeration.MARKET.BINANCE;

/**
 * TODO
 */
public class BinanceCrawler implements CrawlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(BinanceCrawler.class);

    private static final BinanceApiWebSocketClient webSocket = BinanceApiClientFactory.newInstance().newWebSocketClient();

    private final SYMBOL symbol;

    private final INTERVAL interval;

    private final TickPublisher publisher;

    public BinanceCrawler(SYMBOL symbol, INTERVAL interval, BaseListener listener) {
        this.symbol = symbol;
        this.interval = interval;
        this.publisher = new TickPublisher((TickListener) listener);
    }

    /**
     * TODO
     */
    @Override
    public void run() {
        logger.debug("Subscribing to Binance.com " + this.symbol.getNameLower() + " with interval of millis " + interval.getInterval());
        // Obtain candlesticks in real-time
        webSocket.onCandlestickEvent(symbol.getNameLower(), interval.getBinanceInterval(), (CandlestickEvent event) -> {
            logger.debug(event.toString());
            final TickDTO tickDTO = new TickDTO(
                    BINANCE,
                    symbol,
                    interval,
                    event.getEventTime(),
                    event.getOpenTime(),
                    event.getCloseTime(),
                    Double.valueOf(event.getOpen()),
                    Double.valueOf(event.getClose()),
                    Double.valueOf(event.getHigh()),
                    Double.valueOf(event.getLow()),
                    Double.valueOf(event.getVolume()),
                    event.getBarFinal()
            );
            this.publisher.publishTick(tickDTO, false);
            logger.debug(tickDTO.toString());
        });
    }

    @Override
    public void subscribe(BaseListener listener) {
        this.publisher.subscribe((TickListener) listener);
    }
}
