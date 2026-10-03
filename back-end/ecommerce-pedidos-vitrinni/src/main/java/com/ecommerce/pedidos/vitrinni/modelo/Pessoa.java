package com.ecommerce.pedidos.vitrinni.modelo;

public abstract class Pessoa {

    private String nome;
    private String documento;

    public Pessoa(String nome, String documento){
        setNome(nome);
        setDocumento(documento);
    }

    public void setNome(String nome){
        validarNome(nome);
        this.nome = nome.trim();
    }

    public void setDocumento(String documento) {
        validarDocumento(documento);
        this.documento = documento.trim();
    }

    private void validarDocumento(String documento){
        if (documento == null || documento.isBlank()) {
            throw new IllegalArgumentException("Documento inválido!");
        }

        if (!documento.matches("\\d+")) {
            throw new IllegalArgumentException(
                "Documento deve conter apenas números!"
            );
        }
    }

    private void validarNome(String nome){
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é Obrigatório");
        }
    }

    public String getNome(){
        return this.nome;
    }

    public String getDocumento(){
        return this.documento;
    }

    
}