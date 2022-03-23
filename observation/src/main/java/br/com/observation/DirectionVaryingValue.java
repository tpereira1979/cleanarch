package br.com.observation;

import java.math.BigInteger;

public class DirectionVaryingValue {

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
        if (this.fromDirectionValue == null || this.toDirectionValue == null) return Boolean.FALSE;
        final Long differenceOfDirection = calculateDifference(this.fromDirectionValue, this.toDirectionValue);        
        if (differenceOfDirection > DIRECTION_VARYING_VALUE_MAX) return true;
        if (this.directionGreaterThanOrEqualSixtyAndLowerOneHundredEighty(differenceOfDirection) && isSpeedLessThreeKnots(this.speed)) return Boolean.TRUE;
        return Boolean.FALSE;
    }

    private boolean isSpeedLessThreeKnots(BigInteger speed) {
        return speed != null && speed.longValue() < 3;
    }    

    private Long calculateDifference(BigInteger from, BigInteger to) {
        Long difference = from.subtract(to).longValue();
        return difference >= 0 ? difference : (difference * -1); 
    }

    private Boolean directionGreaterThanOrEqualSixtyAndLowerOneHundredEighty(Long direction) {
        return direction >= DIRECTION_VARYING_VALUE_MIN && direction < DIRECTION_VARYING_VALUE_MAX;
    }
    
}
