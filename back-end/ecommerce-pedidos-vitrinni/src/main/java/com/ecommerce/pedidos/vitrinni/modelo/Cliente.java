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
            String endereco) {
        super(nome, cpf);
        setEmail(email);
        setTelefone(telefone);
        setEndereco(endereco);
    }

    /**
     * Define o e-mail do cliente.
     * O e-mail não pode ser nulo ou vazio e deve conter @.
     *
     * @param email e-mail do cliente
     * @throws IllegalArgumentException se o e-mail for inválido
     */

    public void setEmail(String email) {
        validarEmail(email);
        this.email = email;
    }

    private void validarEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("E-mail inválido!");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("E-mail deve conter @");
        }

    }

    private void validarTelefone(String telefone) {
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefone inválido!");
        }

        if (!telefone.matches("\\d+")) {
            throw new IllegalArgumentException(
                    "Telefone deve conter apenas números!");
        }
    }

    private void validarEndereco(String endereco){
        if (endereco == null || endereco.isBlank()) {
            throw new IllegalArgumentException("Endereço inválido!");
        }
    }

    /**
     * Define o telefone do cliente.
     * O telefone não pode ser nulo ou vazio e deve conter apenas números.
     *
     * @param telefone telefone do cliente
     * @throws IllegalArgumentException se o telefone for inválido
     */

    public void setTelefone(String telefone) {
        validarTelefone(telefone);
        this.telefone = telefone;
    }

    /**
     * Define o endereço do cliente.
     * O endereço não pode ser nulo ou vazio.
     *
     * @param endereco endereço do cliente
     * @throws IllegalArgumentException se o endereço for nulo ou vazio
     */

    public void setEndereco(String endereco) {
        validarEndereco(endereco);
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
    public String toString() {
        return String.format(
                "Cliente{nome='%s', documento='%s', email='%s', telefone='%s', endereco='%s'}",
                getNome(),
                getDocumento(),
                email,
                telefone,
                endereco);
    }
}
