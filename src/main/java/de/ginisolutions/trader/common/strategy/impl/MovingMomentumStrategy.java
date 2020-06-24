package de.ginisolutions.trader.common.strategy.impl;

import de.ginisolutions.trader.common.strategy.parameter.ParameterMM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * Moving Momentum (MM) strategy.
 *
 * @see <a href=
 * "http://stockcharts.com/help/doku.php?id=chart_school:trading_strategies:moving_momentum">
 * http://stockcharts.com/help/doku.php?id=chart_school:trading_strategies:moving_momentum</a>
 */
public class MovingMomentumStrategy {

    private static final Logger logger = LoggerFactory.getLogger(MovingMomentumStrategy.class);

    public static Strategy buildStrategy(BarSeries series, ParameterMM parameterMM) {
        if (series == null) {
            throw new IllegalArgumentException("Series cannot be null");
        }

        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);

        // The bias is bullish when the shorter-moving average moves above the longer
        // moving average.
        // The bias is bearish when the shorter-moving average moves below the longer
        // moving average.
        EMAIndicator shortEma = new EMAIndicator(closePrice, parameterMM.getShortEmaBars());
        EMAIndicator longEma = new EMAIndicator(closePrice, parameterMM.getLongEmaBars());

        StochasticOscillatorKIndicator stochasticOscillK = new StochasticOscillatorKIndicator(series, parameterMM.getOscillatorBars());

        MACDIndicator macd = new MACDIndicator(closePrice, parameterMM.getMACDshortBars(), parameterMM.getMACDlongBars());
        EMAIndicator emaMacd = new EMAIndicator(macd, parameterMM.getEMAbars());

        // Entry rule
        Rule entryRule = new OverIndicatorRule(shortEma, longEma) // Trend
                .and(new CrossedDownIndicatorRule(stochasticOscillK, parameterMM.getThreshold())) // Signal 1
                .and(new OverIndicatorRule(macd, emaMacd)); // Signal 2

        // Exit rule
        Rule exitRule = new UnderIndicatorRule(shortEma, longEma) // Trend
                .and(new CrossedUpIndicatorRule(stochasticOscillK, parameterMM.getThreshold())) // Signal 1
                .and(new UnderIndicatorRule(macd, emaMacd)); // Signal 2

        return new BaseStrategy(entryRule, exitRule);
    }
}
