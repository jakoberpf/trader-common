package de.ginisolutions.trader.learning.calibration;

import de.ginisolutions.trader.learning.calibration.utils.AttributeBuilder;
import de.ginisolutions.trader.learning.calibration.wrapper.WrapperMovingMomentum;
import de.ginisolutions.trader.common.strategy.parameter.ParameterMovingMomentum;
import de.ginisolutions.trader.learning.calibration.profit.Profit2MM;
import org.ta4j.core.BarSeries;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

public class MovingMomentumValueFinder {

    public Profit2MM run(BarSeries barSeries) {

        final ParameterMovingMomentum[] parameterMovingMomentumArray = AttributeBuilder.buildMovingMomentumAttributes();

        LocalDateTime before, after;

        before = LocalDateTime.now();
        final Profit2MM[] profit2MMS = convertStreamThreadLocal(barSeries, parameterMovingMomentumArray);
        after = LocalDateTime.now();
        Profit2MM max = Arrays.stream(profit2MMS).max(Comparator.comparing(Profit2MM::getProfit))
                .orElseThrow(NoSuchElementException::new);
        max.setDuration(Duration.between(before, after));
        Profit2MM min = Arrays.stream(profit2MMS).min(Comparator.comparing(Profit2MM::getProfit))
                .orElseThrow(NoSuchElementException::new);
        min.setDuration(Duration.between(before, after));
        System.out.println("Maximum Profit reached with " +
                max.getParams().toString() + " -> " + max.getProfit());
        System.out.println("Minimum Profit reached with " +
                min.getParams().toString() + " -> " + max.getProfit());
        return max;
    }

    private static Profit2MM[] convertStreamThreadLocal(final BarSeries barSeries, final ParameterMovingMomentum[] parameterMovingMomentumArray) {
        final ThreadLocal<WrapperMovingMomentum> dfLookup = ThreadLocal.withInitial(MovingMomentumValueFinder::buildStrategy);
        AtomicReference<Integer> iteration = new AtomicReference<>(0);
        return Arrays.stream(parameterMovingMomentumArray)
                .parallel()
                .map(paramMMAttribute -> {
                    final double profit = dfLookup.get().runStrategy(
                            barSeries,
                            paramMMAttribute
                    );
                    System.out.println(iteration + " = " +
                            paramMMAttribute.getLongEmaBars() + " / " +
                            paramMMAttribute.getLongEmaBars() + " / " +
                            paramMMAttribute.getOscillatorBars() + " / " +
                            paramMMAttribute.getMACDshortBars() + " / " +
                            paramMMAttribute.getMACDlongBars() + " / " +
                            paramMMAttribute.getEMAbars() + " / " +
                            paramMMAttribute.getThreshold() + " / " +
                            " -> " + profit);
                    iteration.getAndSet(iteration.get() + 1);
                    return new Profit2MM(profit, paramMMAttribute);
                }).toArray(Profit2MM[]::new);
    }

    private static WrapperMovingMomentum buildStrategy() {
        return new WrapperMovingMomentum();
    }
}
