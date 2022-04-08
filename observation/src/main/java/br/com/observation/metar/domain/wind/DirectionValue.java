package br.com.observation.metar.domain.wind;

import java.math.BigInteger;

public class DirectionValue {

    private static final String FORMAT_OUTPUT_VALUE = "%03d";    
    private static final String EMPTY = "///";

    private BigInteger value;

    public DirectionValue(BigInteger value) {
        this.value = value;
    }

    public String getValue() {                
        return this.value == null ? EMPTY : formatDegreeValue(this.value);
    }   

    private String formatDegreeValue(BigInteger value) {                
        return String.format(FORMAT_OUTPUT_VALUE, value.longValue());
    }
    
}
