package br.com.observation.metar.domain.entity.visibility;

public class VisibilityGroup {

    private PrevalingValue prevalingValue;

    public VisibilityGroup(Long prevalingValue) throws Exception {
        this.prevalingValue = new PrevalingValue(prevalingValue);
    }

    public String generate() {        
        return String.format("%s", this.prevalingValue.getValue());
    }


    
}
