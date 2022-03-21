package br.com.observation;

import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;

public class WindMetarGroup {

    private static final String FORMAT_OUTPUT_VRB_VALUE = "VRB%sKT";

    private static final String FORMAT_OUTPUT_VALUE = "%s%sKT";
    
    private DirectionValue direction;

    private SpeedValue speed;

    private DirectionVaryingValue directionVarying; 

    public WindMetarGroup(BigInteger direction, BigInteger speed) {
        this.direction = new DirectionValue(direction);
        this.speed = new SpeedValue(speed);
    }

    public WindMetarGroup(BigInteger direction, BigInteger speed, BigInteger fromDirectionValue, BigInteger toDirectionValue) {
        this.direction = new DirectionValue(direction);
        this.speed = new SpeedValue(speed);
        this.directionVarying = new DirectionVaryingValue(fromDirectionValue, toDirectionValue, speed);
    }

    public String generate() {
        Boolean isVRB = directionVarying != null && directionVarying.isVRB();
        if (isVRB) return String.format(FORMAT_OUTPUT_VRB_VALUE, speed.getValue());
        return String.format(FORMAT_OUTPUT_VALUE, direction.getValue(), speed.getValue());
    }
}
