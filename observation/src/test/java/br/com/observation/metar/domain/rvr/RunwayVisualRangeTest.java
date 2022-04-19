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
    public void shouldEncodeRVRValueLessEightHundredMeters() throws Exception {
        RunwayVisualRangeGroup rvrValid = new RunwayVisualRangeGroup("19l", 750l);
        assertEquals("R19L/0750", rvrValid.encode(), "Codificando valor de rvr menor que 400 metros");
        final Exception rvrInvalid = assertThrows(Exception.class, () -> {
            new RunwayVisualRangeGroup("09", 710l);
        });
        assertEquals("Valor de RVR inválido", rvrInvalid.getMessage(), "Validando incrementos até 800 metros");
    }

    @Test
    public void shouldEncodeRVRValueUpperEightHundredMeters() throws Exception {
        RunwayVisualRangeGroup rvrValid = new RunwayVisualRangeGroup("19l", 1000l);
        assertEquals("R19L/1000", rvrValid.encode(), "Codificando valor de rvr menor que 400 metros");
        final Exception rvrInvalid = assertThrows(Exception.class, () -> {
            new RunwayVisualRangeGroup("09", 1050l);
        });
        assertEquals("Valor de RVR inválido", rvrInvalid.getMessage(), "Validando incrementos acima de 1000 metros");
    }

    @Test
    public void shouldEncodeRVRValueLessFourHundredMeters() throws Exception {
        RunwayVisualRangeGroup rvrValid = new RunwayVisualRangeGroup("19l", 300l);
        assertEquals("R19L/0300", rvrValid.encode(), "Codificando valor de rvr menor que 400 metros");
        final Exception rvrInvalid = assertThrows(Exception.class, () -> {
            new RunwayVisualRangeGroup("09", 310l);
        });
        assertEquals("Valor de RVR inválido", rvrInvalid.getMessage(), "Validando incrementos até 400 metros");
    }

    @Test
    public void shouldEncodeRVRWithValuesEmpty() throws Exception {
        RunwayVisualRangeGroup rvrEmpty = new RunwayVisualRangeGroup("19", null);
        assertEquals("R19/////", rvrEmpty.encode(), "Codificando valor de rvr menor que 1000 metros");
        RunwayVisualRangeGroup rvrEmotyWithTendecy = new RunwayVisualRangeGroup("19", null, "D");
        assertEquals("R19/////", rvrEmotyWithTendecy.encode(), "Codificando valor de rvr menor que 1000 metros");
    }

    @Test
    public void shouldEncodeRVRValueLessOneFifityMeters() throws Exception {
        RunwayVisualRangeGroup rvr = new RunwayVisualRangeGroup("19l", 50l);
        assertEquals("R19L/M0050", rvr.encode(), "Codificando rvr com valor menor ou igual a 50 metros");

        RunwayVisualRangeGroup rvrLessFifty = new RunwayVisualRangeGroup("19l", 25l);
        assertEquals("R19L/M0050", rvrLessFifty.encode(), "Codificando rvr com valor menor a 50 metros");
    }

    @Test
    public void shouldEncodeRVRValueUpperTwoThounsandMeters() throws Exception {
        RunwayVisualRangeGroup rvr = new RunwayVisualRangeGroup("19l", 2000l);
        assertEquals("R19L/P2000", rvr.encode(), "Codificando rvr com valor maior ou igual a 2000 metros");

        RunwayVisualRangeGroup rvrLessFifty = new RunwayVisualRangeGroup("19l", 3000l);
        assertEquals("R19L/P2000", rvrLessFifty.encode(), "Codificando rvr com valor maior que 2000 metros");
    }

    @Test
    public void shouldEncodeRVRValueWithTendency() throws Exception {
        final RunwayVisualRangeGroup rvrWithTedencyUpper = new RunwayVisualRangeGroup("19l", 1300l, "U");
        assertEquals("R19L/1300U", rvrWithTedencyUpper.encode(), "Codificando RVR com valores de tentencia subindo");
        final RunwayVisualRangeGroup rvrWithTedencyDown = new RunwayVisualRangeGroup("19l", 1300l, "D");
        assertEquals("R19L/1300D", rvrWithTedencyDown.encode(), "Codificando RVR com valores de tentencia descendo");
        final RunwayVisualRangeGroup rvrWithTedencyNeutral = new RunwayVisualRangeGroup("19l", 1300l, "N");
        assertEquals("R19L/1300N", rvrWithTedencyNeutral.encode(), "Codificando RVR com valores de tentencia neutro");
    }

    @Test
    public void shouldEncodeRVRValueWithTendencyAndLowerCase() throws Exception {
        final RunwayVisualRangeGroup rvrWithTedencyUpper = new RunwayVisualRangeGroup("19l", 1300l, "u");
        assertEquals("R19L/1300U", rvrWithTedencyUpper.encode(), "Codificando RVR com valores de tentencia subindo em minusculo");
        final RunwayVisualRangeGroup rvrWithTedencyDown = new RunwayVisualRangeGroup("19l", 1300l, "d");
        assertEquals("R19L/1300D", rvrWithTedencyDown.encode(), "Codificando RVR com valores minusculo de tentencia descendo em minusculo");
        final RunwayVisualRangeGroup rvrWithTedencyNeutral = new RunwayVisualRangeGroup("19l", 1300l, "n");
        assertEquals("R19L/1300N", rvrWithTedencyNeutral.encode(), "Codificando RVR com valores de tentencia neutro em minusculo");
    }

    @Test
    public void shouldEncodeRVRValueWithTendencyInvalid() {
        final Exception rvrWithTedencyInvalid = assertThrows(Exception.class, () -> {
            new RunwayVisualRangeGroup("09", 1300l, "T");
        });
        assertEquals("Tendencia Inválida", rvrWithTedencyInvalid.getMessage(), "Validando tendencia inválida do rvr");      
    }    
}
