package com.verival.casosDeUso.politicas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;


import com.verival.entidades.Bairro;
import com.verival.entidades.Passageiro;
import com.verival.entidades.Roteiro;
import com.verival.entidades.geometria.Ponto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculoCustoViagemBasicoTest {

    private ArrayList<Bairro> bairros;
    private Roteiro roteiro;
    private CalculoCustoViagem ccv;

    @BeforeEach
    public void setup() {
        ccv = new CalculoCustoViagemBasico();

        bairros = new ArrayList<>();

        bairros.add(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10,40), 20, 10, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Independencia", new Ponto(30,40), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20,30), 20, 10, 30.0));
        bairros.add(Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40,30), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Boa Vista", new Ponto(40,20), 20, 10, 20.0));

        roteiro = new Roteiro(bairros.get(0), bairros.get(3), bairros);

        ccv.defineRoteiro(roteiro);
    }

    @Test
    public void defineRoteiroTest() {
        
        assertEquals(roteiro, ccv.getRoteiro());
    }

    @Test
    public void definePassageiroTest() {
        Passageiro passageiro = Passageiro.novoPassageiro("12345678910", "Adalberto");

        ccv.definePassageiro(passageiro);
        assertEquals(passageiro, ccv.getPassageiro());
    }
    
    @Test
    public void calculaCustoBasicoTest() {

        Roteiro roteiro = mock(Roteiro.class);
        Collection<Bairro> bairrosPercorridos = new ArrayList<Bairro>();
        bairrosPercorridos.add(bairros.get(0));
        bairrosPercorridos.add(bairros.get(1));
        bairrosPercorridos.add(bairros.get(2));
        bairrosPercorridos.add(bairros.get(3));

        when(roteiro.bairrosPercoridos()).thenReturn(bairrosPercorridos);
        
        ccv.defineRoteiro(roteiro);

        assertEquals(roteiro, ccv.getRoteiro());
        assertEquals(80.0, ccv.calculoCustoBasico());
    }

    @Test
    public void descontoPontuacaoTest() {
        assertEquals(0.0, ccv.descontoPontuacao());
    }

    @Test
    public void descontoPromocaoSazonal() {
        assertEquals(0.0, ccv.descontoPromocaoSazonal());
    }

    @Test
    public void custoViagemTest() {
        Roteiro roteiro = mock(Roteiro.class);
        Collection<Bairro> bairrosPercorridos = new ArrayList<Bairro>();
        bairrosPercorridos.add(bairros.get(0));
        bairrosPercorridos.add(bairros.get(1));
        bairrosPercorridos.add(bairros.get(2));
        bairrosPercorridos.add(bairros.get(3));

        when(roteiro.bairrosPercoridos()).thenReturn(bairrosPercorridos);
        ccv.defineRoteiro(roteiro);

        assertEquals(80.0, ccv.custoViagem());
    }
}