package de.ginisolutions.trader.common.strategy.impl;

import de.ginisolutions.trader.common.strategy.parameter.ParameterCCI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.CCIIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.trading.rules.OverIndicatorRule;
import org.ta4j.core.trading.rules.UnderIndicatorRule;

/**
 * Commodity Channel Index (CCI) Correction strategy.
 *
 * @see <a href=
 * "https://school.stockcharts.com/doku.php?id=trading_strategies:cci_correction">
 * https://school.stockcharts.com/doku.php?id=trading_strategies:cci_correction</a>
 */
public class CCICStrategy {

    /**
     * @param series
     * @param parameterCCI
     * @return
     */
    public static Strategy buildStrategy(BarSeries series, ParameterCCI parameterCCI) {

        if (series == null) {
            throw new IllegalArgumentException("Series cannot be null");
        }

        CCIIndicator longCci = new CCIIndicator(series, parameterCCI.getCCIlong());
        CCIIndicator shortCci = new CCIIndicator(series, parameterCCI.getCCIshort());
        Num plus100 = series.numOf(parameterCCI.getPlus());
        Num minus100 = series.numOf(parameterCCI.getMinus());

        Rule entryRule = new OverIndicatorRule(longCci, plus100) // Bull trend
                .and(new UnderIndicatorRule(shortCci, minus100)); // Signal

        Rule exitRule = new UnderIndicatorRule(longCci, minus100) // Bear trend
                .and(new OverIndicatorRule(shortCci, plus100)); // Signal

        return new BaseStrategy("CCIC", entryRule, exitRule, parameterCCI.getUnstablePeriod());
    }
}
