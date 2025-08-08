package com.example.crypto_forex_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VolatilityData {

    private String symbol;

    @JsonProperty("HIGH_PRICE")
    private double highPrice;

    @JsonProperty("LOW_PRICE")
    private double lowPrice;

    @JsonProperty("VOLATILITY_PERCENT")
    private double volatilityPercent;

    @JsonProperty("WINDOWSTART")
    private Long windowStart;

    @JsonProperty("WINDOWEND")
    private Long windowEnd;

    // Getters & Setters

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getHighPrice() {
        return highPrice;
    }
    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }
    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getVolatilityPercent() {
        return volatilityPercent;
    }
    public void setVolatilityPercent(double volatilityPercent) {
        this.volatilityPercent = volatilityPercent;
    }

    public Long getWindowStart() {
        return windowStart;
    }
    public void setWindowStart(Long windowStart) {
        this.windowStart = windowStart;
    }

    public Long getWindowEnd() {
        return windowEnd;
    }
    public void setWindowEnd(Long windowEnd) {
        this.windowEnd = windowEnd;
    }

    @Override
    public String toString() {
        return "VolatilityData{" +
                "symbol='" + symbol + '\'' +
                ", windowStart=" + windowStart +
                ", windowEnd=" + windowEnd +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", volatilityPercent=" + volatilityPercent +
                '}';
    }
}
