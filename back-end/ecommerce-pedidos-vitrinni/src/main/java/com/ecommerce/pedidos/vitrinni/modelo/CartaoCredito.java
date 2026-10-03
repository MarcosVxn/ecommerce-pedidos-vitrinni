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

    }

    public void setBandeira(BandeiraCartao bandeira){

    }

    public void setParcelas(int quantidadeParcelas){

    }

    @Override
    public void processar() {
        throw new UnsupportedOperationException("Unimplemented method 'processar'");
    }

}
