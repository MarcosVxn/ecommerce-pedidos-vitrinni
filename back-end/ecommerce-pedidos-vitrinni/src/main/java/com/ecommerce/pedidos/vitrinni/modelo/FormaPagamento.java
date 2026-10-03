package com.ecommerce.pedidos.vitrinni.modelo;

import java.math.BigDecimal;

public abstract class FormaPagamento {
    
    private BigDecimal valorPagamento;


    public abstract void processar();
}
