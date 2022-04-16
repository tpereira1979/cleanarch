package br.com.observation.metar.domain.rvr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RunwayVisualRangeTest {

    @Test
    public void shouldEncodeRVR() throws Exception {
        RunwayVisualRangeGroup rvr = new RunwayVisualRangeGroup("19L", 1300l);
        assertEquals("R19L/1300", rvr.encode(), "Codificando valores de RVR");        
    }

    @Test
    public void shouldEncodeRVRWithRunwayWithOneCharacter() throws Exception {
        RunwayVisualRangeGroup rvr = new RunwayVisualRangeGroup("9", 1300l);
        assertEquals("R09/1300", rvr.encode(), "Codificando rvr com o numero da cabeceira menor que 10");
        RunwayVisualRangeGroup rvrWithPosition = new RunwayVisualRangeGroup("9L", 1300l);
        assertEquals("R09L/1300", rvrWithPosition.encode(), "Codificando rvr com o numero da cabeceira menor que 10 e informando a posicao");
    }

    @Test
    public void shouldEncodeRVRWithRunwayWithTwoCharacter() throws Exception {
        RunwayVisualRangeGroup rvr = new RunwayVisualRangeGroup("19", 1300l);
        assertEquals("R19/1300", rvr.encode(), "Codificando rvr com o numero da cabeceira maior que 10");
        RunwayVisualRangeGroup rvrWithPosition = new RunwayVisualRangeGroup("19L", 1300l);
        assertEquals("R19L/1300", rvrWithPosition.encode(), "Codificando rvr com o numero da cabeceira maior que 10 e informando a posicao");
    }

    @Test
    public void shouldValidateRumwayNumberInvalid() {
        final Exception rvrWithPositionInvalid = assertThrows(Exception.class, () -> {
            new RunwayVisualRangeGroup("09A", 1300l);
        });
        assertEquals("Numero da pista inválido", rvrWithPositionInvalid.getMessage(), "Numero da cabeceira da pista invalida com dois characters");

        final Exception rvrWithPositionInvalidWithOneCharacter = assertThrows(Exception.class, () -> {
            new RunwayVisualRangeGroup("9A", 1300l);
        });
        assertEquals("Numero da pista inválido", rvrWithPositionInvalidWithOneCharacter.getMessage(), "Numero da cabeceira da pista invalida com um characteres");

        final Exception rvrRunwayNumberInvalid = assertThrows(Exception.class, () -> {
            new RunwayVisualRangeGroup("AL", 1300l);
        });
        assertEquals("Numero da pista inválido", rvrRunwayNumberInvalid.getMessage(), "Numero da cabeceira da pista invalida com um characteres");

        final Exception rvrRunwayNumberSizeInvalid = assertThrows(Exception.class, () -> {
            new RunwayVisualRangeGroup("0400L", 1300l);
        });
        assertEquals("Numero da pista inválido", rvrRunwayNumberSizeInvalid.getMessage(), "Tamanho do numero da cabeceira da pista invalida");

        final Exception rvrRunwayNumberSizeInvalidOnlyNumber = assertThrows(Exception.class, () -> {
            new RunwayVisualRangeGroup("0400", 1300l);
        });
        assertEquals("Numero da pista inválido", rvrRunwayNumberSizeInvalidOnlyNumber.getMessage(), "Tamanho do numero da cabeceira da pista invalida com somente numeros");        
    }

    @Test
    public void shouldEncodeRVRValueLessOneThounsandMeters() throws Exception {
        RunwayVisualRangeGroup rvr = new RunwayVisualRangeGroup("19l", 300l);
        assertEquals("R19L/0300", rvr.encode(), "Codificando valor de rvr menor que 1000 metros");
    }

    @Test
    public void shouldEncodeRVRValueLessOneFifityMeters() throws Exception {
        RunwayVisualRangeGroup rvr = new RunwayVisualRangeGroup("19l", 50l);
        assertEquals("R19L/M0050", rvr.encode(), "Codificando rvr com valor menor ou igual a 50 metros");

        RunwayVisualRangeGroup rvrLessFifty = new RunwayVisualRangeGroup("19l", 30l);
        assertEquals("R19L/M0050", rvrLessFifty.encode(), "Codificando rvr com valor menor a 50 metros");
    }

    @Test
    public void shouldEncodeRVRValueUpperTwoThounsandMeters() throws Exception {
        RunwayVisualRangeGroup rvr = new RunwayVisualRangeGroup("19l", 2000l);
        assertEquals("R19L/P2000", rvr.encode(), "Codificando rvr com valor maior ou igual a 2000 metros");

        RunwayVisualRangeGroup rvrLessFifty = new RunwayVisualRangeGroup("19l", 3000l);
        assertEquals("R19L/P2000", rvrLessFifty.encode(), "Codificando rvr com valor maior que 2000 metros");
    }


    
}
