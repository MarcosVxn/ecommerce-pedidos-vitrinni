package com.ecommerce.pedidos.vitrinni.model.Employee;

import com.ecommerce.pedidos.vitrinni.model.Client.Pessoa;

public non-sealed class Funcionario extends Pessoa {

    private String matricula;
    private Cargo cargo;

    public Funcionario(String nome, String documento, String matricula, Cargo cargo) {
        super(nome, documento);
        setMatricula(matricula);
        setCargo(cargo);
    }

    public String getMatricula() {
        return matricula;
    }

    private void validarMatricula(String matricula){
        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("Matricula é obrigatória!");
        }
    }

    public void setMatricula(String matricula) {
        validarMatricula(matricula);
        this.matricula = matricula.trim();
    }

    public Cargo getCargo() {
        return cargo;
    }

    private void validarCargo(Cargo cargo){
        if (cargo == null) {
            throw new IllegalArgumentException(" Cargo é obrigatória!");
        }
    }

    public void setCargo(Cargo cargo) {
        validarCargo(cargo);
        this.cargo = cargo;
    }

    @Override
    public String getIdentificacao() {
        return getMatricula();
    }
}
