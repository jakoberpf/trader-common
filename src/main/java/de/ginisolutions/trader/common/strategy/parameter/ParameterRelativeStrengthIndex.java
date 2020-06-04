package de.ginisolutions.trader.common.strategy.parameter;

import de.ginisolutions.trader.trading.domain.enumeration.STRATEGY;

import javax.validation.constraints.NotBlank;

public class ParameterRelativeStrengthIndex implements StrategyParameter {

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

    public ParameterRelativeStrengthIndex() {
    }

    public ParameterRelativeStrengthIndex(int SMAshortBars, int SMAlongBars, int RSIbars, int cdownIthreshold, int cupIthreshold) {
        this.SMAshortBars = SMAshortBars;
        this.SMAlongBars = SMAlongBars;
        this.RSIbars = RSIbars;
        this.cdownIthreshold = cdownIthreshold;
        this.cupIthreshold = cupIthreshold;
    }

    @Override
    public STRATEGY getType() {
        return STRATEGY.RSI;
    }

    public int getSMAshortBars() {
        return SMAshortBars;
    }

    public void setSMAshortBars(int SMAshortBars) {
        this.SMAshortBars = SMAshortBars;
    }

    public int getSMAlongBars() {
        return SMAlongBars;
    }

    public void setSMAlongBars(int SMAlongBars) {
        this.SMAlongBars = SMAlongBars;
    }

    public int getRSIbars() {
        return RSIbars;
    }

    public void setRSIbars(int RSIbars) {
        this.RSIbars = RSIbars;
    }

    public int getCdownIthreshold() {
        return cdownIthreshold;
    }

    public void setCdownIthreshold(int cdownIthreshold) {
        this.cdownIthreshold = cdownIthreshold;
    }

    public int getCupIthreshold() {
        return cupIthreshold;
    }

    public void setCupIthreshold(int cupIthreshold) {
        this.cupIthreshold = cupIthreshold;
    }

    @Override
    public String toString() {
        return "AttributesRelativeStrengthIndex{" +
                "SMAshortBars=" + SMAshortBars +
                ", SMAlongBars=" + SMAlongBars +
                ", RSIbars=" + RSIbars +
                ", CdownIthreshold=" + cdownIthreshold +
                ", CupIthreshold=" + cupIthreshold +
                '}';
    }
}
