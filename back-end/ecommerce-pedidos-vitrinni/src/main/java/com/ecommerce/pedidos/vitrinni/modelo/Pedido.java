package com.ecommerce.pedidos.vitrinni.modelo;


import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int numero;
    private Cliente cliente;
    private int data;
    private String situacao;
    private final List<ItemPedido> itens = new ArrayList<>();

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }


    public void removeItens(ItemPedido item){
        itens.remove(item);
    }

    public void adicionarItem(Produto produto, int quantidade){
        if (itens.isEmpty()) {
          throw new IllegalArgumentException("Pedido sem itens");  
        }
        itens.add(new ItemPedido(produto, quantidade, produto.getPreco()));
        System.out.println("Pedido Criado");
    }

}
