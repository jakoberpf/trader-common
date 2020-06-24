package de.ginisolutions.trader.common.strategy;

import de.ginisolutions.trader.common.strategy.impl.CommodityChannelIndexStrategy;
import de.ginisolutions.trader.common.strategy.impl.MovingMomentumStrategy;
import de.ginisolutions.trader.common.strategy.impl.DevelopmentStrategy;
import de.ginisolutions.trader.common.strategy.impl.RelativeStrengthIndexStrategy;
import de.ginisolutions.trader.common.strategy.parameter.*;
import de.ginisolutions.trader.trading.domain.enumeration.STRATEGY;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Strategy;

import javax.validation.constraints.NotNull;

/**
 *
 */
public class StrategyFactory {

    /**
     * @param barSeries
     * @param strategyEnum
     * @param parameters
     * @return
     */
    public static Strategy buildStrategy(@NotNull BarSeries barSeries, @NotNull STRATEGY strategyEnum, @NotNull StrategyParameter parameters) {
        switch (strategyEnum) {
            case RSI:
                ParameterRSI parameterRSI = (ParameterRSI) parameters;
                return RelativeStrengthIndexStrategy.buildStrategy(barSeries, parameterRSI);
            case MM:
                ParameterMM parameterMM = (ParameterMM) parameters;
                return MovingMomentumStrategy.buildStrategy(barSeries, parameterMM);
            case CCI:
                ParameterCCI parameterCCI = (ParameterCCI) parameters;
                return CommodityChannelIndexStrategy.buildStrategy(barSeries, parameterCCI);
            case DEVELOPMENT:
                ParameterDevelopment parameterDevelopment = (ParameterDevelopment) parameters;
                return DevelopmentStrategy.buildStrategy(barSeries, parameterDevelopment);
            default:
                throw new IllegalArgumentException("Strategy " + strategyEnum + " not found");
        }
    }
}
