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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculoCustoViagemVeraoTest {
    
    private CalculoCustoViagem ccv;
    private Roteiro roteiro;
    private ArrayList<Bairro> bairros;
    private Collection<Bairro> bairrosPercorridos;

    @BeforeEach
    public void setup() {
        ccv = new CalculoCustoViagemVerao();
        bairros = new ArrayList<>();

        bairros.add(Bairro.novoBairroRetangular("Glória", new Ponto(20,20), 40, 20, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Lomba do Pinheiro", new Ponto(20,40), 40, 20, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Belém Novo", new Ponto(40,20), 40, 20, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Rubem Berta", new Ponto(40,30), 40, 20, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Vila Ipiranga", new Ponto(40,20), 40, 20, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Jardim Europa", new Ponto(30,40), 40, 20, 10.0));

        roteiro = mock(Roteiro.class);
        bairrosPercorridos = new ArrayList<Bairro>();
    }

    // TESTE AQUI LEIFHEIT
    @ParameterizedTest
    @CsvSource({"30,3,72.0", "5,3,0.0","9,1,0.0"}) // MUDAR?
    public void descontoPontuacaoTest(int pontuacaoAcumulada, int qtdadeAvaliacoes, double expected) {
        bairrosPercorridos.add(bairros.get(1));
        bairrosPercorridos.add(bairros.get(2));
        bairrosPercorridos.add(bairros.get(3));
        bairrosPercorridos.add(bairros.get(4));

        when(roteiro.bairrosPercoridos()).thenReturn(bairrosPercorridos);
        ccv.defineRoteiro(roteiro);
        
        Passageiro passageiro = Passageiro.passageiroExistente("7474747", "Lucas", pontuacaoAcumulada, qtdadeAvaliacoes);
        ccv.definePassageiro(passageiro);

        assertEquals(expected, ccv.descontoPontuacao());
    }

    // TESTE AQUI LEIFHEIT
    @Test
    public void descontoPromocaoSazonalTestCase1() {
        bairrosPercorridos.add(bairros.get(1));
        bairrosPercorridos.add(bairros.get(2));
        bairrosPercorridos.add(bairros.get(4));
        bairrosPercorridos.add(bairros.get(5));

        when(roteiro.bairrosPercoridos()).thenReturn(bairrosPercorridos);
        ccv.defineRoteiro(roteiro);
        
        //CHECAR O VALOR RETORNADO AQUI E CORRIGIR
        assertEquals(8.0, ccv.descontoPromocaoSazonal());
    }

    // TESTE AQUI LEIFHEIT
    @Test
    public void descontoPromocaoSazonalTestCase2() {
        bairrosPercorridos.add(bairros.get(4));

        when(roteiro.bairrosPercoridos()).thenReturn(bairrosPercorridos);
        ccv.defineRoteiro(roteiro);

        //CHECAR O VALOR RETORNADO AQUI E CORRIGIR
        assertEquals(0.0, ccv.descontoPromocaoSazonal());
    }

    // TESTE AQUI LEIFHEIT
    @Test
    public void descontoPromocaoSazonalTestCase3() {
        bairrosPercorridos.add(bairros.get(5));
        bairrosPercorridos.add(bairros.get(6));

        when(roteiro.bairrosPercoridos()).thenReturn(bairrosPercorridos);
        ccv.defineRoteiro(roteiro);

        //CHECAR O VALOR RETORNADO AQUI E CORRIGIR
        assertEquals(0.0, ccv.descontoPromocaoSazonal());
    }
}