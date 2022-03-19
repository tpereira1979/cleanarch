package br.com.observation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class WindGroupTest {


    @Test
    public void shouldGenerateCalmWind() {
        WindMetarGroup windMetarGroup = new WindMetarGroup("00", "00");        
        assertEquals("00000KT", windMetarGroup.generate(), "Codificando vento calmao");
    }

    @Test
    public void shouldGenerateNoWindValue() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(null, null);        
        assertEquals("/////KT", windMetarGroup.generate(), "Codificando vento calmao");
    }

    @Test
    public void shouldGenerateUnknownDirectionValue() {
        WindMetarGroup windMetarGroup = new WindMetarGroup("X", "10");
        assertEquals("///10KT", windMetarGroup.generate(), "Codificando vento com direcao inesistente");
    }
    
    @Test
    public void shouldGenerateUnknownDirectionValueWithTwoX() {
        WindMetarGroup windMetarGroupXX = new WindMetarGroup("XX", "10");
        assertEquals("///10KT", windMetarGroupXX.generate(), "Codificando vento com direcao inesistente com XX");        
    }

    @Test
    public void shouldGenerateUnknownSpeedValueWithTwoX() {
        WindMetarGroup windMetarGroup = new WindMetarGroup("10", "xx");
        assertEquals("100//KT", windMetarGroup.generate(), "Codificando vento com velocidade inesistente com XX");
    }

    @Test
    public void shouldGenerateUnknownSpeedValue() {
        WindMetarGroup windMetarGroup = new WindMetarGroup("10", "x");
        assertEquals("100//KT", windMetarGroup.generate(), "Codificando vento com direcao inesistente");
    }

    @Test
    public void shouldGenerateWindValueOverTenKt() {
        WindMetarGroup windMetarGroup = new WindMetarGroup("12", "10");
        assertEquals("12010KT", windMetarGroup.generate(), "Codificando vento com valores acima de 100 graus e maiores que 1 kt");
    }

    @Test
    public void shouldGenerateWindValueLessTenKt() {
        WindMetarGroup windMetarGroup = new WindMetarGroup("02", "10");
        assertEquals("02010KT", windMetarGroup.generate(), "Codificando vento com valores abaixo de 100 graus e maiores que 1 kt");
    }

    @Test
    public void shouldGenerateWindSpeedValueOverHundredKnots() {
        WindMetarGroup windMetarGroup = new WindMetarGroup("02", "120");
        assertEquals("020P99KT", windMetarGroup.generate(), "Codificando vento com valores abaixo de 100 graus e maiores que 100 kt");
    }

    @Test
    public void shouldGenerateWindSpeedValueLessTenKnots() {
        WindMetarGroup windMetarGroup = new WindMetarGroup("02", "02");
        assertEquals("02002KT", windMetarGroup.generate(), "Codificando vento com valores abaixo de 100 graus e menores que 10 kt");
    }

    
    
}
