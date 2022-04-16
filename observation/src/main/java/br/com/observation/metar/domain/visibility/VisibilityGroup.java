package br.com.observation.metar.domain.visibility;

import org.apache.commons.lang3.StringUtils;

public class VisibilityGroup {

    private PrevalingValue prevalingValue;    

    private MinimumValue minimumValue;
    
    public VisibilityGroup(final Long prevalingValue, final Long minimumValue) throws Exception {
        this.prevalingValue = new PrevalingValue(prevalingValue);
        this.minimumValue = new MinimumValue(minimumValue, prevalingValue, null);
    }
    
    public VisibilityGroup(final Long prevalingValue, final Long minimumValue, String direction) throws Exception {
        this.prevalingValue = new PrevalingValue(prevalingValue);
        this.minimumValue = new MinimumValue(minimumValue, prevalingValue, direction);
    }

    public String encode() {        
        return StringUtils.isBlank(this.minimumValue.getValue()) 
               ? String.format("%s", this.prevalingValue.getValue())
               : String.format("%s %s", this.prevalingValue.getValue(), this.minimumValue.getValue());
    }    
}
