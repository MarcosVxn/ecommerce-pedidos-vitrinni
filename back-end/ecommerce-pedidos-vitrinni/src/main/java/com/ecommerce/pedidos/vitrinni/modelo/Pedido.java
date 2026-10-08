package com.ecommerce.pedidos.vitrinni.modelo;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int numero;
    private Cliente cliente;
    private int data;
    private FormaPagamento formaPagamento;
    private SituacaoPedido situacao = SituacaoPedido.ABERTO;
    private final List<ItemPedido> itens = new ArrayList<>();

    public Pedido(int numero, Cliente cliente, int data) {
        setNumero(numero);
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

    private void validarMudancaSituacao(SituacaoPedido novaSituacao){
        if (situacao == SituacaoPedido.PAGO && novaSituacao != SituacaoPedido.PAGO){
            throw new IllegalArgumentException("Pedido pago não pode ter sua situação alterada");
        }
        if (situacao == SituacaoPedido.CANCELADO && novaSituacao != SituacaoPedido.CANCELADO){
            throw new IllegalArgumentException(
                    "Pedido cancelado não pode ter sua situação alterada"
            );
        }
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

    private void setSituacao(SituacaoPedido situacao) {
        validarSituacao(situacao);
        validarMudancaSituacao(situacao);
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

    public void cancelarPedido(){
        setSituacao(SituacaoPedido.CANCELADO);
    }

    public void realizarPagamento(FormaPagamento formaPagamento){
        validarFormaPagamento(formaPagamento);
        validarItens();
        validarMudancaSituacao(SituacaoPedido.PAGO);

        formaPagamento.processar();
        setFormaPagamento(formaPagamento);
        setSituacao(SituacaoPedido.PAGO);
    }

    public BigDecimal calcularValorTotal(){
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ItemPedido item : itens) {
            valorTotal = valorTotal.add(item.calcularValorTotal());
        }

        return valorTotal;
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
