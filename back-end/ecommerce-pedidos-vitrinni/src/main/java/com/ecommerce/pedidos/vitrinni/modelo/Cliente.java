package com.ecommerce.pedidos.vitrinni.modelo;

public class Cliente extends Pessoa {

    private String email;
    private String telefone;
    private String endereco;

    public Cliente(
        String nome,
        String cpf,
        String email,
        String telefone,
        String endereco
    ) {
        super(nome, cpf);
        setEmail(email);
        setTelefone(telefone);
        setEndereco(endereco);
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("E-mail inválido!");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("E-mail deve conter @");
        }

        this.email = email;
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefone inválido!");
        }

        if (!telefone.matches("\\d+")) {
            throw new IllegalArgumentException(
                "Telefone deve conter apenas números!"
            );
        }

        this.telefone = telefone;
    }

    public void setEndereco(String endereco) {
        if (endereco == null || endereco.isBlank()) {
            throw new IllegalArgumentException("Endereço inválido!");
        }

        this.endereco = endereco;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String getIdentificacao() {
        return getNome() + "(CPF " + getDocumento() + ")";
    }
}