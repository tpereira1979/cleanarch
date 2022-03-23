package br.com.observation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;


public class WindGroupTest {


    @Test
    public void shouldGenerateCalmWind() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("000"), new BigInteger("00"), null);        
        assertEquals("00000KT", windMetarGroup.generate(), "Codificando vento calmo");
    }

    @Test
    public void shouldGenerateNoWindValue() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(null, null, null);        
        assertEquals("/////KT", windMetarGroup.generate(), "Codificando vento calmo");
    }

    @Test
    public void shouldGenerateUnknownDirectionValue() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(null, new BigInteger("10"), null);
        assertEquals("///10KT", windMetarGroup.generate(), "Codificando vento com direcao inesistente");
    }
    

    @Test
    public void shouldGenerateUnknownSpeedValue() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("100"), null, null);
        assertEquals("100//KT", windMetarGroup.generate(), "Codificando vento com velocidade inesistente");
    }

    @Test
    public void shouldGenerateWindValueOverTenKt() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("120"), new BigInteger("10"), null);
        assertEquals("12010KT", windMetarGroup.generate(), "Codificando vento com valores acima de 100 graus e maiores que 1 kt");
    }

    @Test
    public void shouldGenerateWindValueLessTenKt() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("20"), new BigInteger("10"), null);
        assertEquals("02010KT", windMetarGroup.generate(), "Codificando vento com valores abaixo de 100 graus e maiores que 1 kt");
    }

    @Test
    public void shouldGenerateWindSpeedValueOverHundredKnots() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("20"), new BigInteger("120"), null);
        assertEquals("020P99KT", windMetarGroup.generate(), "Codificando vento com valores abaixo de 100 graus e maiores que 100 kt");
    }

    @Test
    public void shouldGenerateWindSpeedValueLessTenKnots() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("20"), new BigInteger("02"), null);
        assertEquals("02002KT", windMetarGroup.generate(), "Codificando vento com valores abaixo de 100 graus e menores que 10 kt");
    }

    @Test
    public void shouldGenerateWindVRBDirectionWithSppedLowerTheeKnots() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("60"), new BigInteger("02"), null, new BigInteger("60"), new BigInteger("120"));
        assertEquals("VRB02KT", windMetarGroup.generate(), "Codificar VRB quando direcao do vento estiver variando entre 60 e 180 graus com velocidade inferior a 3kt");
    }
    
    @Test
    public void shouldGenerateWindVRBDirectionWithDifferenceOfDirectionUpOneHundredEighty() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("60"), new BigInteger("04"), null, new BigInteger("20"), new BigInteger("210"));
        assertEquals("VRB04KT", windMetarGroup.generate(), "Codificar VRB quando a variacao do vento ultrapssar 180 graus com qualquer valor de velocidade");
    }

    @Test
    public void shouldGenerateWindVRBDirectionWithNoSpeedValue() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("60"), null, null, new BigInteger("10"), new BigInteger("210"));
        assertEquals("VRB//KT", windMetarGroup.generate(), "Codificar VRB quando a variacao do vento ultrapssar 180 graus com qualquer valor de velocidade");
    }

    @Test
    public void shouldGenerateWindVRBDirectionWithOverHundredKnots() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("60"), new BigInteger("120"), null, new BigInteger("20"), new BigInteger("210"));
        assertEquals("VRBP99KT", windMetarGroup.generate(), "Codificar VRB quando a variacao do vento ultrapssar 180 graus com qualquer valor de velocidade");
    }
    
    
    @Test
    public void shouldIsNotGerateWindWithGustValue() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("60"), new BigInteger("20"), new BigInteger("30"));
        assertEquals("06020KT", windMetarGroup.generate(), "Nao Codifica rajada quando o valor da rajada não exceder em 10 em relacao a velocidade");

    }
    
    @Test
    public void shouldGerateWindWithGustValue() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("60"), new BigInteger("20"), new BigInteger("80"));
        assertEquals("06020G80KT", windMetarGroup.generate(), "Codifica rajada de vento");

    }

    @Test
    public void shouldGerateWindWithGustValueWithOverHundredKnots() {
        WindMetarGroup windMetarGroup = new WindMetarGroup(new BigInteger("60"), new BigInteger("20"), new BigInteger("130"));
        assertEquals("06020GP99KT", windMetarGroup.generate(), "Codifica rajada de vento com valores acima de 100 kt");

    }

    @Test
    public void shouldGerateWindWithVRBDirectionAndGustValue() {
        final BigInteger direction = new BigInteger("60");
        final BigInteger speed = new BigInteger("10");
        final BigInteger gust = new BigInteger("21");
        final BigInteger fromDirection = new BigInteger("20");
        final BigInteger toDirection = new BigInteger("210");
        WindMetarGroup windMetarGroup = new WindMetarGroup(direction,speed,gust,fromDirection,toDirection);
        assertEquals("VRB10G21KT", windMetarGroup.generate(), "Codifica rajada de vento com vento variando");
    }
    
    
}
