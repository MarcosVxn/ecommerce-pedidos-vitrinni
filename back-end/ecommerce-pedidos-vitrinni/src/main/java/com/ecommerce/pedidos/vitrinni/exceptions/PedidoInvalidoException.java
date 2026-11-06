package com.ecommerce.pedidos.vitrinni.exceptions;

public class PedidoInvalidoException extends ECommerceException {
    public PedidoInvalidoException(String message) {
        super(message);
    }

    public PedidoInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }
}
