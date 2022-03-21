package br.com.observation;

import java.math.BigInteger;

public class DirectionVaryingValue {

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
        Long differenceOfDirection = calculateDifference(this.fromDirectionValue, this.toDirectionValue);
        if (differenceOfDirection >= 180) return true;
        if (this.upperSixtyLowerOneHundredEighty(differenceOfDirection) && speed.longValue() < 3) return Boolean.TRUE;
        return Boolean.FALSE;
    }    

    private Long calculateDifference(BigInteger from, BigInteger to) {
        Long difference = from.subtract(to).longValue();
        return difference >= 0 ? difference : difference * -1; 
    }



    private Boolean upperSixtyLowerOneHundredEighty(Long direction) {
        return direction >= 60 && direction < 180;
    }
    
}
