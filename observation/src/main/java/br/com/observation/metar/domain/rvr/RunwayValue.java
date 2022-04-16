package br.com.observation.metar.domain.rvr;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

public class RunwayValue {

    private String value;

    public RunwayValue(String value) throws Exception {
        if (this.isInvalid(value)) throw new Exception("Numero da pista inválido");
        this.value = value;
    }

    public String getValue() {
        return String.format("%s", formatRunwayNumber(value));
    }
    
    private String formatRunwayNumber(String value) {
        if (StringUtils.isNumeric(value)) return String.format("%02d", Long.valueOf(value));
        final String position = RegExUtils.removePattern(value, "[0-9]");
        final String runwayNumber = RegExUtils.removePattern(value, "[^0-9]");        
        return String.format("%02d%s", Long.valueOf(runwayNumber), position.toUpperCase());
    }

    private Boolean isInvalid(String value) {
        return !value.matches("([0-9]{1,2}[L|l|R|r|C|c])|[0-9]{1,2}");
    }   
    
}
