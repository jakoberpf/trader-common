package de.ginisolutions.trader.common.strategy.impl;

import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.trading.rules.CrossedDownIndicatorRule;
import org.ta4j.core.trading.rules.CrossedUpIndicatorRule;
import org.ta4j.core.trading.rules.OverIndicatorRule;
import org.ta4j.core.trading.rules.UnderIndicatorRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.ginisolutions.trader.common.strategy.util.StrategyUtilities.getRange;

/**
 * MACD and RSI indicator based strategy.
 *
 * @see <a href=
 * "https://www.cryptohopper.com/blog/173-trading-101-combining-technical-indicators">
 * https://www.cryptohopper.com/blog/173-trading-101-combining-technical-indicators</a>
 */
public class MACD_RSI_Strategy {

    /**
     * @param barSeries
     * @param parameterMap
     * @return
     */
    public static Strategy buildStrategy(BarSeries barSeries, Map<String, Double> parameterMap) {

        if (barSeries == null) {
            throw new IllegalArgumentException("Series cannot be null");
        }

        ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(barSeries);
        SMAIndicator SMA_short = new SMAIndicator(closePriceIndicator, parameterMap.get("SMA_short").intValue());
        SMAIndicator SMA_long = new SMAIndicator(closePriceIndicator, parameterMap.get("SMA_long").intValue());

        // We use a 2-period RSI indicator to identify buying
        // or selling opportunities within the bigger trend.
        RSIIndicator RSI = new RSIIndicator(closePriceIndicator, parameterMap.get("RSI").intValue());

        //
        MACDIndicator MACD = new MACDIndicator(closePriceIndicator, parameterMap.get("MACD_short").intValue(), parameterMap.get("MACD_long").intValue());
        EMAIndicator EMA4MACD = new EMAIndicator(MACD, parameterMap.get("EMA").intValue());

        // Entry rule
        // The long-term trend is up when a security is above its 200-period SMA.
        Rule entryRule = new OverIndicatorRule(SMA_short, SMA_long) // Trend
                .and(new CrossedDownIndicatorRule(RSI, parameterMap.get("CrossDownThreshold"))) // Signal 1
                .and(new OverIndicatorRule(SMA_short, closePriceIndicator)) // Signal 2
                .and(new OverIndicatorRule(MACD, EMA4MACD)); // Signal 2 // Signal 3

        // Exit rule
        // The long-term trend is down when a security is below its 200-period SMA.
        Rule exitRule = new UnderIndicatorRule(SMA_short, SMA_long) // Trend
                .and(new CrossedUpIndicatorRule(RSI, parameterMap.get("CrossUpThreshold"))) // Signal 1
                .and(new UnderIndicatorRule(SMA_short, closePriceIndicator)) // Signal 2
                .and(new UnderIndicatorRule(MACD, EMA4MACD)); // Signal 3

        return new BaseStrategy("MACD_RSI", entryRule, exitRule);
    }

    /**
     * SMA_short: ~10
     * SMA_long: ~200
     * RSI: ~2
     * MACD_short: ~12
     * MACD_long: ~26
     * EMA: ~18
     * CrossDownThreshold: ~5
     * CrossUpThreshold: ~95
     *
     * @return an default parameter map for MACD_RSI
     */
    public static Map<String, Double> buildDefaultParameterMap() {
        final Map<String, Double> parameterMap = new HashMap<>();
        parameterMap.put("SMA_short", 10D);
        parameterMap.put("SMA_long", 200D);
        parameterMap.put("RSI", 2D);
        parameterMap.put("MACD_short", 12D);
        parameterMap.put("MACD_long", 26D);
        parameterMap.put("EMA", 18D);
        parameterMap.put("CrossDownThreshold", 5D);
        parameterMap.put("CrossUpThreshold", 95D);
        return parameterMap;
    }

    /**
     * @param mean  the mean and starting point of the calibration set
     * @param range defines the range below and above the mean to generate parameter maps
     * @return
     */
    public static List<Map<String, Double>> buildCalibrationParameterMapList(final Map<String, Double> mean, Double range) {
        List<Map<String, Double>> result = new ArrayList<Map<String, Double>>();

        int[] SMA_short = getRange(mean.get("SMA_short"), range);
        for (int i = SMA_short[0]; i < SMA_short[1]; i++) {
            int[] SMA_long = getRange(mean.get("SMA_long"), range);
            for (int j = SMA_long[0]; j < SMA_long[1]; j++) {
                int[] RSI = getRange(mean.get("RSI"), range);
                for (int k = RSI[0]; k < RSI[1]; k++) {
                    int[] MACD_short = getRange(mean.get("MACD_short"), range);
                    for (int l = MACD_short[0]; l < MACD_short[1]; l++) {
                        int[] MACD_long = getRange(mean.get("MACD_long"), range);
                        for (int m = MACD_long[0]; m < MACD_long[1]; m++) {
                            int[] EMA = getRange(mean.get("EMA"), range);
                            for (int n = EMA[0]; n < EMA[1]; n++) {
                                int[] CrossDownThreshold = getRange(mean.get("CrossDownThreshold"), range);
                                for (int o = CrossDownThreshold[0]; o < CrossDownThreshold[1]; o++) {
                                    int[] CrossUpThreshold = getRange(mean.get("CrossUpThreshold"), range);
                                    for (int p = CrossUpThreshold[0]; p < CrossUpThreshold[1]; p++) {
                                        final Map<String, Double> parameterMap = new HashMap<>();
                                        parameterMap.put("SMA_short", (double) i);
                                        parameterMap.put("SMA_long", (double) j);
                                        parameterMap.put("RSI", (double) k);
                                        parameterMap.put("MACD_short", 12D);
                                        parameterMap.put("MACD_long", 26D);
                                        parameterMap.put("EMA", 18D);
                                        parameterMap.put("CrossDownThreshold", (double) l);
                                        parameterMap.put("CrossUpThreshold", (double) m);
                                        result.add(parameterMap);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
