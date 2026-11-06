package com.ecommerce.pedidos.vitrinni.exceptions;

public class ProdutoNaoEncontrado extends ECommerceException {


    public ProdutoNaoEncontrado(String codigo) {
        super("Produto não encontrado. Codigo: " + codigo);
    }

}
