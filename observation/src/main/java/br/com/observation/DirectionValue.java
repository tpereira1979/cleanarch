package br.com.observation;

import org.apache.commons.lang3.StringUtils;

public class DirectionValue {

    private static final String FORMAT_OUTPUT_VALUE = "%03d";
    private static final String CALM_OUTPUT_VALUE = "000";
    private static final String CALM_INPUT_VALUE = "00";
    private static final String UNKNOWN = "X";
    private static final String EMPTY = "///";

    private String value;

    public DirectionValue(String value) {
        this.value = value;
    }

    public String getValue() {
        if (StringUtils.isBlank(value)) return EMPTY;
        if (CALM_INPUT_VALUE.equalsIgnoreCase(value)) return CALM_OUTPUT_VALUE; 
        return isUnknown(this.value) ? EMPTY : formatDegreeValue(this.value);
    }

    private Boolean isUnknown(String value) {        
        return value.toUpperCase().startsWith(UNKNOWN);
    }

    private String formatDegreeValue(String value) {        
        Long directionDegree = Long.valueOf(value) * 10;
        return String.format(FORMAT_OUTPUT_VALUE, directionDegree);
    } 
}
