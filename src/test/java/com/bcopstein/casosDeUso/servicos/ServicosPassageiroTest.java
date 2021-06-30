package com.verival.casosDeUso.servicos;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;


import com.verival.casosDeUso.politicas.CalculoCustoViagem;
import com.verival.casosDeUso.Repositorios.RepositorioBairros;
import com.verival.casosDeUso.Repositorios.RepositorioPassageiros;
import com.verival.casosDeUso.Servicos.ServicosPassageiro;
import com.verival.entidades.Passageiro;
import com.verival.entidades.Roteiro;
import com.verival.entidades.Viagem;
import com.verival.interfaces.Persistencia.RepositorioBairrosImplMem;
import com.verival.interfaces.Persistencia.RepositorioPassageirosImplMem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServicosPassageiroTest {
    Passageiro passageiro;
    Roteiro roteiro;
    Viagem viagem;
    RepositorioBairros repBairros;
    RepositorioPassageiros repPassageiros;
    ServicosPassageiro servPassageiros;
    CalculoCustoViagem ccv;

    @BeforeEach
    public void setup() {
        repBairros = new RepositorioBairrosImplMem();
        repPassageiros = new RepositorioPassageirosImplMem();
        servPassageiros = new ServicosPassageiro(repBairros, repPassageiros, ccv);

        roteiro = mock(Roteiro.class);
    }

    @Test
    public void createNewServiceTest() {
      assertTrue(servPassageiros.getListaBairros() != null);
      assertTrue(servPassageiros.getPassageirosCadastrados() != null);
    }

}