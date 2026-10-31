package com.ecommerce.pedidos.vitrinni.model.Payment;

@FunctionalInterface
public interface AutorizadorPagamento {

    boolean autorizar(FormaPagamento formaPagamento);
}