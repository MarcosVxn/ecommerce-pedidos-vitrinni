package com.ecommerce.pedidos.vitrinni.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public non-sealed class CartaoCredito extends FormaPagamento {

    private String numeroMascarado;
    private BandeiraCartao bandeira;
    private int quantidadeParcelas;

    protected CartaoCredito(BigDecimal valorPagamento, LocalDate dataDoPagamento, String numeroMascarado, BandeiraCartao bandeira, int quantidadeParcelas) {
        super(valorPagamento, dataDoPagamento);
        setNumeroMascarado(numeroMascarado);
        setBandeira(bandeira);
        setParcelas(quantidadeParcelas);
    }

    public void setNumeroMascarado(String numeroMascarado){
        validarNumeroInvalido(numeroMascarado);
        numeroMascarado = numeroMascarado.replaceAll("\\s+", "");
        validarNumeroMascarado(numeroMascarado);
        this.numeroMascarado = "**** **** **** " + numeroMascarado.substring(12);
    }

    private void validarNumeroInvalido(String numeroMascarado){
        if (numeroMascarado == null || numeroMascarado.isBlank()) {
            throw new IllegalArgumentException("Número do cartão inválido");
        }
    }

    private void validarNumeroMascarado(String numeroMascarado){
        if (!numeroMascarado.matches("\\d{16}")) {
            throw new IllegalArgumentException("Número do cartão deve conter 16 dígitos");
        }
    }

    public void setBandeira(BandeiraCartao bandeira){
        this.bandeira = bandeira;
    }

    public void setParcelas(int quantidadeParcelas){
        validarParcelas(quantidadeParcelas);
        this.quantidadeParcelas = quantidadeParcelas;
    }

    private void validarParcelas(int quantidadeParcelas){
        if (quantidadeParcelas < 1 || quantidadeParcelas > 12) {
            throw new IllegalArgumentException(
                "A quantidade de parcelas deve estar entre 1 e 12"
            );
        }
    }

    @Override
    public void processar() {
        System.out.println("Pagamento com cartão de crédito processado.");
    }

    @Override
    public String getResumo() {
        return super.getResumo()
                + " | Bandeira: " + bandeira
                + " | Parcelas: " + quantidadeParcelas;
    }
}
