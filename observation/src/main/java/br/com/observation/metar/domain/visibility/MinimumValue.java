package br.com.observation.metar.domain.visibility;

import org.apache.commons.lang3.StringUtils;

public class MinimumValue {

    private Long value;

    private Long prevalingValue;

    private String direction;
        
    public MinimumValue(final Long value, final Long prevalingValue, String direction) throws Exception {
        if (isInValid(value)) throw new Exception("Valor de visibilidade minima invalido");
        this.value = value;
        this.prevalingValue = prevalingValue;
        this.direction = direction;
    }
    
    public String getValue() {
        if (this.value == null) return StringUtils.EMPTY;
        if (this.value.equals(this.prevalingValue)) return StringUtils.EMPTY;
        if (this.value < 1500l) return formatValue(this.value, this.direction);
        return isHalfPrevalingVisibilityValue() && isLessFiveThounsandMeters() 
               ? formatValue(this.value, this.direction)
               : StringUtils.EMPTY;
    }
    
    private Boolean isInValid(Long value) {
        if (value == null) return Boolean.FALSE;        
        return IncrementVisibilityValue.isInValid(value);
    }

    private String formatValue(Long value, String direction) {
        return StringUtils.isBlank(direction) 
               ? String.format("%04d", value)
               : String.format("%04d%s", value, direction);
    }

    private boolean isLessFiveThounsandMeters() {
        return this.value < 5000;
    }

    private Boolean isHalfPrevalingVisibilityValue() {
        return this.prevalingValue != null 
               ? this.value < (this.prevalingValue/2) 
               : Boolean.FALSE;
    }
    
}
