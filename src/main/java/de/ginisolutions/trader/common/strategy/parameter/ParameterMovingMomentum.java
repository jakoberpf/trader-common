package de.ginisolutions.trader.common.strategy.parameter;

import de.ginisolutions.trader.trading.domain.enumeration.STRATEGY;

import javax.validation.constraints.NotBlank;

public class ParameterMovingMomentum implements StrategyParameter {
    @NotBlank
    private int shortEmaBars;

    @NotBlank
    private int longEmaBars;

    @NotBlank
    private int oscillatorBars;

    @NotBlank
    private int MACDshortBars;

    @NotBlank
    private int MACDlongBars;

    @NotBlank
    private int EMAbars;

    @NotBlank
    private int threshold;

    public ParameterMovingMomentum() {
    }

    public ParameterMovingMomentum(int shortEmaBars, int longEmaBars, int oscillatorBars, int MACDshortBars, int MACDlongBars, int EMAbars, int threshold) {
        this.shortEmaBars = shortEmaBars;
        this.longEmaBars = longEmaBars;
        this.oscillatorBars = oscillatorBars;
        this.MACDshortBars = MACDshortBars;
        this.MACDlongBars = MACDlongBars;
        this.EMAbars = EMAbars;
        this.threshold = threshold;
    }

    @Override
    public STRATEGY getType() {
        return STRATEGY.MM;
    }

    public int getShortEmaBars() {
        return shortEmaBars;
    }

    public void setShortEmaBars(int shortEmaBars) {
        this.shortEmaBars = shortEmaBars;
    }

    public int getLongEmaBars() {
        return longEmaBars;
    }

    public void setLongEmaBars(int longEmaBars) {
        this.longEmaBars = longEmaBars;
    }

    public int getOscillatorBars() {
        return oscillatorBars;
    }

    public void setOscillatorBars(int oscillatorBars) {
        this.oscillatorBars = oscillatorBars;
    }

    public int getMACDshortBars() {
        return MACDshortBars;
    }

    public void setMACDshortBars(int MACDshortBars) {
        this.MACDshortBars = MACDshortBars;
    }

    public int getMACDlongBars() {
        return MACDlongBars;
    }

    public void setMACDlongBars(int MACDlongBars) {
        this.MACDlongBars = MACDlongBars;
    }

    public int getEMAbars() {
        return EMAbars;
    }

    public void setEMAbars(int EMAbars) {
        this.EMAbars = EMAbars;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public String toString() {
        return "AttributesMovingMomentum{" +
                "shortEmaBars=" + shortEmaBars +
                ", longEmaBars=" + longEmaBars +
                ", oscillatorBars=" + oscillatorBars +
                ", MACDshortBars=" + MACDshortBars +
                ", MACDlongBars=" + MACDlongBars +
                ", EMAbars=" + EMAbars +
                ", threshold=" + threshold +
                '}';
    }
}
