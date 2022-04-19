package br.com.observation.metar.domain.rvr;

public class IncrementRVRValue {

    public static Boolean invalid(Long value) {
        if (value == null) return Boolean.FALSE;        
        Boolean isValueValid = isDivisibleBy(value);
        return !isValueValid;
    }

    private static Boolean isDivisibleBy(Long value) {
        if (value<=400) return value % 25 == 0;
        if (value>400 && value<=800) return value % 50 == 0;        
        return value % 100 == 0;
    }
    
}
