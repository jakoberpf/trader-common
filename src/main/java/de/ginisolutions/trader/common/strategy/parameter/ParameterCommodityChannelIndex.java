package de.ginisolutions.trader.common.strategy.parameter;

import de.ginisolutions.trader.trading.domain.enumeration.STRATEGY;

import javax.validation.constraints.NotBlank;

public class ParameterCommodityChannelIndex implements StrategyParameter {

    @NotBlank
    private int CCIlong;

    @NotBlank
    private int CCIshort;

    @NotBlank
    private int plus;

    @NotBlank
    private int minus;

    @NotBlank
    private int unstablePeriod;

    public ParameterCommodityChannelIndex() {
    }

    public ParameterCommodityChannelIndex(int CCIlong, int CCIshort, int plus, int minus, int unstablePeriod) {
        this.CCIlong = CCIlong;
        this.CCIshort = CCIshort;
        this.plus = plus;
        this.minus = minus;
        this.unstablePeriod = unstablePeriod;
    }

    @Override
    public STRATEGY getType() {
        return STRATEGY.CCI;
    }

    public int getCCIlong() {
        return CCIlong;
    }

    public void setCCIlong(int CCIlong) {
        this.CCIlong = CCIlong;
    }

    public int getCCIshort() {
        return CCIshort;
    }

    public void setCCIshort(int CCIshort) {
        this.CCIshort = CCIshort;
    }

    public int getPlus() {
        return plus;
    }

    public void setPlus(int plus) {
        this.plus = plus;
    }

    public int getMinus() {
        return minus;
    }

    public void setMinus(int minus) {
        this.minus = minus;
    }

    public int getUnstablePeriod() {
        return unstablePeriod;
    }

    public void setUnstablePeriod(int unstablePeriod) {
        this.unstablePeriod = unstablePeriod;
    }

    @Override
    public String toString() {
        return "ParamCCI{" +
                "CCIlong=" + CCIlong +
                ", CCIshort=" + CCIshort +
                ", plus=" + plus +
                ", minus=" + minus +
                ", unstablePeriod=" + unstablePeriod +
                '}';
    }
}
