package br.com.observation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

public class DirectionVaryingValueTest {

    @Test
    public void shouldIsVRBWhenDifferenceUpperOneHundredEighty() {
        DirectionVaryingValue directionVaryingValue = new DirectionVaryingValue(new BigInteger("20"), new BigInteger("210"), new BigInteger("4"));
        assertTrue(directionVaryingValue.isVRB(), "Retora true quando a diferenca entre as direcoes for maior que 180");
    }

    @Test
    public void shouldIsVRBWheUpperSixtyAndLowerOneHundredEightyAndSpeedLessThreeKnots(){
        DirectionVaryingValue directionVaryingValue = new DirectionVaryingValue(new BigInteger("100"), new BigInteger("210"), new BigInteger("2"));
        assertTrue(directionVaryingValue.isVRB(), "Retora true quando a diferenca for maior igual a 60 e menor que 180 graus e velodiade menor que 3kt");
    }

    @Test
    public void shouldIsVRBWhenDiferenceOfDirectionIsGreaderThan60AndLower180DegressAndSpeedAbove3Knots() {
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
