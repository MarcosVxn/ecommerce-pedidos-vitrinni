package com.ecommerce.pedidos.vitrinni.modelo;

public sealed abstract class Pessoa permits Cliente , Funcionario {

    private String nome;
    private String documento;

    public Pessoa(String nome, String documento) {
        setNome(nome);
        setDocumento(documento);
    }

    /**
     * * Define o nome da pessoa. * O nome não pode ser nulo ou vazio. * * @param
     * nome nome da pessoa * @throws IllegalArgumentException se o nome for nulo ou
     * vazio
     */

    public void setNome(String nome) {
        validarNome(nome);
        this.nome = nome.trim();
    }

    /**
     * * Define o documento da pessoa. * O documento não pode ser nulo ou vazio e
     * deve conter apenas números. * * @param documento documento da pessoa
     * * @throws IllegalArgumentException se o documento for inválido
     */

    public void setDocumento(String documento) {
        validarDocumento(documento);
        this.documento = documento.trim();
    }

    private void validarDocumento(String documento) {
        if (documento == null || documento.isBlank()) {
            throw new IllegalArgumentException("Documento inválido!");
        }

        if (!documento.matches("\\d+")) {
            throw new IllegalArgumentException(
                    "Documento deve conter apenas números!");
        }
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é Obrigatório");
        }
    }

    public String getNome() {
        return this.nome;
    }

    public String getDocumento() {
        return this.documento;
    }

    public abstract String getIdentificacao();

}