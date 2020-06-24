package de.ginisolutions.trader.common.strategy.parameter;

import de.ginisolutions.trader.trading.domain.enumeration.STRATEGY;

import javax.validation.constraints.NotBlank;

public class ParameterDevelopment implements StrategyParameter {

    @NotBlank
    private int SMAshortBars;

    @NotBlank
    private int SMAlongBars;

    @NotBlank
    private int RSIbars;

    @NotBlank
    private int cdownIthreshold;

    @NotBlank
    private int cupIthreshold;

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

    public ParameterDevelopment(@NotBlank int SMAshortBars, @NotBlank int SMAlongBars, @NotBlank int RSIbars, @NotBlank int cdownIthreshold, @NotBlank int cupIthreshold, @NotBlank int shortEmaBars, @NotBlank int longEmaBars, @NotBlank int oscillatorBars, @NotBlank int MACDshortBars, @NotBlank int MACDlongBars, @NotBlank int EMAbars, @NotBlank int threshold) {
        this.SMAshortBars = SMAshortBars;
        this.SMAlongBars = SMAlongBars;
        this.RSIbars = RSIbars;
        this.cdownIthreshold = cdownIthreshold;
        this.cupIthreshold = cupIthreshold;
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
        return STRATEGY.DEVELOPMENT;
    }

    public int getSMAshortBars() {
        return SMAshortBars;
    }

    public int getSMAlongBars() {
        return SMAlongBars;
    }

    public int getRSIbars() {
        return RSIbars;
    }

    public int getCdownIthreshold() {
        return cdownIthreshold;
    }

    public int getCupIthreshold() {
        return cupIthreshold;
    }

    public int getShortEmaBars() {
        return shortEmaBars;
    }

    public int getLongEmaBars() {
        return longEmaBars;
    }

    public int getOscillatorBars() {
        return oscillatorBars;
    }

    public int getMACDshortBars() {
        return MACDshortBars;
    }

    public int getMACDlongBars() {
        return MACDlongBars;
    }

    public int getEMAbars() {
        return EMAbars;
    }

    public int getThreshold() {
        return threshold;
    }
}
