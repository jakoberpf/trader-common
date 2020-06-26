package de.ginisolutions.trader.common.strategy.impl;

import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.StochasticOscillatorKIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.trading.rules.CrossedDownIndicatorRule;
import org.ta4j.core.trading.rules.CrossedUpIndicatorRule;
import org.ta4j.core.trading.rules.OverIndicatorRule;
import org.ta4j.core.trading.rules.UnderIndicatorRule;

import java.util.HashMap;
import java.util.Map;

/**
 * Moving Momentum (MM) based strategy.
 *
 * @see <a href=
 * "http://stockcharts.com/help/doku.php?id=chart_school:trading_strategies:moving_momentum">
 * http://stockcharts.com/help/doku.php?id=chart_school:trading_strategies:moving_momentum</a>
 */
public class MM_Strategy {

    public static Strategy buildStrategy(BarSeries series, Map<String, Double> parameterMap) {

        if (series == null) {
            throw new IllegalArgumentException("Series cannot be null");
        }

        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);

        // The bias is bullish when the shorter-moving average moves above the longer
        // moving average.
        // The bias is bearish when the shorter-moving average moves below the longer
        // moving average.
        EMAIndicator EMA_short = new EMAIndicator(closePrice, parameterMap.get("EMA_short").intValue());
        EMAIndicator longEma = new EMAIndicator(closePrice, parameterMap.get("EMA_long").intValue());

        StochasticOscillatorKIndicator stochasticOscillK = new StochasticOscillatorKIndicator(series, parameterMap.get("Oscillator").intValue());

        MACDIndicator macd = new MACDIndicator(closePrice, parameterMap.get("MACD_short").intValue(), parameterMap.get("MACD_long").intValue());
        EMAIndicator emaMacd = new EMAIndicator(macd, parameterMap.get("MACD_EMA").intValue());

        // Entry rule
        Rule entryRule = new OverIndicatorRule(EMA_short, longEma) // Trend
                .and(new CrossedDownIndicatorRule(stochasticOscillK, parameterMap.get("CrossDownThreshold"))) // Signal 1
                .and(new OverIndicatorRule(macd, emaMacd)); // Signal 2

        // Exit rule
        Rule exitRule = new UnderIndicatorRule(EMA_short, longEma) // Trend
                .and(new CrossedUpIndicatorRule(stochasticOscillK, parameterMap.get("CrossUpThreshold"))) // Signal 1
                .and(new UnderIndicatorRule(macd, emaMacd)); // Signal 2

        return new BaseStrategy(entryRule, exitRule);
    }

    /**
     * EMA_short: ~10
     * EMA_long: ~30
     * Oscillator: ~14
     * MACD_short: ~9
     * MACD_long: ~26
     * MACD_EMA: ~18
     * CrossDownThreshold: ~20
     * CrossUpThreshold: ~20
     *
     * @return an default parameter map for MM
     */
    public static Map<String, Double> buildDefaultParameterMap() {
        final Map<String, Double> parameterMap = new HashMap<>();
        parameterMap.put("EMA_short", 10D);
        parameterMap.put("EMA_long", 30D);
        parameterMap.put("Oscillator", 14D);
        parameterMap.put("MACD_short", 9D);
        parameterMap.put("MACD_long", 26D);
        parameterMap.put("MACD_EMA", 18D);
        parameterMap.put("CrossDownThreshold", 20D);
        parameterMap.put("CrossUpThreshold", 20D);
        return parameterMap;
    }
}
