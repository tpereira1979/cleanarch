package br.com.observation.metar.domain.wind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

public class WindGroupTest {


    @Test
    public void shouldEncodeCalmWind() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("000"), new BigInteger("00"), null);        
        assertEquals("00000KT", windMetarGroup.encode(), "Codificando vento calmo");
    }

    @Test
    public void shouldEncodeNoWindValue() {
        WindGroup windMetarGroup = new WindGroup(null, null, null);        
        assertEquals("/////KT", windMetarGroup.encode(), "Codificando vento calmo");
    }

    @Test
    public void shouldEncodeUnknownDirectionValue() {
        WindGroup windMetarGroup = new WindGroup(null, new BigInteger("10"), null);
        assertEquals("///10KT", windMetarGroup.encode(), "Codificando vento com direcao inesistente");
    }
    

    @Test
    public void shouldEncodeUnknownSpeedValue() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("100"), null, null);
        assertEquals("100//KT", windMetarGroup.encode(), "Codificando vento com velocidade inesistente");
    }

    @Test
    public void shouldEncodeWindValueOverTenKt() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("120"), new BigInteger("10"), null);
        assertEquals("12010KT", windMetarGroup.encode(), "Codificando vento com valores acima de 100 graus e maiores que 1 kt");
    }

    @Test
    public void shouldEncodeWindValueLessTenKt() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("20"), new BigInteger("10"), null);
        assertEquals("02010KT", windMetarGroup.encode(), "Codificando vento com valores abaixo de 100 graus e maiores que 1 kt");
    }

    @Test
    public void shouldEncodeWindSpeedValueOverHundredKnots() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("20"), new BigInteger("120"), null);
        assertEquals("020P99KT", windMetarGroup.encode(), "Codificando vento com valores abaixo de 100 graus e maiores que 100 kt");
    }

    @Test
    public void shouldEncodeWindSpeedValueLessTenKnots() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("20"), new BigInteger("02"), null);
        assertEquals("02002KT", windMetarGroup.encode(), "Codificando vento com valores abaixo de 100 graus e menores que 10 kt");
    }

    @Test
    public void shouldEncodeWindVRBDirectionWithSppedLowerTheeKnots() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("60"), new BigInteger("02"), null, new BigInteger("60"), new BigInteger("120"));
        assertEquals("VRB02KT", windMetarGroup.encode(), "Codificar VRB quando direcao do vento estiver variando entre 60 e 180 graus com velocidade inferior a 3kt");
    }
    
    @Test
    public void shouldEncodeWindVRBDirectionWithDifferenceOfDirectionUpOneHundredEighty() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("60"), new BigInteger("04"), null, new BigInteger("20"), new BigInteger("210"));
        assertEquals("VRB04KT", windMetarGroup.encode(), "Codificar VRB quando a variacao do vento ultrapssar 180 graus com qualquer valor de velocidade");
    }

    @Test
    public void shouldEncodeWindVRBDirectionWithNoSpeedValue() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("60"), null, null, new BigInteger("10"), new BigInteger("210"));
        assertEquals("VRB//KT", windMetarGroup.encode(), "Codificar VRB quando a variacao do vento ultrapssar 180 graus com qualquer valor de velocidade");
    }

    @Test
    public void shouldEncodeWindVRBDirectionWithOverHundredKnots() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("60"), new BigInteger("120"), null, new BigInteger("20"), new BigInteger("210"));
        assertEquals("VRBP99KT", windMetarGroup.encode(), "Codificar VRB quando a variacao do vento ultrapssar 180 graus com qualquer valor de velocidade");
    }
    
    
    @Test
    public void shouldIsNotGerateWindWithGustValue() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("60"), new BigInteger("20"), new BigInteger("30"));
        assertEquals("06020KT", windMetarGroup.encode(), "Nao Codifica rajada quando o valor da rajada não exceder em 10 em relacao a velocidade");

    }
    
    @Test
    public void shouldGerateWindWithGustValue() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("60"), new BigInteger("20"), new BigInteger("80"));
        assertEquals("06020G80KT", windMetarGroup.encode(), "Codifica rajada de vento");

    }

    @Test
    public void shouldGerateWindWithGustValueWithOverHundredKnots() {
        WindGroup windMetarGroup = new WindGroup(new BigInteger("60"), new BigInteger("20"), new BigInteger("130"));
        assertEquals("06020GP99KT", windMetarGroup.encode(), "Codifica rajada de vento com valores acima de 100 kt");

    }

    @Test
    public void shouldGerateWindWithVRBDirectionAndGustValue() {
        final BigInteger direction = new BigInteger("60");
        final BigInteger speed = new BigInteger("10");
        final BigInteger gust = new BigInteger("21");
        final BigInteger fromDirection = new BigInteger("20");
        final BigInteger toDirection = new BigInteger("210");
        WindGroup windMetarGroup = new WindGroup(direction,speed,gust,fromDirection,toDirection);
        assertEquals("VRB10G21KT", windMetarGroup.encode(), "Codifica rajada de vento com vento variando");
    }

    @Test
    public void shouldEncodeWindWithVaryingValue() {
        final BigInteger direction = new BigInteger("60");
        final BigInteger speed = new BigInteger("10");
        final BigInteger gust = null;
        final BigInteger fromDirection = new BigInteger("40");
        final BigInteger toDirection = new BigInteger("200");
        WindGroup windMetarGroup = new WindGroup(direction,speed,gust,fromDirection,toDirection);
        assertEquals("06010KT 040V200", windMetarGroup.encode(), "Codifica a variacao do vento quando diferença da direção for superior " + 
            "ou igual 60 e inferior 180 graus com a velocidade do vento superior ou igual 3 nos");

    }
    
    
}
