package br.com.observation;

public class WindMetarGroup {

    private static final String FORMAT_OUTPUT_VALUE = "%s%sKT";
    
    private DirectionValue direction;

    private SpeedValue speed;

    public WindMetarGroup(String direction, String speed) {
        this.direction = new DirectionValue(direction);
        this.speed = new SpeedValue(speed);
    }

    public String generate() {                
        return String.format(FORMAT_OUTPUT_VALUE, direction.getValue(), speed.getValue());
    }
}
