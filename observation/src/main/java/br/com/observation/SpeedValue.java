package br.com.observation;

import java.math.BigInteger;

public class SpeedValue {

    private static final String EMPTY = "//";
    private static final String FORMAT_OUTPUT_VALUE = "%02d";
    private static final Long MAX_VALUE_INPUT = Long.valueOf("100");
    private static final String PLUS_99_KT_OUTPUT_VALUE = "P99";

    private BigInteger value;

    public SpeedValue(BigInteger value) {
        this.value = value;
    }

    public String getValue() {        
        return this.value == null ? EMPTY : formatSpeedValue(this.value.longValue());
    }   

    private String formatSpeedValue(Long value) {                
        Boolean isWindSpeedOverOneHundredKnots = value > MAX_VALUE_INPUT;
        if (isWindSpeedOverOneHundredKnots) return PLUS_99_KT_OUTPUT_VALUE;
        return String.format(FORMAT_OUTPUT_VALUE, value);
    }
}
