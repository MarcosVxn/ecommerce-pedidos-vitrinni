package com.ecommerce.pedidos.vitrinni.modelo;

public abstract class Pessoa {

    private String nome;
    private String documento;
    
    public Pessoa(String nome, String documento){
        setNome(nome);
        setDocumento(documento);
    }

    public void setNome(String nome){
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é Obrigatório");

        }

        this.nome = nome.trim();
    }

    public void setDocumento(String documento){
        if (documento == null ||  documento.isBlank()) {
            
        }
    }

    public abstract String getIdentificacao();
}
