package de.ginisolutions.trader.common.strategy;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.ginisolutions.trader.common.enumeration.STRATEGY.RSI;

public class StrategyParameterListTest {

    @Test
    public void RSI() {
        final Map<String, Double> parameterMap = new HashMap<>();
        parameterMap.put("SMA_short", 10D);
        parameterMap.put("SMA_long", 10D);
        parameterMap.put("RSI", 10D);
        parameterMap.put("CrossDownThreshold", 10D);
        parameterMap.put("CrossUpThreshold", 10D);
        final List<Map<String, Double>> maps = StrategyFactory.buildCalibrationParameterMapList(RSI, parameterMap, 0.5);
    }
}
