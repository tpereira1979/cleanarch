package br.com.observation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

public class SpeedValueTest {

    @Test
    public void shouldGenerateSpeedValue() {
        SpeedValue speedValue = new SpeedValue(new BigInteger("4"), null);
        assertEquals("04", speedValue.getValue());
    }

    @Test
    public void shouldGenerateEmptySpeedValue() {
        SpeedValue speedValue = new SpeedValue(null, null);
        assertEquals("//", speedValue.getValue());
    }

    public void shouldGenerateGustValue() {
        SpeedValue gustValue = new SpeedValue(new BigInteger("10"), new BigInteger("21"));
        assertEquals("10G21", gustValue.getValue(), "Gera a rajada do vento quando o valor da rajada exceder em 10 nós a velocidade do vento");
    }

    @Test
    public void shouldIsNotGenerateGustValue() {
        SpeedValue gustValue = new SpeedValue(new BigInteger("10"), new BigInteger("18"));
        assertEquals("10", gustValue.getValue(), "Não gera a rajada de vento quando o valor da rajada não exceder em 10 nós a velocidade do vento");
    }

    @Test
    public void shouldIsNotGenerateGustWhenDifferenceBetweenGustAndSpeedValueIsTenKt() {
        SpeedValue gustValue = new SpeedValue(new BigInteger("10"), new BigInteger("20"));
        assertEquals("10", gustValue.getValue(), "Não gera a rajada de vento quando a diferença entre a velcidade e rajada é de 10 kt");
    }

    @Test
    public void shouldIsNotGenerateGustWhenThereIsNotGustValue() {
        SpeedValue gustValue = new SpeedValue(new BigInteger("10"), null);
        assertEquals("10", gustValue.getValue(), "Não gera rajada quando não exisitir velocidade do vento");
    }

    @Test
    public void shouldIsNotGenerateGustWhenThereIsNotSpeedValue() {
        SpeedValue gustValue = new SpeedValue(null, new BigInteger("10"));
        assertEquals("//", gustValue.getValue(), "Não gera rajada quando nao exisitir velocidade do vento");
    }    
    
}
