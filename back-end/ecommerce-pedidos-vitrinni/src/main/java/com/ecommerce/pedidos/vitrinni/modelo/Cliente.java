package com.ecommerce.pedidos.vitrinni.modelo;

public class Cliente extends Pessoa {
    private String email;
    private String telefone;
    private String endereco;
}

public Cliente(String nome, String cpf, String email,String telefone,String endereco){
    super(nome, cpf);
    setEmail(email);
    setTelefone(telefone);
}

@Override 
public String getIdentificacao(){
    return getNome()+ "(CPF "+ getDocumento()+ ")"
}