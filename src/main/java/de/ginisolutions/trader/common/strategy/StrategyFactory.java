package de.ginisolutions.trader.common.strategy;

import de.ginisolutions.trader.common.strategy.impl.*;
import de.ginisolutions.trader.common.enumeration.STRATEGY;
import org.ta4j.core.BarSeries;
import org.ta4j.core.Strategy;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:contact@jakoberpf.de">Jakob Erpf</a>
 */
public class StrategyFactory {

    /**
     * @param barSeries
     * @param strategy
     * @param parameterMap
     * @return
     */
    public static Strategy buildStrategy(@NotNull BarSeries barSeries, @NotNull STRATEGY strategy, @NotNull Map<String, Double> parameterMap) {
        switch (strategy) {
            case ADX:
                return ADX_Strategy.buildStrategy(barSeries, parameterMap);
            case CCIC:
                return CCIC_Strategy.buildStrategy(barSeries, parameterMap);
            case GE:
                return GE_Strategy.buildStrategy(barSeries, parameterMap);
            case MACD_RSI:
                return MACD_RSI_Strategy.buildStrategy(barSeries, parameterMap);
            case MM:
                return MM_Strategy.buildStrategy(barSeries, parameterMap);
            case RSI:
                return RSI_Strategy.buildStrategy(barSeries, parameterMap);
            default:
                throw new IllegalArgumentException("Strategy " + strategy + " not found");
        }
    }

    /**
     * @param strategy
     * @return
     */
    public static Map<String, Double> buildDefaultParameterMap(@NotNull STRATEGY strategy) {
        switch (strategy) {
            case ADX:
                return ADX_Strategy.buildDefaultParameterMap();
            case CCIC:
                return CCIC_Strategy.buildDefaultParameterMap();
            case GE:
                return GE_Strategy.buildDefaultParameterMap();
            case MACD_RSI:
                return MACD_RSI_Strategy.buildDefaultParameterMap();
            case MM:
                return MM_Strategy.buildDefaultParameterMap();
            case RSI:
                return RSI_Strategy.buildDefaultParameterMap();
            default:
                throw new IllegalArgumentException("Strategy " + strategy + " not found");
        }
    }

    /**
     * @param strategy
     * @return
     */
    public static List<Map<String, Double>> buildCalibrationParameterMapList(@NotNull STRATEGY strategy, Map<String, Double> parameterMap, Double range) {
        switch (strategy) {
            case ADX:
//                return ADX_Strategy.buildCalibrationParameterMapList(parameterMap, range);
                throw new IllegalArgumentException("Not yet implemented");
            case CCIC:
//                return CCIC_Strategy.buildCalibrationParameterMapList(parameterMap, range);
                throw new IllegalArgumentException("Not yet implemented");
            case GE:
//                return GE_Strategy.buildCalibrationParameterMapList(parameterMap, range);
                throw new IllegalArgumentException("Not yet implemented");
            case MACD_RSI:
                return MACD_RSI_Strategy.buildCalibrationParameterMapList(parameterMap, range);
            case MM:
//                return MM_Strategy.buildCalibrationParameterMapList(parameterMap, range);
                throw new IllegalArgumentException("Not yet implemented");
            case RSI:
                return RSI_Strategy.buildCalibrationParameterMapList(parameterMap, range);
            default:
                throw new IllegalArgumentException("Strategy " + strategy + " not found");
        }
    }
}
