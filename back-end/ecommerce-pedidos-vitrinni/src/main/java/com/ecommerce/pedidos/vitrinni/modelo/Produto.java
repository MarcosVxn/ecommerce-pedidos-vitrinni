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
        BigDecimal preco,int quantidadeEmEstoque){
            setCodigo(codigo);
            setNome(nome);
            setDescricao(descricao);
            setPreco(preco);
            setQuantidade(quantidadeEmEstoque);
            setAtivo(ativo);
        }


    public void setAtivo(boolean ativo){
        this.ativo = true;
    }


    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    public String getNome(){
        return this.nome;
    }

    public boolean isAtivo(){
        return this.ativo;
    }

    public BigDecimal getPreco(){
        return this.preco;
    }

    public void setPreco(BigDecimal preco){
        if (preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("preço não pode ser negativo");
        }
        this.preco = preco;
    }

    public void setQuantidade(int quantidadeEmEstoque){
        if (quantidadeEmEstoque <= 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public boolean temEstoqueDisponivel(int quantidadeDesejada){
        return ativo && quantidadeEmEstoque >= quantidadeDesejada;
    }

    @Override
    public String toString(){
        return String.format("[%-10s] %-15s R$ %9s (%6d em estoque)", codigo, nome, preco, quantidadeEmEstoque);
    }

    public void baixarEstoque(int quantidade){
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
        if (quantidade > this.quantidadeEmEstoque) {
            throw new IllegalArgumentException("Não pode ser que maior que a quantidade em estoque");
        }
        this.quantidadeEmEstoque = this.quantidadeEmEstoque - quantidade;
    }
}
