package br.com.observation.metar.domain.rvr;

public class VisualRangeValue {

    private static final String PLUS_TWO_THOUNSAND_METERS = "P2000";
    private static final String MINOR_FIFITY_METERS = "M0050";
    private Long value;

    public VisualRangeValue(Long value) {
        this.value = value;
    }

    public String getValue() { 
        if (this.isLessFiftyMeters()) return MINOR_FIFITY_METERS;
        if (this.isUpperTwoThounsanMeter()) return PLUS_TWO_THOUNSAND_METERS;       
        return String.format("%04d", value);
    }

    private Boolean isUpperTwoThounsanMeter() {
        return this.value>=2000;
    }

    private Boolean isLessFiftyMeters() {
        return this.value<=50;
    }    

}
