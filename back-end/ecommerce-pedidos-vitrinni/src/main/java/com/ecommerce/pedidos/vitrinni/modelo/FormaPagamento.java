package com.ecommerce.pedidos.vitrinni.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public sealed abstract class FormaPagamento permits CartaoCredito , Boleto {

    private BigDecimal valorPagamento;
    private LocalDate dataDoPagamento;

    protected FormaPagamento(BigDecimal valorPagamento, LocalDate dataDoPagamento) {
        setValorPagamento(valorPagamento);
        setDataDoPagamento(dataDoPagamento);
    }

    public void setValorPagamento(BigDecimal valorPagamento) {
        validarPagamento(valorPagamento);
        this.valorPagamento = valorPagamento;
    }

    public BigDecimal getValorPagamento() {
        return valorPagamento;
    }

    private void validarPagamento(BigDecimal valorPagamento) {
        if (valorPagamento == null || valorPagamento.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do pagamento é inválido");
        }
    }

    private void validarDataPagamento(LocalDate dataDoPagamento) {
        if (dataDoPagamento == null) {
            throw new IllegalArgumentException("Data do pagamento é obrigatória");
        }
    }

    public void setDataDoPagamento(LocalDate dataDoPagamento) {
        validarDataPagamento(dataDoPagamento);
        this.dataDoPagamento = dataDoPagamento;
    }

    public LocalDate getDataDoPagamento() {
        return dataDoPagamento;
    }

    public abstract void processar();

    public String getResumo() {
        return String.format(
                "Valor: R$ %s | Data do pagamento: %s",
                valorPagamento,
                dataDoPagamento);
    }

    @Override
    public String toString() {
        return String.format(
                "FormaPagamento{valor=%s, data=%s}",
                valorPagamento,
                dataDoPagamento);
    }

}
