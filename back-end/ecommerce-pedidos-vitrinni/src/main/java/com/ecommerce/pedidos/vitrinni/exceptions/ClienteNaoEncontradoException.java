package com.ecommerce.pedidos.vitrinni.exceptions;

public class ClienteNaoEncontradoException extends ECommerceException {

    public ClienteNaoEncontradoException(String customerId) {
        super("Cliente não encontrado. ID: " + customerId);
    }

}
