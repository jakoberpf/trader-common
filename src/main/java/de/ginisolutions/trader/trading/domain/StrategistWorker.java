//package de.ginisolutions.trader.trading.domain;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.validation.constraints.NotNull;
//import java.io.*;
//import java.text.DecimalFormat;
//import java.time.Duration;
//import java.time.Instant;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//
///**
// *
// */
//public class Strategist implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    private static final Logger logger = LoggerFactory.getLogger(Strategist.class);
//
//    @NotNull
//    private final Strategy strategy;
//    @NotNull
//    private final TradingRecord tradingRecord;
//    @NotNull
//    private final BarSeries barSeries;
//    @NotNull
//    private final TraderAttributes attributes;
//    @NotNull
//    private final AccountImpl account;
//
//    public Trader(Strategy strategy, TradingRecord tradingRecord, BarSeries barSeries, TraderAttributes attributes, String key, String secret) {
//        this.strategy = strategy;
//        this.tradingRecord = tradingRecord;
//        this.barSeries = barSeries;
//        this.attributes = attributes;
//        this.account = AccountImplFactory.buildAccount(this.attributes.getExchangeEnum(), key, secret);
//    }
//
//    public void onNewTick(TraderTick event) {
//        if (event.getTick().getExchange() == this.attributes.getExchangeEnum() && event.getTick().getMarket() == this.attributes.getMarketEnum() && event.getTick().getInterval() == this.attributes.getIntervalEnum()) {
//            if (ZonedDateTime.ofInstant(Instant.ofEpochMilli(event.getTick().getOpenTime()), ZoneId.systemDefault()).isAfter(this.barSeries.getLastBar().getEndTime())) {
//                logger.debug("Published tick is new to me");
//                this.barSeries.addBar(
//                        Duration.ofMillis(event.getTick().getInterval().getMillis() - 1),
//                        ZonedDateTime.ofInstant(Instant.ofEpochMilli(event.getTick().getCloseTime()), ZoneId.systemDefault()),
//                        event.getTick().getOpen(),
//                        event.getTick().getHigh(),
//                        event.getTick().getLow(),
//                        event.getTick().getClose(),
//                        event.getTick().getVolume()
//                );
//                logger.info("Added new tick to bar series -> " + event.getTick().toString());
//                logger.info(this.attributes.getExchangeEnum() + " on " + this.attributes.getMarketEnum() + " in " + this.attributes.getIntervalEnum().getMillis() + " milliseconds " + "added new bar, with close price = " + this.barSeries.getLastBar().getClosePrice().doubleValue());
//                this.decide();
//            } else {
//                logger.info("Published tick is not new to me");
//            }
//        }
//    }
//
//    public void onNewTack(TraderTack event) {
//        // TODO update last bar
//    }
//
//    public void onTimelineUpdate(TimelineUpdate event) {
//
//    }
//
//    public void decide() {
//        logger.info("Decision process...");
//        final int endIndexTest = this.barSeries.getEndIndex();
//        if (this.attributes.isLive()) {
//            final Bar newBar = this.barSeries.getLastBar();
//            final int endIndex = this.barSeries.getEndIndex();
//            final AccountAsset asset = this.account.getBalance().getBalances().get(this.attributes.getMarketEnum().getMarketAsset());
//            final AccountAsset funds = this.account.getBalance().getBalances().get(this.attributes.getMarketEnum().getMarketBase());
//            if (strategy.shouldEnter(endIndex)) {
//                // Our strategy should enter
//                logger.info("...Strategy should ENTER on " + endIndex);
//                // Real Account Info
//                logger.info("Real: Investments are " + asset.getFree() + " free and " + asset.getLocked() + " locked.");
//                logger.info("Real: Funds are " + funds.getFree() + " free and " + funds.getLocked() + " locked.");
//                if (funds.getFree() <= 10.0) {
//                    logger.info("No funds to enter with");
//                } else {
//                    // Real Trade
//                    logger.info("Will enter market, buying " + this.attributes.getBudget() + " " + asset.getId());
//                    this.account.makeOrder(this.attributes.getMarketEnum(), filterAmount(this.attributes.getBudget()), "buy");
//                    // Virtual Trading Record
//                    tradingRecord.enter(endIndex, newBar.getClosePrice(), PrecisionNum.valueOf(filterAmount(this.attributes.getBudget())));
//                }
//            } else if (strategy.shouldExit(endIndex)) {
//                // Our strategy should exit
//                logger.info("...Strategy should EXIT on " + endIndex);
//                // Real Account Info
//                logger.info("Real: Investments are " + asset.getFree() + " free and " + asset.getLocked() + " locked.");
//                logger.info("Real: Funds are " + funds.getFree() + " free and " + funds.getLocked() + " locked.");
//                if (asset.getFree() <= 0.0005) {
//                    logger.info("No asset to exit with");
//                } else {
//                    // Real Trade
//                    logger.info("Will exit market, selling " + asset.getFree() + " " + asset.getId());
//                    this.account.makeOrder(this.attributes.getMarketEnum(), this.attributes.getBudget(), "sell");
//                    // Virtual Trading Record
//                    tradingRecord.exit(endIndex, newBar.getClosePrice(), PrecisionNum.valueOf(filterAmount(asset.getFree())));
//                }
//            } else {
//                logger.info("...Strategy should HOLD on " + endIndex);
//            }
//        }
//    }
//
//    public static double filterAmount(double value) {
//        // TODO check with filters from market, like min/max or step
//        DecimalFormat df = new DecimalFormat("#.#######");
//        return Double.parseDouble(df.format(value).replace(",", "."));
//    }
//
//    /**
//     * Convert a Trader object into stream of bytes.
//     *
//     * @param trader Trader object.
//     * @return stream of bytes
//     */
//    public static byte[] buildStream(Trader trader) {
//        // Reference for stream of bytes
//        byte[] stream = null;
//        // ObjectOutputStream is used to convert a Java object into OutputStream
//        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
//             ObjectOutputStream oos = new ObjectOutputStream(baos);) {
//            oos.writeObject(trader);
//            stream = baos.toByteArray();
//        } catch (IOException e) {
//            // Error in serialization
//            e.printStackTrace();
//        }
//        return stream;
//    }
//
//    /**
//     * Convert stream of bytes to Trader.
//     *
//     * @param stream byte array
//     * @return Trader object
//     */
//    public static Trader buildTrader(byte[] stream) {
//        Trader trader = null;
//        try (ByteArrayInputStream bais = new ByteArrayInputStream(stream);
//             ObjectInputStream ois = new ObjectInputStream(bais);) {
//            trader = (Trader) ois.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            // Error in de-serialization
//            e.printStackTrace();
//        }
//        return trader;
//    }
//}
//
//
//}
