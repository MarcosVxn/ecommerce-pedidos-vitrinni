package com.ecommerce.pedidos.vitrinni.model.Client;

public non-sealed class Cliente extends Pessoa {

    private String email;
    private String telefone;
    private Endereco endereco;

    public Cliente(
            String nome,
            String cpf,
            String email,
            String telefone,
            Endereco endereco) {
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
        if (email == null ||
                !email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {

            throw new IllegalArgumentException(
                    "Formato de e-mail inválido."
            );
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


   private void validarEndereco(Endereco endereco){
       if (endereco == null){
           throw new IllegalArgumentException("Endereço é obrigatório");
       }
   }

    public void setEndereco(Endereco endereco) {
       validarEndereco(endereco);
       this.endereco = endereco;
    }

    public Endereco getEndereco() {
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
        return getDocumento();
    }

    @Override
    protected void validarDocumento(String documento) {

        super.validarDocumento(documento);

        if (!documento.trim().matches("\\d{11}")) {
            throw new IllegalArgumentException(
                    "CPF deve conter 11 números."
            );
        }
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
