package de.ginisolutions.trader.common.strategy.parameter;

import de.ginisolutions.trader.common.enumeration.STRATEGY;

/**
 * The interface for a strategy parameter set. It contains the type of strategy
 * and the necessary parameters for the strategy to be run.
 */
public interface StrategyParameter {
    STRATEGY getType();
}
