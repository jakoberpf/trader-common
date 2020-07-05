package de.ginisolutions.trader.common.strategy.impl;

import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.bollinger.BollingerBandsLowerIndicator;
import org.ta4j.core.indicators.bollinger.BollingerBandsMiddleIndicator;
import org.ta4j.core.indicators.bollinger.BollingerBandsUpperIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.statistics.StandardDeviationIndicator;
import org.ta4j.core.num.PrecisionNum;
import org.ta4j.core.trading.rules.OverIndicatorRule;
import org.ta4j.core.trading.rules.StopLossRule;
import org.ta4j.core.trading.rules.UnderIndicatorRule;

import java.util.HashMap;
import java.util.Map;

public class BBB_MACD_Strategy {

    public static Strategy buildStrategy(BarSeries series, Map<String, Double> parameterMap) {

        final ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);
        final SMAIndicator SMA = new SMAIndicator(closePriceIndicator, parameterMap.get("SMA").intValue());

        final BollingerBandsMiddleIndicator bollingerMiddel = new BollingerBandsMiddleIndicator(SMA);
        final StandardDeviationIndicator standardDeviation = new StandardDeviationIndicator(closePriceIndicator, parameterMap.get("SMA").intValue());

        final BollingerBandsUpperIndicator bollingerUp = new BollingerBandsUpperIndicator(bollingerMiddel, standardDeviation, PrecisionNum.valueOf(parameterMap.get("DeviationUpperFactor")));
        final BollingerBandsLowerIndicator bollingerDown = new BollingerBandsLowerIndicator(bollingerMiddel, standardDeviation, PrecisionNum.valueOf(parameterMap.get("DeviationLowerFactor")));

        MACDIndicator MACD = new MACDIndicator(closePriceIndicator, parameterMap.get("MACD_short").intValue(), parameterMap.get("MACD_long").intValue());
        EMAIndicator EMA4MACD = new EMAIndicator(MACD, parameterMap.get("EMA").intValue());

        final Rule buyingRule = new UnderIndicatorRule(closePriceIndicator, bollingerDown) // Signal 1
                .and(new OverIndicatorRule(MACD, EMA4MACD)); // Signal 2
        final Rule sellingRule = new OverIndicatorRule(closePriceIndicator, bollingerUp) // Signal 1
                .and(new UnderIndicatorRule(MACD, EMA4MACD)); // Signal 2
//                .or(new StopLossRule(closePriceIndicator, PrecisionNum.valueOf(parameterMap.get("StopLossRule"))));

        return new BaseStrategy("BBB_MACD", buyingRule, sellingRule);
    }

    /**
     * SMA: ~20
     * DeviationUpperFactor: ~2
     * DeviationLowerFactor: ~2
     * MACD_short: ~12
     * MACD_long: ~26
     * EMA: ~18
     * StopLossRule: ~2
     *
     * @return an default parameter map for ADX
     */
    public static Map<String, Double> buildDefaultParameterMap() {
        final Map<String, Double> parameterMap = new HashMap<>();
        parameterMap.put("SMA", 20D);
        parameterMap.put("DeviationUpperFactor", 2D);
        parameterMap.put("DeviationLowerFactor", 2D);
        parameterMap.put("MACD_short", 12D);
        parameterMap.put("MACD_long", 26D);
        parameterMap.put("EMA", 18D);
        parameterMap.put("StopLossRule", 2D);
        return parameterMap;
    }
}
