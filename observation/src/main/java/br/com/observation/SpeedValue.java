package br.com.observation;

import org.apache.commons.lang3.StringUtils;

public class SpeedValue {
    
    private static final String UNKNOWN = "X";
    private static final String EMPTY = "//";
    private static final String FORMAT_OUTPUT_VALUE = "%02d";
    private static final Long MAX_VALUE_INPUT = Long.valueOf("100");
    private static final String PLUS_99_KT_OUTPUT_VALUE = "P99";

    private String value;

    public SpeedValue(String value) {
        this.value = value;
    }

    public String getValue() {
        if (StringUtils.isBlank(this.value)) {
            return EMPTY;
        }
        return isUnknown(this.value) ? EMPTY : formatSpeedValue(this.value);
    }

    private Boolean isUnknown(String value) {        
        return value.toUpperCase().startsWith(UNKNOWN);
    }

    private String formatSpeedValue(String value) {        
        Long speedKt = Long.valueOf(value);
        Boolean isWindSpeedOverOneHundredKnots = speedKt > MAX_VALUE_INPUT;
        if (isWindSpeedOverOneHundredKnots) return PLUS_99_KT_OUTPUT_VALUE;
        return String.format(FORMAT_OUTPUT_VALUE, speedKt);
    }
}
