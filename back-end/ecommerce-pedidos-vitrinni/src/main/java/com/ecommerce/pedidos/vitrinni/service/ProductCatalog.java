package com.ecommerce.pedidos.vitrinni.service;

import com.ecommerce.pedidos.vitrinni.exceptions.ProdutoNaoEncontrado;
import com.ecommerce.pedidos.vitrinni.model.Product.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {

    private final List<Produto> products = new ArrayList<>();

    public void registerProduct(Produto product) {

        if (product == null) {
            throw new IllegalArgumentException("Produto não informado.");
        }

        boolean exists = products.stream()
                .anyMatch(p -> p.getCodigo().equals(product.getCodigo()));

        if (exists) {
            throw new IllegalArgumentException(
                    "Este produto já está cadastrado."
            );
        }

        products.add(product);
    }

    public Produto findByCodigo(String codigo)
            throws ProdutoNaoEncontrado {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código não informado.");
        }

        return products.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new ProdutoNaoEncontrado(codigo));
    }
}