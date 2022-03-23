package br.com.observation;

import java.math.BigInteger;

public class WindMetarGroup {

    private static final String FORMAT_OUTPUT_VRB_VALUE = "VRB%sKT";

    private static final String FORMAT_OUTPUT_VALUE = "%s%sKT";
    
    private DirectionValue direction;
    
    private SpeedValue speed;

    private DirectionVaryingValue directionVarying; 

    public WindMetarGroup(BigInteger direction, BigInteger speed, BigInteger gust) {
        this.direction = new DirectionValue(direction);
        this.speed = new SpeedValue(speed, gust);        
        this.directionVarying = new DirectionVaryingValue(null, null, speed);
    }    

    public WindMetarGroup(BigInteger direction, BigInteger speed, BigInteger gust, BigInteger fromDirectionValue, BigInteger toDirectionValue) {
        this.direction = new DirectionValue(direction);
        this.speed = new SpeedValue(speed, gust);
        this.directionVarying = new DirectionVaryingValue(fromDirectionValue, toDirectionValue, speed);
    }

    public String generate() {
        if (directionVarying.isVRB()) return String.format(FORMAT_OUTPUT_VRB_VALUE, speed.getValue());
        return String.format(FORMAT_OUTPUT_VALUE, direction.getValue(), speed.getValue());
    }
}
