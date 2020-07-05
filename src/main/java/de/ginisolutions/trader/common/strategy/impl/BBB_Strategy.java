package de.ginisolutions.trader.common.strategy.impl;

import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.bollinger.BollingerBandsLowerIndicator;
import org.ta4j.core.indicators.bollinger.BollingerBandsMiddleIndicator;
import org.ta4j.core.indicators.bollinger.BollingerBandsUpperIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.statistics.StandardDeviationIndicator;
import org.ta4j.core.num.DoubleNum;
import org.ta4j.core.num.PrecisionNum;
import org.ta4j.core.trading.rules.OverIndicatorRule;
import org.ta4j.core.trading.rules.StopLossRule;
import org.ta4j.core.trading.rules.UnderIndicatorRule;

import java.util.HashMap;
import java.util.Map;

/**
 * Bollinger Bands Breakout (BBB) Indicator based strategy.
 *
 * @see <a href=
 * "">
 * </a>
 */
public class BBB_Strategy {

    public static Strategy buildStrategy(BarSeries series, Map<String, Double> parameterMap) {

        final ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
        final SMAIndicator SMA = new SMAIndicator(closePrice, parameterMap.get("SMA").intValue());

        final BollingerBandsMiddleIndicator bbmiddle = new BollingerBandsMiddleIndicator(SMA);
        final StandardDeviationIndicator sd = new StandardDeviationIndicator(closePrice, parameterMap.get("SMA").intValue());

        final BollingerBandsUpperIndicator bbup = new BollingerBandsUpperIndicator(bbmiddle, sd, PrecisionNum.valueOf(parameterMap.get("DeviationUpperFactor")));
        final BollingerBandsLowerIndicator bbdown = new BollingerBandsLowerIndicator(bbmiddle, sd, PrecisionNum.valueOf(parameterMap.get("DeviationLowerFactor")));

        final Rule buyingRule = new UnderIndicatorRule(closePrice, bbdown);
        final Rule sellingRule = new OverIndicatorRule(closePrice, bbup).or(new StopLossRule(closePrice, PrecisionNum.valueOf(parameterMap.get("StopLossRule"))));

        return new BaseStrategy("BBB", buyingRule, sellingRule);
    }

    /**
     * SMA: ~20
     * DeviationUpperFactor: ~2
     * DeviationLowerFactor: ~2
     * StopLossRule: ~2
     *
     * @return an default parameter map for ADX
     */
    public static Map<String, Double> buildDefaultParameterMap() {
        final Map<String, Double> parameterMap = new HashMap<>();
        parameterMap.put("SMA", 20D);
        parameterMap.put("DeviationUpperFactor", 2D);
        parameterMap.put("DeviationLowerFactor", 2D);
        parameterMap.put("StopLossRule", 2D);
        return parameterMap;
    }
}
