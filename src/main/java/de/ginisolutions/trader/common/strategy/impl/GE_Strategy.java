package de.ginisolutions.trader.common.strategy.impl;

import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.helpers.*;
import org.ta4j.core.trading.rules.OverIndicatorRule;
import org.ta4j.core.trading.rules.UnderIndicatorRule;

import java.util.HashMap;
import java.util.Map;

/**
 * Global Extrema (GE) based strategy.
 */
public class GE_Strategy {

    /**
     * @param series the bar series
     * @return the global extrema strategy
     */
    public static Strategy buildStrategy(BarSeries series, Map<String, Double> parameterMap) {

        if (series == null) {
            throw new IllegalArgumentException("Series cannot be null");
        }

        ClosePriceIndicator closePrices = new ClosePriceIndicator(series);

        // Getting the max price over the past week
        HighPriceIndicator maxPrices = new HighPriceIndicator(series);
        HighestValueIndicator weekMaxPrice = new HighestValueIndicator(maxPrices, parameterMap.get("NumberOfBarsPerWeek").intValue());
        // Getting the min price over the past week
        LowPriceIndicator minPrices = new LowPriceIndicator(series);
        LowestValueIndicator weekMinPrice = new LowestValueIndicator(minPrices, parameterMap.get("NumberOfBarsPerWeek").intValue());

        // Going long if the close price goes below the min price
        MultiplierIndicator downWeek = new MultiplierIndicator(weekMinPrice, parameterMap.get("downWeek"));
        Rule buyingRule = new UnderIndicatorRule(closePrices, downWeek);

        // Going short if the close price goes above the max price
        MultiplierIndicator upWeek = new MultiplierIndicator(weekMaxPrice, parameterMap.get("upWeek"));
        Rule sellingRule = new OverIndicatorRule(closePrices, upWeek);

        return new BaseStrategy("GE", buyingRule, sellingRule);
    }

    /**
     * NumberOfBarsPerWeek: 60 * 24 * 7 for minute bars
     * DownWeek: ~1.004
     * UpWeek: ~0.996
     *
     * @return an default parameter map for global extrema strategy
     */
    public static Map<String, Double> buildDefaultParameterMap() {
        final Map<String, Double> parameterMap = new HashMap<>();
        parameterMap.put("DownWeek", 1.004);
        parameterMap.put("UpWeek", 0.996);
        return parameterMap;
    }
}
