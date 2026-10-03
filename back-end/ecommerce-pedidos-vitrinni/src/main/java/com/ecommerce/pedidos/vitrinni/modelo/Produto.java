package com.ecommerce.pedidos.vitrinni.modelo;

import java.math.BigDecimal;

public class Produto {

    // Atributos
    private String codigo;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private int quantidadeEmEstoque;
    private boolean ativo;

    public Produto(String codigo, String nome, String descricao,
            BigDecimal preco, int quantidadeEmEstoque) {
        setCodigo(codigo);
        setNome(nome);
        setDescricao(descricao);
        setPreco(preco);
        setQuantidade(quantidadeEmEstoque);
        setAtivo(ativo);
    }

    public void setAtivo(boolean ativo) {
        this.ativo = true;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public boolean isAtivo() {
        return this.ativo;
    }

    public BigDecimal getPreco() {
        return this.preco;
    }

    /**
     * * Define o preço do produto. * O preço deve ser maior que zero. * * @param
     * preco preço do produto * @throws IllegalArgumentException se o preço for
     * menor ou igual a zero
     */

    public void setPreco(BigDecimal preco) {
        validarPreco(preco);
        this.preco = preco;
    }

    private void validarPreco(BigDecimal preco) {
        if (preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("preço não pode ser negativo");
        }
    }

    /**
     * * Define a quantidade disponível em estoque. * A quantidade deve ser maior
     * que zero. * * @param quantidadeEmEstoque quantidade disponível em estoque
     * * @throws IllegalArgumentException se a quantidade for menor ou igual a zero
     */

    public void setQuantidade(int quantidadeEmEstoque) {
        validarQuantidade(quantidadeEmEstoque);
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    private void validarQuantidade(int quantidadeEmEstoque) {
        if (quantidadeEmEstoque <= 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
    }

    public boolean temEstoqueDisponivel(int quantidadeDesejada) {
        return ativo && quantidadeEmEstoque >= quantidadeDesejada;
    }

    @Override
    public String toString() {
        return String.format("[%-10s] %-15s R$ %9s (%6d em estoque)", codigo, nome, preco, quantidadeEmEstoque);
    }

    public void baixarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
        if (quantidade > this.quantidadeEmEstoque) {
            throw new IllegalArgumentException("Não pode ser que maior que a quantidade em estoque");
        }
        this.quantidadeEmEstoque = this.quantidadeEmEstoque - quantidade;
    }
}
