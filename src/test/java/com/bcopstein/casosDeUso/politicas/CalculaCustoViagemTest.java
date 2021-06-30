package com.bcopstein.casosDeUso.politicas;

import org.junit.jupiter.api.Test;
import com.bcopstein.casosDeUso.Politicas.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculaCustoViagemTest {
    @Test
    public void calculaCustoViagemTest() {
        CalculoCustoViagem ccv = mock(CalculoCustoViagemBasico.class);
        when(ccv.custoViagem()).thenReturn(50.0);
        double respEsperada = 50.0;
        CustoViagem cv = new CustoViagem(ccv);
        double respObtida = cv.custoViagem(null, null);
        assertEquals(respEsperada, respObtida, 0.0001);
    }
}