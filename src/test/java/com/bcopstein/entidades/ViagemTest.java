package com.verival.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import com.verival.entidades.geometria.Ponto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ViagemTest {

    private Viagem viagem;
    private ArrayList<Bairro> bairros;
    private Roteiro roteiro;
    private Passageiro passageiro;
    private LocalDateTime time;
    

    @BeforeEach
    public void setup() {
        bairros = new ArrayList<Bairro>();

        bairros.add(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10,40), 20, 10, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Independencia", new Ponto(30,40), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20,30), 20, 10, 30.0));
        bairros.add(Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40,30), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Boa Vista", new Ponto(40,20), 20, 10, 20.0));

        roteiro = new Roteiro(bairros.get(0), bairros.get(3), bairros);

        passageiro = Passageiro.novoPassageiro("12345678910", "Adalberto");

        time = LocalDateTime.now();

        viagem = new Viagem(1, time, roteiro, passageiro, 12.5);
    }

    @Test
    public void viagemTest() {
        assertEquals(1, viagem.getId());
        assertEquals(time, viagem.getDataHora());
        assertEquals(roteiro, viagem.getRoteiro());
        assertEquals(passageiro, viagem.getPassageiro());
        assertEquals(12.5, viagem.getValorCobrado());
    }

    @Test
    public void toStringTest() {

        String passageiro = "Passageiro [cpf=" + "12345678910" + ", nome=" + "Adalberto" + ", pontuacaoAcumulada=" + 8
        + ", qtdadeAvaliacoes=" + 1 + "]";
        String bairroDest = "Bairro [area=" + "Area [pInfDir=" + "Ponto [x=" + 60 + ", y=" + 20 + "]" + ", pSupEsq=" + "Ponto [x=" + 40 + ", y=" + 30 + "]" + "]" + ", nome=" + "Auxiliadora" + "]";

        String bairroOrig = "Bairro [area=" + "Area [pInfDir=" + "Ponto [x=" + 30 + ", y=" + 30 + "]" + ", pSupEsq=" + "Ponto [x=" + 10 + ", y=" + 40 + "]" + "]" + ", nome=" + "Bom Fim" + "]";

        String roteiro = "Roteiro [bairroDestino=" + bairroDest + ", bairroOrigem=" + bairroOrig + "]";

        String expected = "Viagem [valor cobrado=" + 12.5 + ", dataHora=" + time + ", id=" + 1 + 
        ", passageiro=" + passageiro + ", roteiro=" + roteiro + "]";

        assertEquals(expected, viagem.toString());
    }
}