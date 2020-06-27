package de.ginisolutions.trader.common.strategy.parameter;

import de.ginisolutions.trader.common.enumeration.STRATEGY;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotBlank;

public class ParameterCCI implements StrategyParameter {

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

    public ParameterCCI() {
    }

    public ParameterCCI(int CCIlong, int CCIshort, int plus, int minus, int unstablePeriod) {
        this.CCIlong = CCIlong;
        this.CCIshort = CCIshort;
        this.plus = plus;
        this.minus = minus;
        this.unstablePeriod = unstablePeriod;
    }

    @Override
    public STRATEGY getType() {
        return STRATEGY.CCIC;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ParameterCCI)) return false;

        ParameterCCI that = (ParameterCCI) o;

        return new EqualsBuilder()
                .append(getCCIlong(), that.getCCIlong())
                .append(getCCIshort(), that.getCCIshort())
                .append(getPlus(), that.getPlus())
                .append(getMinus(), that.getMinus())
                .append(getUnstablePeriod(), that.getUnstablePeriod())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getCCIlong())
                .append(getCCIshort())
                .append(getPlus())
                .append(getMinus())
                .append(getUnstablePeriod())
                .toHashCode();
    }
}
