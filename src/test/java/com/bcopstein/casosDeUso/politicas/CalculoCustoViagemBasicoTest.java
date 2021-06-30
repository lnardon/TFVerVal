package com.bcopstein.casosDeUso.politicas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Collection;
import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.geometria.Ponto;
import com.bcopstein.casosDeUso.Politicas.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculoCustoViagemBasicoTest {

    private ArrayList<Bairro> blocks;
    private Roteiro roteiro;
    private CalculoCustoViagem calc;

    @BeforeEach
    public void setup() {
        calc = new CalculoCustoViagemBasico();
        blocks = new ArrayList<>();
        blocks.add(Bairro.novoBairroRetangular("Glória", new Ponto(20, 20), 40, 20, 10.0));
        blocks.add(Bairro.novoBairroRetangular("Lomba do Pinheiro", new Ponto(20, 40), 40, 20, 10.0));
        blocks.add(Bairro.novoBairroRetangular("Belém Novo", new Ponto(40, 20), 40, 20, 10.0));
        blocks.add(Bairro.novoBairroRetangular("Rubem Berta", new Ponto(40, 30), 40, 20, 10.0));
        blocks.add(Bairro.novoBairroRetangular("Vila Ipiranga", new Ponto(40, 20), 40, 20, 10.0));
        blocks.add(Bairro.novoBairroRetangular("Jardim Europa", new Ponto(30, 40), 40, 20, 10.0));

        roteiro = new Roteiro(blocks.get(0), blocks.get(3), blocks);
        calc.defineRoteiro(roteiro);
    }

    @Test
    public void escolheRoteiroTest() {
        assertEquals(roteiro, calc.getRoteiro());
    }

    @Test
    public void custoViagemTest() {
        Roteiro roteiro = mock(Roteiro.class);
        Collection<Bairro> bairrosPercorridos = new ArrayList<Bairro>();
        bairrosPercorridos.add(blocks.get(5));
        bairrosPercorridos.add(blocks.get(4));
        bairrosPercorridos.add(blocks.get(2));

        when(roteiro.bairrosPercoridos()).thenReturn(bairrosPercorridos);
        calc.defineRoteiro(roteiro);

        assertEquals(30.0, calc.custoViagem());
    }

    @Test
    public void calculaCustoBasicoTest() {
        Roteiro roteiro = mock(Roteiro.class);
        Collection<Bairro> bairrosPercorridos = new ArrayList<Bairro>();
        bairrosPercorridos.add(blocks.get(5));
        bairrosPercorridos.add(blocks.get(4));
        bairrosPercorridos.add(blocks.get(0));

        when(roteiro.bairrosPercoridos()).thenReturn(bairrosPercorridos);

        calc.defineRoteiro(roteiro);

        assertEquals(roteiro, calc.getRoteiro());
        assertEquals(30.0, calc.calculoCustoBasico());
    }

    @Test
    public void descontoPontuacaoTest() {
        assertEquals(0.0, calc.descontoPontuacao());
    }

    @Test
    public void descontoPromocaoSazonalTest() {
        assertEquals(0.0, calc.descontoPromocaoSazonal());
    }
}