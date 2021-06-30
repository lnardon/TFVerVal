package com.bcopstein.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.util.ArrayList;
import com.bcopstein.entidades.geometria.Ponto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ViagemTest {

    private Viagem viagem;
    private ArrayList<Bairro> blocks;
    private Roteiro roteiro;
    private Passageiro passageiro;
    private LocalDateTime tempo;

    @BeforeEach
    public void setup() {
        blocks = new ArrayList<Bairro>();
        blocks.add(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10, 40), 20, 10, 10.0));
        blocks.add(Bairro.novoBairroRetangular("Independencia", new Ponto(30, 40), 20, 10, 20.0));
        blocks.add(Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20, 30), 20, 10, 30.0));
        blocks.add(Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40, 30), 20, 10, 20.0));
        blocks.add(Bairro.novoBairroRetangular("Boa Vista", new Ponto(40, 20), 20, 10, 20.0));
        roteiro = new Roteiro(blocks.get(0), blocks.get(3), blocks);
        passageiro = Passageiro.novoPassageiro("12345678910", "Adalberto");
        tempo = LocalDateTime.now();
        viagem = new Viagem(1, tempo, roteiro, passageiro, 12.5);
    }

    @Test
    public void testViagem() {
        assertEquals(1, viagem.getId());
        assertEquals(tempo, viagem.getDataHora());
        assertEquals(roteiro, viagem.getRoteiro());
        assertEquals(passageiro, viagem.getPassageiro());
        assertEquals(12.5, viagem.getValorCobrado());
    }
}