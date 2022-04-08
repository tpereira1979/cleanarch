package br.com.observation.metar.domain.wind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

public class DirectionValueTest {

    @Test
    public void shouldGenerateValue() {
        DirectionValue directionValue = new DirectionValue(new BigInteger("200"));
        assertEquals("200", directionValue.getValue(), "Gerando valores de direcao");
    }

    @Test
    public void shouldGenerateEmptyValue() {
        DirectionValue directionValue = new DirectionValue(null);
        assertEquals("///", directionValue.getValue(), "Gerando valores de direcao vazio");
    }
    
}
