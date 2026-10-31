package com.ecommerce.pedidos.vitrinni.exceptions;

public class PagamentoRecusadoException extends ECommerceException {

    public PagamentoRecusadoException(String message) {
        super(message);
    }

    public PagamentoRecusadoException(String message, Throwable cause) {
        super(message, cause);
    }
}