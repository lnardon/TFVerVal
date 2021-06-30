package com.bcopstein.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;
import com.bcopstein.entidades.geometria.Reta;
import com.bcopstein.entidades.geometria.SituacaoReta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AreaTest {
    private Area area;

    @BeforeEach
    public void setup() {
        area = new Area(new Ponto(10, 50), new Ponto(60, 10));
    }

    @Test
    public void testCentral() {
        Ponto p = area.pontoCentral();
        assertEquals(35, p.getX());
        assertEquals(30, p.getY());
    }

    @ParameterizedTest
    @CsvSource({ "15,40,35,40,TODA_DENTRO", "15, 5,35, 5,TODA_FORA" })
    public void testClass(int x1, int y1, int x2, int y2, String classificacao) {
        Reta reta = new Reta(new Ponto(x1, y1), new Ponto(x2, y2));
        SituacaoReta sitObj = switch (classificacao) {
            case "TODA_DENTRO" -> SituacaoReta.TODA_DENTRO;
            case "TODA_FORA" -> SituacaoReta.TODA_FORA;
            case "INTERSECTA" -> SituacaoReta.INTERSECTA;
            default -> SituacaoReta.TODA_DENTRO;
        };
        SituacaoReta sitGot = area.classifica(reta);
        assertEquals(sitObj, sitGot);
    }
}