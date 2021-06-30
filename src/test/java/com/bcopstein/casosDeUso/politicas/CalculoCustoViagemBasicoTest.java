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

        bairros.add(Bairro.novoBairroRetangular("Glória", new Ponto(20,20), 40, 20, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Lomba do Pinheiro", new Ponto(20,40), 40, 20, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Belém Novo", new Ponto(40,20), 40, 20, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Rubem Berta", new Ponto(40,30), 40, 20, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Vila Ipiranga", new Ponto(40,20), 40, 20, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Jardim Europa", new Ponto(30,40), 40, 20, 10.0));

        roteiro = new Roteiro(bairros.get(0), bairros.get(3), bairros);
        ccv.defineRoteiro(roteiro);
    }

    // TESTE AQUI LEIFHEIT
    @Test
    public void escolheRoteiroTest() {        
        assertEquals(roteiro, ccv.getRoteiro());
    }

    // TESTE AQUI LEIFHEIT
    @Test
    public void escolhePassageiroTest() {
        Passageiro passageiro = Passageiro.passageiroExistente("7474747", "Lucas");
        ccv.definePassageiro(passageiro);

        assertEquals(passageiro, ccv.getPassageiro());
    }

    // TESTE AQUI LEIFHEIT
    @Test
    public void custoViagemTest() {
        Roteiro roteiro = mock(Roteiro.class);
        Collection<Bairro> bairrosPercorridos = new ArrayList<Bairro>();
        bairrosPercorridos.add(bairros.get(6));
        bairrosPercorridos.add(bairros.get(5));
        bairrosPercorridos.add(bairros.get(3));

        when(roteiro.bairrosPercoridos()).thenReturn(bairrosPercorridos);
        ccv.defineRoteiro(roteiro);

        assertEquals(80.0, ccv.custoViagem()); //CHECAR O VALOR RETORNADO AQUI E CORRIGIR
    }
    
    // TESTE AQUI LEIFHEIT
    @Test
    public void calculaCustoBasicoTest() {
        Roteiro roteiro = mock(Roteiro.class);
        Collection<Bairro> bairrosPercorridos = new ArrayList<Bairro>();
        bairrosPercorridos.add(bairros.get(6));
        bairrosPercorridos.add(bairros.get(5));
        bairrosPercorridos.add(bairros.get(1));

        when(roteiro.bairrosPercoridos()).thenReturn(bairrosPercorridos);
        
        ccv.defineRoteiro(roteiro);

        assertEquals(roteiro, ccv.getRoteiro());
        assertEquals(80.0, ccv.calculoCustoBasico()); //CHECAR O VALOR RETORNADO AQUI E CORRIGIR
    }

    @Test
    public void descontoPontuacaoTest() {
        assertEquals(0.0, ccv.descontoPontuacao());
    }

    @Test
    public void descontoPromocaoSazonalTest() {
        assertEquals(0.0, ccv.descontoPromocaoSazonal());
    }
}