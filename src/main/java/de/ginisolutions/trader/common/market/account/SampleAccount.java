package de.ginisolutions.trader.common.market.account;

import de.ginisolutions.trader.common.market.AccountImpl;

import javax.validation.constraints.NotNull;

public class SampleAccount implements AccountImpl {

    /**
     * This is an emtpy testing class
     */

    @NotNull
    private final String key;

    @NotNull
    private final String secret;

    public SampleAccount(@NotNull String key, @NotNull String secret) {
        this.key = key;
        this.secret = secret;
    }
}
