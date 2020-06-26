package de.ginisolutions.trader.common.market.crawler;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.CandlestickEvent;
import de.ginisolutions.trader.common.market.CrawlerImpl;
import de.ginisolutions.trader.common.messaging.TickListener;
import de.ginisolutions.trader.common.messaging.TickPublisher;
import de.ginisolutions.trader.common.model.tick.CommonTick;
import de.ginisolutions.trader.common.enumeration.INTERVAL;
import de.ginisolutions.trader.common.enumeration.MARKET;
import de.ginisolutions.trader.common.enumeration.SYMBOL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

import static de.ginisolutions.trader.common.enumeration.MARKET.BINANCE;

/**
 * TODO
 */
public class BinanceCrawler implements CrawlerImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(BinanceCrawler.class);

    private static final BinanceApiWebSocketClient webSocket = BinanceApiClientFactory.newInstance().newWebSocketClient();

    private static final MARKET market = BINANCE;

    private final SYMBOL symbol;

    private final INTERVAL interval;

    private final TickPublisher publisher;

    private Closeable closeable;

    public BinanceCrawler(SYMBOL symbol, INTERVAL interval, TickListener listener) {
        this.symbol = symbol;
        this.interval = interval;
        this.publisher = new TickPublisher();
        this.publisher.subscribe(listener);
    }

    /**
     * TODO
     */
    @Override
    public void run() {
        LOGGER.debug("Subscribing to {} {} {}", market, this.symbol, this.interval);
        // Obtain candlesticks in real-time
        this.closeable = webSocket.onCandlestickEvent(symbol.getNameLower(), interval.getBinanceInterval(), (CandlestickEvent event) -> {
            LOGGER.debug("New Tick {}", event.toString());
            final CommonTick commonTick = new CommonTick(
                    market,
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
            this.publisher.publishTick(commonTick);
            LOGGER.debug(commonTick.toString());
        });
    }

    @Override
    public void close() {
        try {
            this.closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param listener
     */
    @Override
    public void subscribe(TickListener listener) {
        LOGGER.debug("Subscribing new listener: {}", listener);
        this.publisher.subscribe(listener);
    }
}
