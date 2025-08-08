package com.example.crypto_forex_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlertMessage {

    // Alan adları artık ksqlDB'den gelen JSON ile birebir aynı.
    private String symbol;
    private Double latestPrice;
    private Double priceAvg;
    private Double percentageIncrease;
    private Long timestamp;
}
