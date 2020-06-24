package de.ginisolutions.trader.common.strategy.impl;

import de.ginisolutions.trader.common.strategy.parameter.ParameterDevelopment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.*;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.trading.rules.CrossedDownIndicatorRule;
import org.ta4j.core.trading.rules.CrossedUpIndicatorRule;
import org.ta4j.core.trading.rules.OverIndicatorRule;
import org.ta4j.core.trading.rules.UnderIndicatorRule;

public class DevelopmentStrategy {

    private static final Logger logger = LoggerFactory.getLogger(RelativeStrengthIndexStrategy.class);

    public static Strategy buildStrategy(BarSeries barSeries, ParameterDevelopment parameter) {
        logger.debug("Building Relative Strength Index Strategy {}", parameter);

        if (barSeries == null) {
            throw new IllegalArgumentException("Series cannot be null");
        }

        ClosePriceIndicator closePrice = new ClosePriceIndicator(barSeries);
        SMAIndicator shortSma = new SMAIndicator(closePrice, parameter.getSMAshortBars());
        SMAIndicator longSma = new SMAIndicator(closePrice, parameter.getSMAlongBars());

        // The bias is bullish when the shorter-moving average moves above the longer
        // moving average.
        // The bias is bearish when the shorter-moving average moves below the longer
        // moving average.
        EMAIndicator shortEma = new EMAIndicator(closePrice, parameter.getShortEmaBars());
        EMAIndicator longEma = new EMAIndicator(closePrice, parameter.getLongEmaBars());

        StochasticOscillatorKIndicator stochasticOscillK = new StochasticOscillatorKIndicator(barSeries, parameter.getOscillatorBars());

        MACDIndicator macd = new MACDIndicator(closePrice, parameter.getMACDshortBars(), parameter.getMACDlongBars());
        EMAIndicator emaMacd = new EMAIndicator(macd, parameter.getEMAbars());

        // We use a 2-period RSI indicator to identify buying
        // or selling opportunities within the bigger trend.
        RSIIndicator rsi = new RSIIndicator(closePrice, parameter.getRSIbars());

        // Entry rule
        // The long-term trend is up when a security is above its 200-period SMA.
        Rule entryRule = new OverIndicatorRule(shortSma, longSma) // Trend
                .and(new CrossedDownIndicatorRule(rsi, parameter.getCdownIthreshold())) // Signal 1
                .and(new OverIndicatorRule(shortSma, closePrice)) // Signal 2
                // Moving momentum
                .and(new OverIndicatorRule(shortEma, longEma)) // Trend
                .and(new CrossedDownIndicatorRule(stochasticOscillK, parameter.getThreshold())) // Signal 3
                .and(new OverIndicatorRule(macd, emaMacd)); // Signal 4

        // Exit rule
        // The long-term trend is down when a security is below its 200-period SMA.
        Rule exitRule = new UnderIndicatorRule(shortSma, longSma) // Trend
                .and(new CrossedUpIndicatorRule(rsi, parameter.getCupIthreshold())) // Signal 1
                .and(new UnderIndicatorRule(shortSma, closePrice)) // Signal 2
                // Moving momentum
                .and(new UnderIndicatorRule(shortEma, longEma)) // Trend
                .and(new CrossedUpIndicatorRule(stochasticOscillK, parameter.getThreshold())) // Signal 3
                .and(new UnderIndicatorRule(macd, emaMacd)); // Signal 4

        logger.debug("Finished building Relative Strength Index Strategy {}", parameter);
        return new BaseStrategy(entryRule, exitRule);
    }
}
