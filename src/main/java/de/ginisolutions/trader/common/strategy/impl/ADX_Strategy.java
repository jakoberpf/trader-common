package de.ginisolutions.trader.common.strategy.impl;

import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.adx.ADXIndicator;
import org.ta4j.core.indicators.adx.MinusDIIndicator;
import org.ta4j.core.indicators.adx.PlusDIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.trading.rules.CrossedDownIndicatorRule;
import org.ta4j.core.trading.rules.CrossedUpIndicatorRule;
import org.ta4j.core.trading.rules.OverIndicatorRule;
import org.ta4j.core.trading.rules.UnderIndicatorRule;

import java.util.HashMap;
import java.util.Map;

/**
 * Average Directional Index (ADX) Indicator based strategy.
 *
 * @see <a href=
 * "http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:average_directional_index_adx">
 * http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:average_directional_index_adx</a>
 */
public class ADX_Strategy {

    /**
     * @param series
     * @param parameterMap
     * @return
     */
    public static Strategy buildStrategy(BarSeries series, Map<String, Double> parameterMap) {

        if (series == null) {
            throw new IllegalArgumentException("Series cannot be null");
        }

        final ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);
        final SMAIndicator smaIndicator = new SMAIndicator(closePriceIndicator, parameterMap.get("SMA").intValue());

        final int adxBarCount = parameterMap.get("ADX").intValue();
        final ADXIndicator adxIndicator = new ADXIndicator(series, adxBarCount);
        final OverIndicatorRule adxOver20Rule = new OverIndicatorRule(adxIndicator, parameterMap.get("OverThreshold"));

        final PlusDIIndicator plusDIIndicator = new PlusDIIndicator(series, adxBarCount);
        final MinusDIIndicator minusDIIndicator = new MinusDIIndicator(series, adxBarCount);

        final Rule plusDICrossedUpMinusDI = new CrossedUpIndicatorRule(plusDIIndicator, minusDIIndicator);
        final Rule plusDICrossedDownMinusDI = new CrossedDownIndicatorRule(plusDIIndicator, minusDIIndicator);
        final OverIndicatorRule closePriceOverSma = new OverIndicatorRule(closePriceIndicator, smaIndicator);
        final Rule entryRule = adxOver20Rule.and(plusDICrossedUpMinusDI).and(closePriceOverSma);

        final UnderIndicatorRule closePriceUnderSma = new UnderIndicatorRule(closePriceIndicator, smaIndicator);
        final Rule exitRule = adxOver20Rule.and(plusDICrossedDownMinusDI).and(closePriceUnderSma);

        return new BaseStrategy("ADX", entryRule, exitRule, adxBarCount);
    }

    /**
     * SMA: ~50
     * ADX: ~14
     * OverThreshold: ~20
     *
     * @return an default parameter map for ADX
     */
    public static Map<String, Double> buildDefaultParameterMap() {
        final Map<String, Double> parameterMap = new HashMap<>();
        parameterMap.put("SMA", 50D);
        parameterMap.put("ADX", 14D);
        parameterMap.put("OverThreshold", 20D);
        return parameterMap;
    }
}
