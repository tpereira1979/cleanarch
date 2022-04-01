package br.com.observation.metar.domain.entity.visibility;

public class PrevalingValue {

    private static final int VISIBILITY_LIMIT = 10000;
    private static final String VISIBILITY_OVER_THAN_TEN_KM = "9999";
    private Long value;

    public PrevalingValue(Long value) throws Exception {
        if (this.isInValid(value)) throw new Exception("Valor de visibilidade predominante invalido");
        this.value = value;
    }
    
    public String getValue() {
        if (this.value == null) return "////";
        return this.value >= VISIBILITY_LIMIT ? VISIBILITY_OVER_THAN_TEN_KM : String.format("%04d", this.value);
    }

    private Boolean isInValid(Long value) {
        if (value == null) return Boolean.FALSE;
        if (value >= VISIBILITY_LIMIT) return Boolean.FALSE;
        Boolean isValueValid = this.isDivisibleBy(value, 50l) 
                               || this.isDivisibleBy(value, 100l)
                               || this.isDivisibleBy(value, 1000l);
        return !isValueValid;
    }

    private Boolean isDivisibleBy(Long value, Long by) {
        return value % by == 0;
    }
    
}
