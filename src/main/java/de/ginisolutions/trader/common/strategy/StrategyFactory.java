package de.ginisolutions.trader.common.strategy;

import de.ginisolutions.trader.common.strategy.impl.CommodityChannelIndexStrategy;
import de.ginisolutions.trader.common.strategy.impl.MovingMomentumStrategy;
import de.ginisolutions.trader.common.strategy.impl.RelativeStrengthIndexStrategy;
import de.ginisolutions.trader.common.strategy.parameter.ParameterCommodityChannelIndex;
import de.ginisolutions.trader.common.strategy.parameter.ParameterMovingMomentum;
import de.ginisolutions.trader.common.strategy.parameter.ParameterRelativeStrengthIndex;
import de.ginisolutions.trader.common.strategy.parameter.StrategyParameter;
import de.ginisolutions.trader.trading.domain.enumeration.STRATEGY;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.trading.rules.OverIndicatorRule;
import org.ta4j.core.trading.rules.UnderIndicatorRule;

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
                ParameterRelativeStrengthIndex parameterRelativeStrengthIndex = (ParameterRelativeStrengthIndex) parameters;
                return RelativeStrengthIndexStrategy.buildStrategy(barSeries, parameterRelativeStrengthIndex);
            case MM:
                ParameterMovingMomentum parameterMovingMomentum = (ParameterMovingMomentum) parameters;
                return MovingMomentumStrategy.buildStrategy(barSeries, parameterMovingMomentum);
            case CCI:
                ParameterCommodityChannelIndex parameterCommodityChannelIndex = (ParameterCommodityChannelIndex) parameters;
                return CommodityChannelIndexStrategy.buildStrategy(barSeries, parameterCommodityChannelIndex);
            case SAMPLE_ENUM:
                ClosePriceIndicator closePrice = new ClosePriceIndicator(barSeries);
                SMAIndicator sma = new SMAIndicator(closePrice, 12);
                // Buy when SMA goes over close price
                // Sell when close price goes over SMA
                return new BaseStrategy(new OverIndicatorRule(sma, closePrice), new UnderIndicatorRule(sma, closePrice));
            default:
                throw new IllegalArgumentException("Strategy " + strategyEnum + " not found");
        }
    }
}
