package de.ginisolutions.trader.history.domain.enumeration;

public enum MARKET {
    /**
     * Crypto Markets/Exchanges
     */
    BINANCE("Binance", "binance.com", ""),
    CRYPTO("Crypto", "crypto.com", ""),
    /**
     * Stock Markets/Exchanges
     */
    COMDIRECT("Comdirect", "comdirect.de", "");

    private final String name;

    private final String webURL;

    private final String apiURL;

    MARKET(String name, String webURL, String apiURL) {
        this.name = name;
        this.webURL = webURL;
        this.apiURL = apiURL;
    }

    public String getName() {
        return name;
    }

    public String getWebURL() {
        return webURL;
    }

    public String getApiURL() {
        return apiURL;
    }
}
