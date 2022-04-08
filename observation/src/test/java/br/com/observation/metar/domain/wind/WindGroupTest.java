package br.com.observation.metar.domain.wind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

public class WindGroupTest {


    @Test
    public void shouldGenerateCalmWind() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("000"), new BigInteger("00"), null);        
        assertEquals("00000KT", windMetarGroup.generate(), "Codificando vento calmo");
    }

    @Test
    public void shouldGenerateNoWindValue() {
        WindGroup windMetarGroup = new WindGroup(null, null, null);        
        assertEquals("/////KT", windMetarGroup.generate(), "Codificando vento calmo");
    }

    @Test
    public void shouldGenerateUnknownDirectionValue() {
        WindGroup windMetarGroup = new WindGroup(null, new BigInteger("10"), null);
        assertEquals("///10KT", windMetarGroup.generate(), "Codificando vento com direcao inesistente");
    }
    

    @Test
    public void shouldGenerateUnknownSpeedValue() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("100"), null, null);
        assertEquals("100//KT", windMetarGroup.generate(), "Codificando vento com velocidade inesistente");
    }

    @Test
    public void shouldGenerateWindValueOverTenKt() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("120"), new BigInteger("10"), null);
        assertEquals("12010KT", windMetarGroup.generate(), "Codificando vento com valores acima de 100 graus e maiores que 1 kt");
    }

    @Test
    public void shouldGenerateWindValueLessTenKt() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("20"), new BigInteger("10"), null);
        assertEquals("02010KT", windMetarGroup.generate(), "Codificando vento com valores abaixo de 100 graus e maiores que 1 kt");
    }

    @Test
    public void shouldGenerateWindSpeedValueOverHundredKnots() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("20"), new BigInteger("120"), null);
        assertEquals("020P99KT", windMetarGroup.generate(), "Codificando vento com valores abaixo de 100 graus e maiores que 100 kt");
    }

    @Test
    public void shouldGenerateWindSpeedValueLessTenKnots() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("20"), new BigInteger("02"), null);
        assertEquals("02002KT", windMetarGroup.generate(), "Codificando vento com valores abaixo de 100 graus e menores que 10 kt");
    }

    @Test
    public void shouldGenerateWindVRBDirectionWithSppedLowerTheeKnots() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("60"), new BigInteger("02"), null, new BigInteger("60"), new BigInteger("120"));
        assertEquals("VRB02KT", windMetarGroup.generate(), "Codificar VRB quando direcao do vento estiver variando entre 60 e 180 graus com velocidade inferior a 3kt");
    }
    
    @Test
    public void shouldGenerateWindVRBDirectionWithDifferenceOfDirectionUpOneHundredEighty() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("60"), new BigInteger("04"), null, new BigInteger("20"), new BigInteger("210"));
        assertEquals("VRB04KT", windMetarGroup.generate(), "Codificar VRB quando a variacao do vento ultrapssar 180 graus com qualquer valor de velocidade");
    }

    @Test
    public void shouldGenerateWindVRBDirectionWithNoSpeedValue() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("60"), null, null, new BigInteger("10"), new BigInteger("210"));
        assertEquals("VRB//KT", windMetarGroup.generate(), "Codificar VRB quando a variacao do vento ultrapssar 180 graus com qualquer valor de velocidade");
    }

    @Test
    public void shouldGenerateWindVRBDirectionWithOverHundredKnots() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("60"), new BigInteger("120"), null, new BigInteger("20"), new BigInteger("210"));
        assertEquals("VRBP99KT", windMetarGroup.generate(), "Codificar VRB quando a variacao do vento ultrapssar 180 graus com qualquer valor de velocidade");
    }
    
    
    @Test
    public void shouldIsNotGerateWindWithGustValue() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("60"), new BigInteger("20"), new BigInteger("30"));
        assertEquals("06020KT", windMetarGroup.generate(), "Nao Codifica rajada quando o valor da rajada não exceder em 10 em relacao a velocidade");

    }
    
    @Test
    public void shouldGerateWindWithGustValue() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("60"), new BigInteger("20"), new BigInteger("80"));
        assertEquals("06020G80KT", windMetarGroup.generate(), "Codifica rajada de vento");

    }

    @Test
    public void shouldGerateWindWithGustValueWithOverHundredKnots() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("60"), new BigInteger("20"), new BigInteger("130"));
        assertEquals("06020GP99KT", windMetarGroup.generate(), "Codifica rajada de vento com valores acima de 100 kt");

    }

    @Test
    public void shouldGerateWindWithVRBDirectionAndGustValue() {
        final BigInteger direction = new BigInteger("60");
        final BigInteger speed = new BigInteger("10");
        final BigInteger gust = new BigInteger("21");
        final BigInteger fromDirection = new BigInteger("20");
        final BigInteger toDirection = new BigInteger("210");
        WindGroup windMetarGroup = new WindGroup(direction,speed,gust,fromDirection,toDirection);
        assertEquals("VRB10G21KT", windMetarGroup.generate(), "Codifica rajada de vento com vento variando");
    }

    @Test
    public void shouldGenerateWindWithVaryingValue() {
        final BigInteger direction = new BigInteger("60");
        final BigInteger speed = new BigInteger("10");
        final BigInteger gust = null;
        final BigInteger fromDirection = new BigInteger("40");
        final BigInteger toDirection = new BigInteger("200");
        WindGroup windMetarGroup = new WindGroup(direction,speed,gust,fromDirection,toDirection);
        assertEquals("06010KT 040V200", windMetarGroup.generate(), "Codifica a variacao do vento quando diferença da direção for superior " + 
            "ou igual 60 e inferior 180 graus com a velocidade do vento superior ou igual 3 nos");

    }
    
    
}
