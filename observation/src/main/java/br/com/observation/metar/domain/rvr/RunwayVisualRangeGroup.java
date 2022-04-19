package br.com.observation.metar.domain.rvr;

public class RunwayVisualRangeGroup {

    private RunwayValue runwayValue;

    private VisualRangeValue visualRangeValue;
    
    public RunwayVisualRangeGroup(String runway, Long value) throws Exception {
        this.runwayValue = new RunwayValue(runway);
        this.visualRangeValue = new VisualRangeValue(value, null);
    }

    public RunwayVisualRangeGroup(String runway, Long value, String tendendy) throws Exception {
        this.runwayValue = new RunwayValue(runway);
        this.visualRangeValue = new VisualRangeValue(value, tendendy);
    }

    public String encode() {
        return String.format("R%s/%s", this.runwayValue.getValue(), this.visualRangeValue.getValue());
    }
    
}
