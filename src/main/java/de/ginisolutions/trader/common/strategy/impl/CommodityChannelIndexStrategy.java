package de.ginisolutions.trader.common.strategy.impl;

import de.ginisolutions.trader.common.strategy.parameter.ParameterCommodityChannelIndex;
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
public class CommodityChannelIndexStrategy {

    private static final Logger logger = LoggerFactory.getLogger(CommodityChannelIndexStrategy.class);

    public static Strategy buildStrategy(BarSeries series, ParameterCommodityChannelIndex parameterCommodityChannelIndex) {
        logger.info("Building Commodity Channel Index Correction Strategy");
        if (series == null) {
            throw new IllegalArgumentException("Series cannot be null");
        }

        CCIIndicator longCci = new CCIIndicator(series, parameterCommodityChannelIndex.getCCIlong());
        CCIIndicator shortCci = new CCIIndicator(series, parameterCommodityChannelIndex.getCCIshort());
        Num plus100 = series.numOf(parameterCommodityChannelIndex.getPlus());
        Num minus100 = series.numOf(parameterCommodityChannelIndex.getMinus());

        Rule entryRule = new OverIndicatorRule(longCci, plus100) // Bull trend
                .and(new UnderIndicatorRule(shortCci, minus100)); // Signal

        Rule exitRule = new UnderIndicatorRule(longCci, minus100) // Bear trend
                .and(new OverIndicatorRule(shortCci, plus100)); // Signal

        Strategy strategy = new BaseStrategy(entryRule, exitRule);

        strategy.setUnstablePeriod(parameterCommodityChannelIndex.getUnstablePeriod());

        logger.info("Building Commodity Channel Index Correction Strategy finished");
        return strategy;
    }
}
