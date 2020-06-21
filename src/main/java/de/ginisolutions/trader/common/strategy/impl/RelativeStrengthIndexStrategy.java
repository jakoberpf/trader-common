package de.ginisolutions.trader.common.strategy.impl;

import de.ginisolutions.trader.common.strategy.parameter.ParameterRelativeStrengthIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.trading.rules.CrossedDownIndicatorRule;
import org.ta4j.core.trading.rules.CrossedUpIndicatorRule;
import org.ta4j.core.trading.rules.OverIndicatorRule;
import org.ta4j.core.trading.rules.UnderIndicatorRule;

/**
 * Relative Strength Index (RSI) strategy.
 *
 * @see <a href=
 * "https://school.stockcharts.com/doku.php?id=technical_indicators:rrg_relative_strength">
 * https://school.stockcharts.com/doku.php?id=technical_indicators:rrg_relative_strength</a>
 */
public class RelativeStrengthIndexStrategy {

    private static final Logger logger = LoggerFactory.getLogger(RelativeStrengthIndexStrategy.class);

    public static Strategy buildStrategy(BarSeries barSeries, ParameterRelativeStrengthIndex parameterRelativeStrengthIndex) {
        logger.debug("Building Relative Strength Index Strategy {}", parameterRelativeStrengthIndex);

        if (barSeries == null) {
            throw new IllegalArgumentException("Series cannot be null");
        }

        ClosePriceIndicator closePrice = new ClosePriceIndicator(barSeries);
        SMAIndicator shortSma = new SMAIndicator(closePrice, parameterRelativeStrengthIndex.getSMAshortBars());
        SMAIndicator longSma = new SMAIndicator(closePrice, parameterRelativeStrengthIndex.getSMAlongBars());

        // We use a 2-period RSI indicator to identify buying
        // or selling opportunities within the bigger trend.
        RSIIndicator rsi = new RSIIndicator(closePrice, parameterRelativeStrengthIndex.getRSIbars());

        // Entry rule
        // The long-term trend is up when a security is above its 200-period SMA.
        Rule entryRule = new OverIndicatorRule(shortSma, longSma) // Trend
                .and(new CrossedDownIndicatorRule(rsi, parameterRelativeStrengthIndex.getCdownIthreshold())) // Signal 1
                .and(new OverIndicatorRule(shortSma, closePrice)); // Signal 2

        // Exit rule
        // The long-term trend is down when a security is below its 200-period SMA.
        Rule exitRule = new UnderIndicatorRule(shortSma, longSma) // Trend
                .and(new CrossedUpIndicatorRule(rsi, parameterRelativeStrengthIndex.getCupIthreshold())) // Signal 1
                .and(new UnderIndicatorRule(shortSma, closePrice)); // Signal 2

        logger.debug("Finished building Relative Strength Index Strategy {}", parameterRelativeStrengthIndex);
        return new BaseStrategy(entryRule, exitRule);
    }
}
