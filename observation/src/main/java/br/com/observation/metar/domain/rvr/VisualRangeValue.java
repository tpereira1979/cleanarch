package br.com.observation.metar.domain.rvr;

import org.apache.commons.lang3.StringUtils;

public class VisualRangeValue {

    private static final String EMPTY_VALUE = "////";
    private static final String PLUS_TWO_THOUNSAND_METERS = "P2000";
    private static final String MINOR_FIFITY_METERS = "M0050";

    private Long value;
    private String tendency;

    public VisualRangeValue(Long value, String tendency) throws Exception {
        if (IncrementRVRValue.invalid(value)) throw new Exception("Valor de RVR inválido");
        if (isTendencyInvalid(tendency)) throw new Exception("Tendencia Inválida");
        this.value = value;
        this.tendency = StringUtils.isNotBlank(tendency) ? tendency : StringUtils.EMPTY;
    }

    private Boolean isTendencyInvalid(String tendency) {
        return StringUtils.isNotBlank(tendency) && !tendency.toUpperCase().matches("[U|N|D]");
    }

    public String getValue() {
        if (this.value == null) return EMPTY_VALUE; 
        if (this.isLessFiftyMeters()) return MINOR_FIFITY_METERS;
        if (this.isUpperTwoThounsantMeters()) return PLUS_TWO_THOUNSAND_METERS;                       
        return String.format("%04d%s", this.value, this.tendency.toUpperCase());
    }

    private Boolean isUpperTwoThounsantMeters() {
        return this.value>=2000;
    }

    private Boolean isLessFiftyMeters() {
        return this.value<=50;
    }    

}
