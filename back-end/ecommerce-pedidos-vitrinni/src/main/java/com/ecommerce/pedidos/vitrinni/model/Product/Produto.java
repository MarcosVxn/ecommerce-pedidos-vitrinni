package com.ecommerce.pedidos.vitrinni.model.Product;

import com.ecommerce.pedidos.vitrinni.exceptions.EstoqueInsuficienteException;

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
        setAtivo(true);
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNome(String nome) {
        validarNome(nome);
        this.nome = nome.trim();
    }
    private void validarNome(String nome){
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("O nome é obrigatório ");
        }
    }


    public void setCodigo(String codigo) {
        validarCodigo(codigo);
        this.codigo = codigo.trim();
    }
    private void validarCodigo(String codigo){
        if (codigo == null || codigo.isBlank()){
            throw new IllegalArgumentException("O codigo é obrigatório ");
        }
    }
    public String getCodigo() {
        return codigo;
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
        if (preco == null) {
            throw new IllegalArgumentException(
                    "Preço não informado."
            );
        }

        if (preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
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
        if (quantidadeEmEstoque < 0) {
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

    public void baixarEstoque(int quantidade)
            throws EstoqueInsuficienteException {

        if (quantidade <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser maior que zero."
            );
        }

        if (quantidade > quantidadeEmEstoque) {
            throw new EstoqueInsuficienteException(
                    "Estoque insuficiente para " + nome +
                            ". Disponível: " + quantidadeEmEstoque +
                            ", solicitado: " + quantidade
            );
        }

        quantidadeEmEstoque -= quantidade;
    }

    public void reporEstoque(int quantidade) {

        if (quantidade <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade para reposição deve ser maior que zero."
            );
        }

        quantidadeEmEstoque += quantidade;
    }


}
