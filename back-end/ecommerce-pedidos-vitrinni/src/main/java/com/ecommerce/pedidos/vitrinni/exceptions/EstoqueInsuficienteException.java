package com.ecommerce.pedidos.vitrinni.exceptions;

public class EstoqueInsuficienteException extends ECommerceException {

    public EstoqueInsuficienteException(String message) {
        super(message);
    }

    public EstoqueInsuficienteException(String message, Throwable cause) {
        super(message, cause);
    }
}
