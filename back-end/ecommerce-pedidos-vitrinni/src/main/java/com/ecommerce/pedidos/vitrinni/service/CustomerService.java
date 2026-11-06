package com.ecommerce.pedidos.vitrinni.service;

import com.ecommerce.pedidos.vitrinni.exceptions.ClienteNaoEncontradoException;
import com.ecommerce.pedidos.vitrinni.model.Client.Cliente;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private final List<Cliente> customers = new ArrayList<>();

    public void registerCustomer(Cliente customer) {

        if (customer == null) {
            throw new IllegalArgumentException("Cliente não informado.");
        }

        boolean exists = customers.stream()
                .anyMatch(c -> c.getDocumento()
                        .equals(customer.getDocumento()));

        if (exists) {
            throw new IllegalArgumentException(
                    "Já existe um cliente com esse documento."
            );
        }

        customers.add(customer);
    }

    public Cliente findCustomerByDocument(String document)
            throws ClienteNaoEncontradoException {

        if (document == null || document.isBlank()) {
            throw new IllegalArgumentException("Documento não informado.");
        }

        String normalizedDocument = document.trim();

        if (!normalizedDocument.matches("\\d{11}")) {
            throw new IllegalArgumentException(
                    "CPF deve conter 11 números."
            );
        }

        return customers.stream()
                .filter(c -> c.getDocumento().equals(document))
                .findFirst()
                .orElseThrow(() ->
                        new ClienteNaoEncontradoException(document)
                );
    }
}