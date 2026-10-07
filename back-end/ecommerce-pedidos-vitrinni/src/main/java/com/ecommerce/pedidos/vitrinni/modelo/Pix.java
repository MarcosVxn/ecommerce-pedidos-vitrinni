package com.ecommerce.pedidos.vitrinni.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public non-sealed class Pix extends FormaPagamento {

    private String chavePix;
    private TipoChavePix tipoChavePix;

    public Pix(BigDecimal valorPagamento, LocalDate dataDoPagamento, String chavePix, TipoChavePix tipoChavePix) {
        super(valorPagamento, dataDoPagamento);
        setTipoChavePix(tipoChavePix);
        setChavePix(chavePix);
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        validarChavePix(chavePix);
        this.chavePix = chavePix;
    }

    private void validarChavePix(String chavePix) {

        if (chavePix == null || chavePix.isBlank()) {
            throw new IllegalArgumentException("Chave Pix é obrigatória!");
        }

        switch (tipoChavePix) {

            case CPF:
                if (!chavePix.matches("\\d{11}")) {
                    throw new IllegalArgumentException(
                            "CPF deve conter 11 números!");
                }
                break;

            case EMAIL:

                if (!chavePix.contains("@")) {
                    throw new IllegalArgumentException("E-mail deve conter @");
                }
                break;

            case TELEFONE:
                if (!chavePix.matches("\\d{10,11}")) {
                    throw new IllegalArgumentException(
                            "Telefone deve conter 10 ou 11 números!");
                }
                break;

        }
    }

    private void validarTipoChave(TipoChavePix tipoChavePix){
        if (tipoChavePix == null) {
            throw new IllegalArgumentException("Tipo do Pix é obrigatória!");
        }
    }

    public TipoChavePix getTipoChavePix() {
        return tipoChavePix;
    }

    public void setTipoChavePix(TipoChavePix tipoChavePix) {
        validarTipoChave(tipoChavePix);
        this.tipoChavePix = tipoChavePix;
    }

    @Override
    public void processar() {
        System.out.println("Pagamento com Pix processado.");
    }
    @Override
    public String getResumo() {
        return super.getResumo()
                + " | Chave Pix: " + chavePix
                + " | Tipo: " + tipoChavePix;
    }
}
