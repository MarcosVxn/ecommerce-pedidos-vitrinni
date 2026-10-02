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

    public void setDocumento(String documento) {
        if (documento == null || documento.isBlank()) {
            throw new IllegalArgumentException("Documento inválido!");
        }

        if (!documento.matches("\\d+")) {
            throw new IllegalArgumentException(
                "Documento deve conter apenas números!"
            );
        }

        this.documento = documento.trim();
    }

    public String getNome(){
        return this.nome;
    }

    public String getDocumento(){
        return this.documento;
    }

    
}