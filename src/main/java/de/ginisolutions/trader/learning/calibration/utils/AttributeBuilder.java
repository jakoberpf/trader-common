package de.ginisolutions.trader.learning.calibration.utils;

import de.ginisolutions.trader.common.strategy.parameter.ParameterMovingMomentum;
import de.ginisolutions.trader.common.strategy.parameter.ParameterRelativeStrengthIndex;

import java.util.ArrayList;
import java.util.List;

public class AttributeBuilder {
    // Static Builder for Moving Momentum Parameters
    public static ParameterMovingMomentum[] buildMovingMomentumAttributes() {
        List<ParameterMovingMomentum> list = new ArrayList<>();
        for (int i = 5; i < 15; i++) {
            for (int j = 25; j < 35; j++) {
                for (int k = 5; k < 25; k++) {
                    for (int l = 5; l < 18; l++) {
                        for (int m = 19; m < 35; m++) {
                            for (int n = 10; n < 30; n++) {
                                for (int o = 15; o < 25; o++) {
                                    list.add(new ParameterMovingMomentum(i, j, k, l, m, n, o));
                                }
                            }
                        }
                    }
                }
            }
        }
        ParameterMovingMomentum[] attributeParameterMovingMomentums = new ParameterMovingMomentum[list.size()];
        for (int i = 0; i < attributeParameterMovingMomentums.length; i++) {
            attributeParameterMovingMomentums[i] = list.get(i);
        }
        System.out.println("Number of combinations: " + attributeParameterMovingMomentums.length);
        return attributeParameterMovingMomentums;
    }

    // Static builder for Relative Strength Index Parameters
    public static ParameterRelativeStrengthIndex[] buildRelativeStrengthIndexAttributes() {
        List<ParameterRelativeStrengthIndex> list = new ArrayList<>();
        for (int i = 5; i < 15; i++) {
            for (int j = 180; j < 220; j++) {
                for (int k = 1; k < 6; k++) {
                    for (int l = 2; l < 10; l++) {
                        for (int m = 80; m < 120; m++) {
                            list.add(new ParameterRelativeStrengthIndex(i, j, k, l, m));
                        }
                    }
                }
            }
        }
        ParameterRelativeStrengthIndex[] attributeRelativeStrengthIndices = new ParameterRelativeStrengthIndex[list.size()];
        for (int i = 0; i < attributeRelativeStrengthIndices.length; i++) {
            attributeRelativeStrengthIndices[i] = list.get(i);
        }
        System.out.println("Number of combinations: " + attributeRelativeStrengthIndices.length);
        return attributeRelativeStrengthIndices;
    }
}
