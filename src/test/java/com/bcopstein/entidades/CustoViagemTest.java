package com.bcopstein.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.bcopstein.casosDeUso.Politicas.CalculoCustoViagem;
import com.bcopstein.casosDeUso.Politicas.CalculoCustoViagemBasico;
import com.bcopstein.casosDeUso.Politicas.CustoViagem;
import org.junit.jupiter.api.Test;

public class CustoViagemTest {
    @Test
    public void testCost() {
        CalculoCustoViagem bill = mock(CalculoCustoViagemBasico.class);
        when(bill.custoViagem()).thenReturn(35.0);

        CustoViagem cost = new CustoViagem(bill);
        double cost1 = cost.custoViagem(null, null);
        double cost2 = 35;
        assertEquals(cost1, cost2, 0.001);
    }

}
