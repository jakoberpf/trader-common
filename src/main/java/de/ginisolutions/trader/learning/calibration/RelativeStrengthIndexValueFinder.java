package de.ginisolutions.trader.learning.calibration;

import de.ginisolutions.trader.learning.calibration.utils.AttributeBuilder;
import de.ginisolutions.trader.learning.calibration.wrapper.WrapperRelativeStrengthIndex;
import de.ginisolutions.trader.common.strategy.parameter.ParameterRelativeStrengthIndex;
import de.ginisolutions.trader.learning.calibration.profit.Profit2RSI;
import org.ta4j.core.BarSeries;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

public class RelativeStrengthIndexValueFinder {

    public Profit2RSI run(BarSeries barSeries) {

        final ParameterRelativeStrengthIndex[] attributeRelativeStrengthIndices = AttributeBuilder.buildRelativeStrengthIndexAttributes();

        LocalDateTime before, after;

        before = LocalDateTime.now();
        final Profit2RSI[] profit2RelativeStrengthIndices = convertStreamThreadLocal(barSeries, attributeRelativeStrengthIndices);
        after = LocalDateTime.now();
        Profit2RSI max = Arrays.stream(profit2RelativeStrengthIndices).max(Comparator.comparing(Profit2RSI::getProfit))
                .orElseThrow(NoSuchElementException::new);
        max.setDuration(Duration.between(before, after));
        Profit2RSI min = Arrays.stream(profit2RelativeStrengthIndices).min(Comparator.comparing(Profit2RSI::getProfit))
                .orElseThrow(NoSuchElementException::new);
        min.setDuration(Duration.between(before, after));
        System.out.println("Maximum Profit reached with " +
                max.getParams().toString() + " -> " + max.getProfit());
        System.out.println("Minimum Profit reached with " +
                min.getParams().toString() + " -> " + min.getProfit());
        return max;
    }

    private static Profit2RSI[] convertStreamThreadLocal(final BarSeries barSeries, final ParameterRelativeStrengthIndex[] attributeRelativeStrengthIndices) {
        final ThreadLocal<WrapperRelativeStrengthIndex> dfLookup = ThreadLocal.withInitial(RelativeStrengthIndexValueFinder::buildStrategy);
        AtomicReference<Integer> iteration = new AtomicReference<>(0);
        return Arrays.stream(attributeRelativeStrengthIndices)
                .parallel()
                .map(paramRSIAttribute -> {
                    final double profit = dfLookup.get().runStrategy(
                            barSeries,
                            paramRSIAttribute
                    );
                    System.out.println(iteration + " = " +
                            paramRSIAttribute.getSMAshortBars() + " / " +
                            paramRSIAttribute.getSMAlongBars() + " / " +
                            paramRSIAttribute.getRSIbars() + " / " +
                            paramRSIAttribute.getCdownIthreshold() + " / " +
                            paramRSIAttribute.getCupIthreshold() + " / " +
                            " -> " + profit);
                    iteration.getAndSet(iteration.get() + 1);
                    return new Profit2RSI(profit, paramRSIAttribute);
                }).toArray(Profit2RSI[]::new);
    }

    private static WrapperRelativeStrengthIndex buildStrategy() {
        return new WrapperRelativeStrengthIndex();
    }
}

