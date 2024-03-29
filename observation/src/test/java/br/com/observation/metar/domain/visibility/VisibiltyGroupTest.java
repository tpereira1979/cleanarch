package br.com.observation.metar.domain.visibility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class VisibiltyGroupTest {

    @Test
    public void shouldEncodeVisibiltyOverThanTwentyTousandMeters() {
        try {
            VisibilityGroup visibility = new VisibilityGroup(20000l, null);
            assertEquals("9999", visibility.encode(), "Codificando visibilidade acima de 10 km");            
        } catch (Exception e) {
            fail("Erro codifica valores de visiblidade acima de 10 km");
        }
    }
    
    @Test
    public void shouldEncodeVisibiltyLessThanTwentyTousandMeters() {
        try {
            VisibilityGroup visibility = new VisibilityGroup(1500l, null);
            assertEquals("1500", visibility.encode(), "Codificando visibilidade abaixo de 20000 metros");            
        } catch (Exception e) {
            fail("Erro ao codificar valores de visiblidade acima de 2000 metros");
        }
    }

    @Test
    public void shouldEncodeVisibiltyLessThanOneHundredMeters() {
        try {
            VisibilityGroup visibility = new VisibilityGroup(50l, null);
            assertEquals("0050", visibility.encode(), "Codificando visibilidade abaixo de 100 metros");            
        } catch (Exception e) {
            fail("Erro ao codificar valores de visiblidade abaix de 100 metros");
        }
    }

    @Test
    public void shouldNotEncodeMinimumVisibility() throws Exception {
        VisibilityGroup visibility = new VisibilityGroup(2100l, 1800l);
        assertEquals("2100", visibility.encode(), "Não codificar visibilidade minima acima de 1500 metros");
        VisibilityGroup visibilityEquals = new VisibilityGroup(1000l, 1000l);
        assertEquals("1000", visibilityEquals.encode(), "Não codificar visibilidade minima quando estiver igual a predominante");
        VisibilityGroup halfVisibilityEquals = new VisibilityGroup(3000l, 1500l);
        assertEquals("3000", halfVisibilityEquals.encode(), "Não codificar a visibilidade minima com valores igual a 50% da predominante e inferior a 5000 metros");
        VisibilityGroup halfVisibilityUpper = new VisibilityGroup(3000l, 2000l);
        assertEquals("3000", halfVisibilityUpper.encode(), "Não codificar visibilidade minima com valores maior que 50% da predominante e inferior a 5000 metros");
        VisibilityGroup halfVisibilityMinimumUpperFiveThousand = new VisibilityGroup(12000l, 7000l);
        assertEquals("9999", halfVisibilityMinimumUpperFiveThousand.encode(), "Não codificar visibilidade minima com valores maior que 50% da predominante e superior a 5000 metros");        
        VisibilityGroup halfVisibilityUpperTenKm = new VisibilityGroup(20000l, 7000l);
        assertEquals("9999", halfVisibilityUpperTenKm.encode(), "Não codificar visibilidade minima com valores menor que 50% da predominante e superior a 5000 metros");        
    }

    @Test
    public void shouldEncodeMinimumVisibility() throws Exception {
        VisibilityGroup visibility = new VisibilityGroup(1600l, 1000l);
        assertEquals("1600 1000", visibility.encode(), "Codificando visibilidade minima abaixo de 1500 metros");        
    }

    @Test
    public void shouldEncodeMinimumWithHalfPrevalingValue() throws Exception {
        VisibilityGroup visibility = new VisibilityGroup(1600l, 700l);
        assertEquals("1600 0700", visibility.encode(), "Codificando visibilidade minima com valores inferiores a 50% da predominante e inferior a 1500 metros");     
    }

    @Test
    public void shouldEncodeMinimumWithDirection() throws Exception {
        VisibilityGroup visibility = new VisibilityGroup(1600l, 700l, "NE");
        assertEquals("1600 0700NE", visibility.encode(), "Codificando visibilidade minima com direção e valores inferior a 1500 metros");
        
        VisibilityGroup visibilityUpperFiveThousandValue = new VisibilityGroup(6000l, 2000l, "E");
        assertEquals("6000 2000E", visibilityUpperFiveThousandValue.encode(), "Codificando visibilidade minima com valores inferiores a 50% da predominante e inferior a 5000 metros");
    }

    @Test
    public void shouldEncodeInvalidVisibiltyForFiftyMeterInc() {
        final Exception prevalingException = assertThrows(Exception.class, () -> {
            new VisibilityGroup(51l, null);
        });
        assertEquals("Valor de visibilidade predominante invalido", prevalingException.getMessage(), "Validando valores de visibilidade predominante invalidos para incrementos de até 50 metros");
        final Exception minimumException = assertThrows(Exception.class, () -> {
            new VisibilityGroup(50l, 51l);
        });
        assertEquals("Valor de visibilidade minima invalido", minimumException.getMessage(), "Validando valores de visibilidade minima invalidos para incrementos de até 50 metros");
    }

    @Test
    public void shouldEncodeInvalidVisibiltyForHundredMeterInc() {
        final Exception prevalingException = assertThrows(Exception.class, () -> {
            new VisibilityGroup(115l, null);
        });
        assertEquals("Valor de visibilidade predominante invalido", prevalingException.getMessage(), "Validando valores de visibilidade predominante invalidos para incrementos de até 100 metros");
        final Exception minimumException = assertThrows(Exception.class, () -> {
            new VisibilityGroup(100l, 116l);
        });
        assertEquals("Valor de visibilidade minima invalido", minimumException.getMessage(), "Validando valores de visibilidade minina invalidos para incrementos de até 100 metros");
    }

    @Test
    public void shouldEncodeInvalidVisibiltyForThousandMeterInc() {
        final Exception prevalingException = assertThrows(Exception.class, () -> {
            new VisibilityGroup(1535l, null);
        });
        assertEquals("Valor de visibilidade predominante invalido", prevalingException.getMessage(), "Validando valores de visibilidade predominante invalidos para incrementos de até 1000 metros");
        final Exception minimumException = assertThrows(Exception.class, () -> {
            new VisibilityGroup(1500l, 1435l);
        });
        assertEquals("Valor de visibilidade minima invalido", minimumException.getMessage(), "Validando valores de visibilidade minima invalidos para incrementos de até 1000 metros");
    }    

    @Test
    public void shouldEncodeVisibiltyIsNotExist() {
        try {
            VisibilityGroup visibility = new VisibilityGroup(null, null);
            assertEquals("////", visibility.encode(), "Codifica valor de visilidade nao informado");
            VisibilityGroup withVisibilityMinimum = new VisibilityGroup(null, 7000l);
            assertEquals("////", withVisibilityMinimum.encode(), "Codificando visibilidade minima sem visiblidade predominante");            
        } catch (Exception e) {
            fail("Erro codifica valores de visiblidade sem valor");
        }
    }

    
    
}
