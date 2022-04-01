package br.com.observation.metar.domain.entity.visibility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class VisibiltyGroupTest {

    @Test
    public void shouldGenerateVisibiltyOverThanTwentyTousandMeters() {
        try {
            VisibilityGroup visibility = new VisibilityGroup(20000l);
            assertEquals("9999", visibility.generate(), "Codificando visibilidade acima de 10 km");            
        } catch (Exception e) {
            fail("Erro codifica valores de visiblidade acima de 10 km");
        }
    }
    
    @Test
    public void shouldGenerateVisibiltyLessThanTwentyTousandMeters() {
        try {
            VisibilityGroup visibility = new VisibilityGroup(1500l);
            assertEquals("1500", visibility.generate(), "Codificando visibilidade abaixo de 20000 metros");            
        } catch (Exception e) {
            fail("Erro ao codificar valores de visiblidade acima de 2000 metros");
        }
    }

    @Test
    public void shouldGenerateVisibiltyLessThanOneHundredMeters() {
        try {
            VisibilityGroup visibility = new VisibilityGroup(50l);
            assertEquals("0050", visibility.generate(), "Codificando visibilidade abaixo de 100 metros");            
        } catch (Exception e) {
            fail("Erro ao codificar valores de visiblidade abaix de 100 metros");
        }
    }

    @Test
    public void shouldGenerateInvalidVisibiltyForFiftyMeterInc() {
        final Exception exception = assertThrows(Exception.class, () -> {
            new VisibilityGroup(51l);
        });
        assertEquals("Valor de visibilidade predominante invalido", exception.getMessage(), "Validando valores de visibilidade invalidos para incrementos de 50 metros");
    }

    @Test
    public void shouldGenerateInvalidVisibiltyForHundredMeterInc() {
        final Exception exception = assertThrows(Exception.class, () -> {
            new VisibilityGroup(115l);
        });
        assertEquals("Valor de visibilidade predominante invalido", exception.getMessage(), "Validando valores de visibilidade invalidos para incrementos de 100 metros");
    }

    @Test
    public void shouldGenerateInvalidVisibiltyForThousandMeterInc() {
        final Exception exception = assertThrows(Exception.class, () -> {
            new VisibilityGroup(1535l);
        });
        assertEquals("Valor de visibilidade predominante invalido", exception.getMessage(), "Validando valores de visibilidade invalidos para incrementos de 1000 metros");
    }

    @Test
    public void shouldGenerateVisibiltyIsNotExist() {
        try {
            VisibilityGroup visibility = new VisibilityGroup(null);
            assertEquals("////", visibility.generate(), "Codifica valor de visilidade nao informado");            
        } catch (Exception e) {
            fail("Erro codifica valores de visiblidade sem valor");
        }
    }
    
}
