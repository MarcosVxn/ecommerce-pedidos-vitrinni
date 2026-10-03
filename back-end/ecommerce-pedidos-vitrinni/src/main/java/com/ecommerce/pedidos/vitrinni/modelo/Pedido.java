package com.ecommerce.pedidos.vitrinni.modelo;


import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int numero;
    private Cliente cliente;
    private int data;
    private SituacaoPedido situacao;
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

    public void setSituacao(SituacaoPedido situacao) {
        this.situacao = situacao;
    }

    public SituacaoPedido getSituacao() {
        return situacao;
    }

    public List<ItemPedido> getItens() {
       return  List.copyOf(itens);
    }


    public void removeItens(ItemPedido item){
        itens.remove(item);
    }

    public void adicionarItem(Produto produto, int quantidade){
        itens.add(new ItemPedido(produto, quantidade, produto.getPreco()));
        System.out.println("Item adicionado com sucesso");
    }

    @Override
    public String toString() {
        return String.format(
            "Pedido{numero=%d, cliente=%s, data=%d, situacao='%s', itens=%s}",
            numero,
            cliente,
            data,
            situacao,
            itens
    );
}
}
