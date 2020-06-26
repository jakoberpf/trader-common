package de.ginisolutions.trader.common.strategy.impl;

import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.CCIIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.trading.rules.OverIndicatorRule;
import org.ta4j.core.trading.rules.UnderIndicatorRule;

import java.util.HashMap;
import java.util.Map;

/**
 * Commodity Channel Index (CCI) Correction based strategy.
 *
 * @see <a href=
 * "https://school.stockcharts.com/doku.php?id=trading_strategies:cci_correction">
 * https://school.stockcharts.com/doku.php?id=trading_strategies:cci_correction</a>
 */
public class CCIC_Strategy {

    /**
     * @param series a bar series
     * @return an CCI indicator based strategy
     */
    public static Strategy buildStrategy(BarSeries series, Map<String, Double> parameterMap) {

        if (series == null) {
            throw new IllegalArgumentException("Series cannot be null");
        }

        CCIIndicator CCI_long = new CCIIndicator(series, parameterMap.get("CCI_long").intValue());
        CCIIndicator CCI_short = new CCIIndicator(series, parameterMap.get("CCI_short").intValue());
        Num plus100 = series.numOf(parameterMap.get("Plus100"));
        Num minus100 = series.numOf(parameterMap.get("Minus100"));

        Rule entryRule = new OverIndicatorRule(CCI_long, plus100) // Bull trend
                .and(new UnderIndicatorRule(CCI_short, minus100)); // Signal

        Rule exitRule = new UnderIndicatorRule(CCI_long, minus100) // Bear trend
                .and(new OverIndicatorRule(CCI_short, plus100)); // Signal

        return new BaseStrategy("CCIC", entryRule, exitRule, parameterMap.get("UnstablePeriod").intValue());
    }

    /**
     * CCI_long: ~200
     * CCI_short: ~5
     * Plus100: 100
     * Minus100: -100
     * UnstablePeriod: 5
     *
     * @return an default parameter map for CCI
     */
    public static Map<String, Double> buildDefaultParameterMap() {
        final Map<String, Double> parameterMap = new HashMap<>();
        parameterMap.put("CCI_long", 200D);
        parameterMap.put("CCI_short", 5D);
        parameterMap.put("Plus100", 100D);
        parameterMap.put("Minus100", -100D);
        parameterMap.put("UnstablePeriod", 5D);
        return parameterMap;
    }
}
