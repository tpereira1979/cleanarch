package br.com.observation.metar.domain.entity.wind;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

public class DirectionVaryingValueTest {

    @Test
    public void shouldIsDirectionVaritionValueWithSpeedUpperOrEqualThreeKnots() {
        DirectionVaryingValue directionVaryingValue = new DirectionVaryingValue(new BigInteger("100"), new BigInteger("210"), new BigInteger("3"));
        assertEquals("100V210", directionVaryingValue.getValue(), "Codifica a variacao do vento quando a direcao do vento for 60 ou mais, porem inferior 180 graus " + 
                                                                       "com velodidade de 3 nos ");
    }

    @Test
    public void shouldIsDirectionVaritionValueWithSpeedUpperThreeKnots() {
        DirectionVaryingValue directionVaryingValue = new DirectionVaryingValue(new BigInteger("100"), new BigInteger("210"), new BigInteger("4"));
        assertEquals("100V210", directionVaryingValue.getValue(), "Codifica a variacao do vento quando a direcao do vento for 60 ou mais, " + 
                                                                        "porem inferior 180 graus com velocidade acima de 3 nos");
    }

    @Test
    public void shouldIsDirectionVaritionValueLowerSixty() {
        DirectionVaryingValue directionVaryingValue = new DirectionVaryingValue(new BigInteger("10"), new BigInteger("90"), new BigInteger("4"));
        assertEquals("010V090", directionVaryingValue.getValue(), "Codifica a variação do vento com valores abaixo de 100 graus");
    }

    @Test
    public void shouldDifferenceLessSixtyDegree() {
        DirectionVaryingValue directionVaryingValue = new DirectionVaryingValue(new BigInteger("10"), new BigInteger("30"), new BigInteger("4"));
        assertEquals("", directionVaryingValue.getValue(), "Nao codifica a variação do vento com a diferenca menor 60 graus com velocidade acima de 3kt");
    }

    @Test
    public void shouldDifferenceLessSixtyDegreeLessThreeKnots() {
        DirectionVaryingValue directionVaryingValue = new DirectionVaryingValue(new BigInteger("10"), new BigInteger("30"), new BigInteger("2"));
        assertEquals("", directionVaryingValue.getValue(), "Nao codifica a variação do vento com a diferenca menor 60 graus com velocidade abaixo de 3kt");
    }


    @Test
    public void shouldDifferenceUpperOneHundredEightyDegree() {
        DirectionVaryingValue directionVaryingValue = new DirectionVaryingValue(new BigInteger("10"), new BigInteger("200"), new BigInteger("4"));
        assertEquals("", directionVaryingValue.getValue(), "Nao codifica a variação do vento com a dieferenca maior 180 graus com velocidade acima de 3kt");
    }

    @Test
    public void shouldDifferenceUpperOneHundredEightyDegreeLessThreeKnots() {
        DirectionVaryingValue directionVaryingValue = new DirectionVaryingValue(new BigInteger("10"), new BigInteger("200"), new BigInteger("2"));
        assertEquals("", directionVaryingValue.getValue(), "Nao codifica a variação do vento com a dieferenca maior 180 graus com velocidade abaixo de 3kt");
    }

    
    @Test
    public void shouldIsVRBWhenDifferenceUpperOneHundredEighty() {
        DirectionVaryingValue directionVaryingValue = new DirectionVaryingValue(new BigInteger("20"), new BigInteger("210"), new BigInteger("4"));
        assertTrue(directionVaryingValue.isVRB(), "Retorna true quando a diferenca entre as direcoes for maior que 180");
    }

    @Test
    public void shouldIsVRBSpeedLessThreeKnots(){
        DirectionVaryingValue directionVaryingValue = new DirectionVaryingValue(new BigInteger("100"), new BigInteger("210"), new BigInteger("2"));
        assertTrue(directionVaryingValue.isVRB(), "Retora true quando a diferenca for maior igual a 60 e menor que 180 graus com velodiade menor que 3kt");
    }

    @Test
    public void shouldIsVRBSpeedAbove3Knots() {
        DirectionVaryingValue directionVaryingInitialValue = new DirectionVaryingValue(new BigInteger("100"), new BigInteger("210"), new BigInteger("4"));
        assertFalse(directionVaryingInitialValue.isVRB(), "Não gera VRB quando direção estiver entre 60 e 180 e velocidade superior ou igual a 3kt");        
        DirectionVaryingValue directionVaryingWithTreeKnotsOfSpeed = new DirectionVaryingValue(new BigInteger("100"), new BigInteger("210"), new BigInteger("3"));
        assertFalse(directionVaryingWithTreeKnotsOfSpeed.isVRB(), "Não gera VRB quando não existir velocidade do vento com 3kt e a direção estiver entre 60 e 180");        
        
    }

    @Test
    public void shouldIsNotVRBWhenNotExistsWindDirectionVaryingValue(){
        DirectionVaryingValue directionVaryingValue = new DirectionVaryingValue(null, null, null);
        assertFalse(directionVaryingValue.isVRB(), "Não gera VRB quando não existir direcao do vento");
    }

    @Test
    public void shouldIsNotVRBWhenNotExistsWindDirectionValue() {
        DirectionVaryingValue directionVaryingInitialValue = new DirectionVaryingValue(null, new BigInteger("210"), new BigInteger("2"));
        assertFalse(directionVaryingInitialValue.isVRB(), "Não gera VRB quando não existir direcao inicial do vento");        
        DirectionVaryingValue directionVaryingFinalValue = new DirectionVaryingValue(new BigInteger("100"), null, new BigInteger("2"));
        assertFalse(directionVaryingFinalValue.isVRB(), "Não gera VRB quando não exisitir uma direcao final do vento");
    }

    @Test
    public void shouldIsNotVRBWhenNotExistsSpeedValue() {
        DirectionVaryingValue directionVaryingInitialValue = new DirectionVaryingValue(new BigInteger("100"), new BigInteger("210"), null);
        assertFalse(directionVaryingInitialValue.isVRB(), "Não gera VRB quando não existir velocidade do vento e a direção estiver entre 60 e 180");        
        
    }
    
}
