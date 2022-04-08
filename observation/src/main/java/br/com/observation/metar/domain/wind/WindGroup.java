package br.com.observation.metar.domain.wind;

import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;

public class WindGroup {

    private static final String VRB_VALUE = "VRB";

    private static final String FORMAT_OUTPUT_VALUE_WITH_DIRECTION = "%s%sKT %s";

    private static final String FORMAT_OUTPUT_VALUE = "%s%sKT";
    
    private DirectionValue direction;
    
    private SpeedValue speed;

    private DirectionVaryingValue directionVarying; 

    public WindGroup(BigInteger direction, BigInteger speed, BigInteger gust) {
        this.direction = new DirectionValue(direction);
        this.speed = new SpeedValue(speed, gust);        
        this.directionVarying = new DirectionVaryingValue(null, null, speed);
    }    

    public WindGroup(BigInteger direction, 
                          BigInteger speed,
                          BigInteger gust,
                          BigInteger fromDirectionValue,
                          BigInteger toDirectionValue) {                              
        this.direction = new DirectionValue(direction);
        this.speed = new SpeedValue(speed, gust);
        this.directionVarying = new DirectionVaryingValue(fromDirectionValue, toDirectionValue, speed);
    }

    public String generate() {        
        if (directionVarying.isVRB()) return formatGroup(VRB_VALUE, speed.getValue());        
        return StringUtils.isBlank(directionVarying.getValue()) 
                ? formatGroup(direction.getValue(), speed.getValue()) 
                : formatGroup(direction.getValue(), speed.getValue(), directionVarying.getValue());
    }

    private String formatGroup(String direction, String speed) {
        return String.format(FORMAT_OUTPUT_VALUE, direction, speed);
    }

    private String formatGroup(String direction, String speed, String directionVarying) {
        return String.format(FORMAT_OUTPUT_VALUE_WITH_DIRECTION, direction, speed, directionVarying);
    }


}
