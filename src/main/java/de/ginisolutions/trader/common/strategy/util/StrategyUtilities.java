package de.ginisolutions.trader.common.strategy.util;

public class StrategyUtilities {

    public static int[] getRange(Double mean, Double range) {
        int[] result = new int[2];
        result[0] = (int) (mean * (1 - range));
        result[1] = (int) (mean * (1 + range));
        return result;
    }

}
