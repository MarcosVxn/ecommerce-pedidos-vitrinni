package com.ecommerce.pedidos.vitrinni.model.Order;

import com.ecommerce.pedidos.vitrinni.model.Product.Produto;

import java.math.BigDecimal;

public class ItemPedido {

    private Produto produto;
    private int quantidade;
    private BigDecimal preco;

    public ItemPedido(Produto produto, int quantidade, BigDecimal preco) {
        setProduto(produto);
        setQuantidade(quantidade);
        setPreco(preco);
    }

    public Produto getProduto() {
        return produto;
    }

    private void validarProduto(Produto produto){
        if (produto == null){
            throw new IllegalArgumentException("Produto Invalido");
        }
    }

    private void setProduto(Produto produto) {
        validarProduto(produto);
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    private void validarQuantidade(int quantidade){
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade Invalida");
        }
    }

    public void setQuantidade(int quantidade) {
        validarQuantidade(quantidade);
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    private void validarPreco(BigDecimal preco){
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço inválido");
        }
    }

    private void setPreco(BigDecimal preco) {
        validarPreco(preco);
        this.preco = preco;
    }

    public BigDecimal calcularValorTotal(){
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }

    @Override
    public String toString() {
        return String.format(
                "ItemPedido{produto=%s, quantidade=%d, preco=R$ %.2f, valorTotal=R$ %.2f}",
                produto,
                quantidade,
                preco,
                calcularValorTotal()
        );
    }
}
