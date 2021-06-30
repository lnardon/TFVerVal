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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculoCustoViagemRelampagoTest {

    private ArrayList<Bairro> bairros;
    private Collection<Bairro> bairrosPercorridos;
    private CalculoCustoViagem ccv;
    private Roteiro roteiro;

    @BeforeEach
    public void setup() {
        ccv = new CalculoCustoViagemRelampago();
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
    @CsvSource({"200,32,4.0", "120,20,0.0", "10,31,0.0", "100,15,0.0",
                "150,30,0.0"}) // MUDAR?
    public void descontoPontuacaoRelampagoTest(int pontuacaoAcumulada, int qtdadeAvaliacoes, double expected) {
        bairrosPercorridos.add(bairros.get(6));
        bairrosPercorridos.add(bairros.get(5));
        bairrosPercorridos.add(bairros.get(3));
        bairrosPercorridos.add(bairros.get(2));
        bairrosPercorridos.add(bairros.get(1));

        when(roteiro.bairrosPercoridos()).thenReturn(bairrosPercorridos);
        ccv.defineRoteiro(roteiro);
        
        Passageiro passageiro = Passageiro.passageiroExistente("7474747", "Lucas", pontuacaoAcumulada, qtdadeAvaliacoes);
        ccv.definePassageiro(passageiro);

        assertEquals(expected, ccv.descontoPontuacao()); //CHECAR O VALOR RETORNADO AQUI E CORRIGIR
    }

    // TESTE AQUI LEIFHEIT
    @ParameterizedTest
    @CsvSource({"0,3,4.0","1,1,0.0","1,4,0.0"}) // MUDAR?
    public void descontoPromocaoSazonalTestCaso1() {
        bairrosPercorridos.add(bairros.get(6));
        bairrosPercorridos.add(bairros.get(5));
        bairrosPercorridos.add(bairros.get(3));
        bairrosPercorridos.add(bairros.get(2));

        when(roteiro.bairrosPercoridos()).thenReturn(bairrosPercorridos);
        ccv.defineRoteiro(roteiro);

        assertEquals(4.0, ccv.descontoPromocaoSazonal()); //CHECAR O VALOR RETORNADO AQUI E CORRIGIR
    }

    // TESTE AQUI LEIFHEIT
    public void descontoPromocaoSazonalTestCaso2() {
        bairrosPercorridos.add(bairros.get(6));

        when(roteiro.bairrosPercoridos()).thenReturn(bairrosPercorridos);
        ccv.defineRoteiro(roteiro);

        assertEquals(0.0, ccv.descontoPromocaoSazonal()); //CHECAR O VALOR RETORNADO AQUI E CORRIGIR
    }

    // TESTE AQUI LEIFHEIT
    public void descontoPromocaoSazonalTestCaso3() {
        bairrosPercorridos.add(bairros.get(6));
        bairrosPercorridos.add(bairros.get(5));
        bairrosPercorridos.add(bairros.get(3));
        bairrosPercorridos.add(bairros.get(2));

        assertEquals(0.0, ccv.descontoPromocaoSazonal()); //CHECAR O VALOR RETORNADO AQUI E CORRIGIR
    }
}