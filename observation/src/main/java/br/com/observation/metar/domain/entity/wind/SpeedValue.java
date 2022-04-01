package br.com.observation.metar.domain.entity.wind;

import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;

public class SpeedValue {

    private static final String FORMAT_OUTPUT_SPEED_GUST_VALUE = "%s%s";
    private static final String FORMAT_OUTPUT_GUST_VALUE = "G%s";
    private static final String EMPTY = "//";
    private static final String FORMAT_OUTPUT_VALUE = "%02d";
    private static final Long MAX_VALUE_INPUT = Long.valueOf("100");
    private static final String PLUS_99_KT_OUTPUT_VALUE = "P99";

    private BigInteger speed;

    private BigInteger gust;

    public SpeedValue(BigInteger speed, BigInteger gust) {
        this.speed = speed;
        this.gust = gust;
    }

    public String getValue() {
        if (this.speed == null) return EMPTY;
        final String gustValue = this.generateGustValue();
        final String speedValue = formatValue(this.speed.longValue());        
        return StringUtils.isBlank(gustValue) 
               ? speedValue
               : String.format(FORMAT_OUTPUT_SPEED_GUST_VALUE, speedValue, gustValue);
    }
    
    private String generateGustValue() {
        if (this.gust == null || this.speed == null) return StringUtils.EMPTY;
        final Boolean isExceededInTen = gustExceedsTenKt(this.speed); 
        return isExceededInTen ? String.format(FORMAT_OUTPUT_GUST_VALUE, formatValue(this.gust.longValue())) : StringUtils.EMPTY;
    }

    private Boolean gustExceedsTenKt(BigInteger value) {        
        return this.gust.subtract(value).longValue() > 10;
    } 

    private String formatValue(Long value) {                
        final Boolean isWindSpeedOverOneHundredKnots = value > MAX_VALUE_INPUT;
        if (isWindSpeedOverOneHundredKnots) return PLUS_99_KT_OUTPUT_VALUE;
        return String.format(FORMAT_OUTPUT_VALUE, value);
    }
}
