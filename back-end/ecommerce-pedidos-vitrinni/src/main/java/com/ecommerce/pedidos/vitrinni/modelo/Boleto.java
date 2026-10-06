package com.ecommerce.pedidos.vitrinni.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public non-sealed class Boleto extends FormaPagamento{

    private String codigoDeBarras;
    private LocalDate dataDeVencimento;

    public Boleto(BigDecimal valorPagamento, LocalDate dataDoPagamento, String codigoDeBarras, LocalDate dataDeVencimento) {
        super(valorPagamento, dataDoPagamento);
        setCodigoDeBarras(codigoDeBarras);
        setDataDeVencimento(dataDeVencimento);
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        validarCodigo(codigoDeBarras);
        validarNumeroBarra(codigoDeBarras);
        this.codigoDeBarras = codigoDeBarras;
    }

    public void validarCodigo(String codigoDeBarras){
        if (codigoDeBarras == null || codigoDeBarras.isBlank()) {
            throw new IllegalArgumentException("Código de barras inválido");
        }
    }

    public void validarNumeroBarra(String codigoDeBarras){
        if (!codigoDeBarras.matches("\\d{44}")) {
            throw new IllegalArgumentException("Código de barras deve conter 44 dígitos");
        }
    }

    public void validarData(LocalDate dataDeVencimento){
        if (dataDeVencimento == null) {
            throw new IllegalArgumentException("Data de vencimento é obrigatória");
        }

        if (dataDeVencimento.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data de vencimento não pode estar no passado");
        }
    }

    public LocalDate getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(LocalDate dataDeVencimento) {
        validarData(dataDeVencimento);
        this.dataDeVencimento = dataDeVencimento;
    }

    @Override
    public void processar() {
        System.out.println("Pagamento com boleto processado.");
    }


}
