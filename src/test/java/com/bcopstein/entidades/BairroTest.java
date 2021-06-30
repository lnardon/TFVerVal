package com.bcopstein.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;
import com.bcopstein.entidades.geometria.Reta;
import com.bcopstein.entidades.geometria.SituacaoReta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BairroTest {
    private Area a1, a2;
    private Reta reta1, reta2;

    @BeforeEach
    public void setup() {
        reta1 = new Reta(new Ponto(10, 10), new Ponto(20, 10));
        reta2 = new Reta(new Ponto(10, 10), new Ponto(20, 20));
        a1 = mock(Area.class);
        when(a1.classifica(reta1)).thenReturn(SituacaoReta.TODA_DENTRO);

        a2 = mock(Area.class);
        when(a2.classifica(reta1)).thenReturn(SituacaoReta.TODA_FORA);
        when(a2.classifica(reta2)).thenReturn(SituacaoReta.INTERSECTA);
        when(a2.pontoCentral()).thenReturn(new Ponto(20, 20));
    }

    @Test
    public void testCentral() {
        Bairro block = new Bairro("Alto Petropolis", a2, 10);
        Ponto ptObj = new Ponto(20, 20);
        Ponto ptGot = block.getPontoCentral();
        assertEquals(ptObj, ptGot);
    }

    @Test
    public void testLineClass() {
        Bairro block = new Bairro("Morro Santana", a2, 10);
        SituacaoReta ptObj = SituacaoReta.INTERSECTA;
        SituacaoReta ptGot = block.getClassificacao(reta1);
        assertEquals(ptObj, ptGot);
    }

}
