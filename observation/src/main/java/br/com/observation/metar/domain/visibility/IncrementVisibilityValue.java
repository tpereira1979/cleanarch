package br.com.observation.metar.domain.visibility;

public class IncrementVisibilityValue {      

    public static Boolean isInValid(Long value) {
        if (value == null) return Boolean.FALSE;                
        Boolean isValueValid = isDivisibleBy(value, 50l) 
                               || isDivisibleBy(value, 100l)
                               || isDivisibleBy(value, 1000l);
        return !isValueValid;
    }
    
    private static Boolean isDivisibleBy(Long value, Long by) {
        return value % by == 0;
    }
}
