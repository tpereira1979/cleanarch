package br.com.observation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;


public class WindGroupTest {


    @Test
    public void shouldGenerateCalmWind() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("000"), new BigInteger("00"));        
        assertEquals("00000KT", windMetarGroup.generate(), "Codificando vento calmao");
    }

    @Test
    public void shouldGenerateNoWindValue() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(null, null);        
        assertEquals("/////KT", windMetarGroup.generate(), "Codificando vento calmao");
    }

    @Test
    public void shouldGenerateUnknownDirectionValue() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(null, new BigInteger("10"));
        assertEquals("///10KT", windMetarGroup.generate(), "Codificando vento com direcao inesistente");
    }
    

    @Test
    public void shouldGenerateUnknownSpeedValue() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("100"), null);
        assertEquals("100//KT", windMetarGroup.generate(), "Codificando vento com velocidade inesistente");
    }

    @Test
    public void shouldGenerateWindValueOverTenKt() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("120"), new BigInteger("10"));
        assertEquals("12010KT", windMetarGroup.generate(), "Codificando vento com valores acima de 100 graus e maiores que 1 kt");
    }

    @Test
    public void shouldGenerateWindValueLessTenKt() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("20"), new BigInteger("10"));
        assertEquals("02010KT", windMetarGroup.generate(), "Codificando vento com valores abaixo de 100 graus e maiores que 1 kt");
    }

    @Test
    public void shouldGenerateWindSpeedValueOverHundredKnots() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("20"), new BigInteger("120"));
        assertEquals("020P99KT", windMetarGroup.generate(), "Codificando vento com valores abaixo de 100 graus e maiores que 100 kt");
    }

    @Test
    public void shouldGenerateWindSpeedValueLessTenKnots() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("20"), new BigInteger("02"));
        assertEquals("02002KT", windMetarGroup.generate(), "Codificando vento com valores abaixo de 100 graus e menores que 10 kt");
    }

    @Test
    public void shouldGenerateWindVRBDirectionWithSppedLowerTheeKnots() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("60"), new BigInteger("02"), new BigInteger("60"), new BigInteger("120"));
        assertEquals("VRB02KT", windMetarGroup.generate(), "Codificar VRB quando direcao do vento estiver variando entre 60 e 180 graus com velocidade inferior a 3kt");
    }
    
    @Test
    public void shouldGenerateWindVRBDirectionWithDifferenceOfDirectionUpOneHundredEighty() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("60"), new BigInteger("04"), new BigInteger("20"), new BigInteger("210"));
        assertEquals("VRB04KT", windMetarGroup.generate(), "Codificar VRB quando a variacao do vento ultrapssar 180 graus com qualquer valor de velocidade");
    }

    @Test
    public void shouldGenerateWindVRBDirectionWithNoSpeedValue() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("60"), null, new BigInteger("20"), new BigInteger("210"));
        assertEquals("VRB//KT", windMetarGroup.generate(), "Codificar VRB quando a variacao do vento ultrapssar 180 graus com qualquer valor de velocidade");
    }
    
}
