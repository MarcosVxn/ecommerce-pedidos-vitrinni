package com.ecommerce.pedidos.vitrinni.modelo;


public class Endereco {

    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(
            String logradouro,
            String numero,
            String bairro,
            String cidade,
            String estado,
            String cep) {

        setLogradouro(logradouro);
        setNumero(numero);
        setBairro(bairro);
        setCidade(cidade);
        setEstado(estado);
        setCep(cep);
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        validarTexto(logradouro, "Logradouro");
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        validarTexto(numero, "Número");
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        validarTexto(bairro, "Bairro");
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        validarTexto(cidade, "Cidade");
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        validarEstado(estado);
        this.estado = estado.toUpperCase();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        validarCep(cep);
        this.cep = cep;
    }

    private void validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(
                    campo + " é obrigatório"
            );
        }
    }

    private void validarEstado(String estado) {
        validarTexto(estado, "Estado");

        if (estado.length() != 2) {
            throw new IllegalArgumentException(
                    "Estado deve possuir 2 caracteres"
            );
        }
    }

    private void validarCep(String cep) {
        if (cep == null || cep.isBlank()) {
            throw new IllegalArgumentException("CEP é obrigatório");
        }

        if (!cep.matches("\\d{5}-?\\d{3}")) {
            throw new IllegalArgumentException(
                    "CEP deve possuir 8 números"
            );
        }
    }

    @Override
    public String toString() {
        return String.format(
                "%s, %s - %s, %s - %s, CEP: %s",
                logradouro,
                numero,
                bairro,
                cidade,
                estado,
                cep
        );
    }
}