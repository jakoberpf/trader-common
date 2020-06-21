package de.ginisolutions.trader.common.strategy.impl;

import de.ginisolutions.trader.common.strategy.parameter.StrategyParameter;
import org.ta4j.core.BarSeries;

/**
 *
 */
public interface Strategy {
    Strategy buildStrategy(BarSeries barSeries, StrategyParameter strategyParameter);
}
