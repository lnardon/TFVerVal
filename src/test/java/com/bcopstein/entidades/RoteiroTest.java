package com.bcopstein.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.bcopstein.entidades.geometria.Ponto;
import com.bcopstein.entidades.geometria.Reta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoteiroTest {
    private List<Bairro> blocks;

    @BeforeEach
    public void setup() {
        blocks = new ArrayList<>();
        blocks.add(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10, 40), 20, 10, 10.0));
        blocks.add(Bairro.novoBairroRetangular("Independencia", new Ponto(30, 40), 20, 10, 20.0));
        blocks.add(Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20, 30), 20, 10, 30.0));
        blocks.add(Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40, 30), 20, 10, 20.0));
        blocks.add(Bairro.novoBairroRetangular("Boa Vista", new Ponto(40, 20), 20, 10, 20.0));
    }

    @Test
    public void testRoute() {
        Roteiro roteiro = new Roteiro(blocks.get(1), blocks.get(4), blocks);
        Reta rotaEsp = new Reta(new Ponto(40, 35), new Ponto(50, 15));
        assertEquals(rotaEsp, roteiro.getRota());
    }

    @Test
    public void testBlocksVisited() {
        Roteiro roteiro = new Roteiro(blocks.get(1), blocks.get(4), blocks);
        Collection<Bairro> w = new ArrayList<>();
        w.add(blocks.get(1));
        w.add(blocks.get(2));
        w.add(blocks.get(3));
        w.add(blocks.get(4));
        Collection<Bairro> g = roteiro.bairrosPercoridos();
        assertEquals(w, g);
    }
}