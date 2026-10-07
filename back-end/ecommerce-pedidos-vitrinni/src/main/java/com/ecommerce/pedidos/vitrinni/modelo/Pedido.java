package com.ecommerce.pedidos.vitrinni.modelo;


import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int numero;
    private Cliente cliente;
    private int data;
    private FormaPagamento formaPagamento;
    private SituacaoPedido situacao;
    private final List<ItemPedido> itens = new ArrayList<>();

    public Pedido(int numero, Cliente cliente, int data, SituacaoPedido situacao) {
        setNumero(numero);
        setSituacao(situacao);
        setData(data);
        setCliente(cliente);
    }

    private void validarNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("Número do pedido inválido");
        }
    }

    public void setNumero(int numero) {
        validarNumero(numero);
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    private void validarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente é obrigatório");
        }
    }

    public void setCliente(Cliente cliente) {
        validarCliente(cliente);
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

    private void validarSituacao(SituacaoPedido situacao) {
        if (situacao == null) {
            throw new IllegalArgumentException("Situação do pedido é obrigatória");
        }
    }

    public void setSituacao(SituacaoPedido situacao) {
        validarSituacao(situacao);
        this.situacao = situacao;
    }

    public SituacaoPedido getSituacao() {
        return situacao;
    }

    public List<ItemPedido> getItens() {
       return  List.copyOf(itens);
    }

    private void validarFormaPagamento(FormaPagamento formaPagamento){
        if (formaPagamento == null){
            throw new IllegalArgumentException("Forma de pagamento invalida");
        }
    }

    private void setFormaPagamento(FormaPagamento formaPagamento) {
        validarFormaPagamento(formaPagamento);
        this.formaPagamento = formaPagamento;
    }


    private void validarItens(){
        if (itens.isEmpty()){
            throw new IllegalArgumentException("O pedido deve possuir pelo menos um item");
        }
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void realizarPagamento(FormaPagamento formaPagamento){
        validarFormaPagamento(formaPagamento);
        validarItens();

        formaPagamento.processar();
        setFormaPagamento(formaPagamento);
    }


    public void removeItem(ItemPedido item){
        itens.remove(item);
    }

    public void addItem(Produto produto, int quantidade){
        itens.add(new ItemPedido(produto, quantidade, produto.getPreco()));
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
