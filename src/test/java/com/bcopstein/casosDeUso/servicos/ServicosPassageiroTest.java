package com.bcopstein.casosDeUso.servicos;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.bcopstein.casosDeUso.Politicas.CalculoCustoViagem;
import com.bcopstein.casosDeUso.Repositorios.RepositorioBairros;
import com.bcopstein.casosDeUso.Repositorios.RepositorioPassageiros;
import com.bcopstein.casosDeUso.Servicos.ServicosPassageiro;
import com.bcopstein.entidades.Passageiro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.Viagem;
import com.bcopstein.interfaces.Persistencia.RepositorioBairrosImplMem;
import com.bcopstein.interfaces.Persistencia.RepositorioPassageirosImplMem;

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