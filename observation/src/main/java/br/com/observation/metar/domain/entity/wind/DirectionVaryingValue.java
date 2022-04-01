package br.com.observation.metar.domain.entity.wind;

import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;

public class DirectionVaryingValue {

    private static final String FORMAT_OUTPUT = "%03dV%03d";
    private static final Long DIRECTION_VARYING_VALUE_MAX = Long.valueOf(180);
    private static final Long DIRECTION_VARYING_VALUE_MIN = Long.valueOf(60);

    private BigInteger fromDirectionValue;

    private BigInteger toDirectionValue;

    private BigInteger speed;

    public DirectionVaryingValue(BigInteger fromDirectionValue, BigInteger toDirectionValue, BigInteger speed) {
        this.fromDirectionValue = fromDirectionValue;
        this.toDirectionValue = toDirectionValue;
        this.speed = speed;
    }

    public Boolean isVRB() {
        if (windVariationValueIsEmpty()) return Boolean.FALSE;                        
        final Long differenceOfDirection = calculateDifference(this.fromDirectionValue, this.toDirectionValue);        
        if (differenceOfDirection > DIRECTION_VARYING_VALUE_MAX) return Boolean.TRUE;
        if (this.directionGreaterThanOrEqualSixtyAndLowerOneHundredEighty(differenceOfDirection) && isSpeedLessThreeKnots(this.speed)) return Boolean.TRUE;
        return Boolean.FALSE;
    }
    
    public String getValue() {
        if (windVariationValueIsEmpty()) return StringUtils.EMPTY;        
        return (this.hasDirectionVariationValid()) ? formatDirectionVaryingValue(this.fromDirectionValue, this.toDirectionValue) : StringUtils.EMPTY;
    }

    private Boolean windVariationValueIsEmpty() {
        return this.fromDirectionValue == null || this.toDirectionValue == null;
    }
    

    private Boolean hasDirectionVariationValid() {
        final Long differenceOfDirection = calculateDifference(this.fromDirectionValue, this.toDirectionValue);
        return this.directionGreaterThanOrEqualSixtyAndLowerOneHundredEighty(differenceOfDirection) && isSpeedUpperOrEqualsThreeKnots(this.speed);        
    }   
    
    
    private String formatDirectionVaryingValue(BigInteger from, BigInteger to) {
        return String.format(FORMAT_OUTPUT, from, to);
    }

    private Boolean isSpeedLessThreeKnots(BigInteger speed) {
        return speed != null && speed.longValue() < 3;
    }
    
    private Boolean isSpeedUpperOrEqualsThreeKnots(BigInteger speed) {
        return speed != null && speed.longValue() >= 3;
    }

    private Long calculateDifference(BigInteger from, BigInteger to) {
        Long difference = from.subtract(to).longValue();
        return difference >= 0 ? difference : (difference * -1); 
    }

    private Boolean directionGreaterThanOrEqualSixtyAndLowerOneHundredEighty(Long direction) {
        return direction >= DIRECTION_VARYING_VALUE_MIN && direction < DIRECTION_VARYING_VALUE_MAX;
    }   
    
}
