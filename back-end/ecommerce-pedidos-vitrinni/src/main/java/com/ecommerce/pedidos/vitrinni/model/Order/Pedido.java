package com.ecommerce.pedidos.vitrinni.model.Order;


import com.ecommerce.pedidos.vitrinni.model.Client.Cliente;
import com.ecommerce.pedidos.vitrinni.model.Payment.FormaPagamento;
import com.ecommerce.pedidos.vitrinni.model.Product.Produto;
import com.ecommerce.pedidos.vitrinni.exceptions.EstoqueInsuficienteException;
import com.ecommerce.pedidos.vitrinni.exceptions.PedidoInvalidoException;

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

    private void setNumero(int numero) {
        validarNumero(numero);
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    private void validarPedidoAberto() throws PedidoInvalidoException {
        if (situacao != SituacaoPedido.ABERTO) {
            throw new PedidoInvalidoException(
                    "Operação não permitida. O pedido está " + situacao
            );
        }
    }

    private void validarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente é obrigatório");
        }
    }

    private void setCliente(Cliente cliente) {
        validarCliente(cliente);
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void setData(int data) {
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

    private void setSituacao(SituacaoPedido situacao) throws PedidoInvalidoException {
        validarSituacao(situacao);
        validarMudancaSituacao(situacao);
        this.situacao = situacao;
    }

    private void validarMudancaSituacao(SituacaoPedido novaSituacao)
            throws PedidoInvalidoException {

        if (situacao == SituacaoPedido.PAGO
                && novaSituacao != SituacaoPedido.PAGO) {

            throw new PedidoInvalidoException(
                    "Não é possível alterar a situação de um pedido pago."
            );
        }

        if (situacao == SituacaoPedido.CANCELADO
                && novaSituacao != SituacaoPedido.CANCELADO) {

            throw new PedidoInvalidoException(
                    "Não é possível alterar a situação de um pedido cancelado."
            );
        }
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

    private void validarItens() throws PedidoInvalidoException {
        if (itens.isEmpty()) {
            throw new PedidoInvalidoException(
                    "Não é possível realizar o pagamento. O pedido não possui itens."
            );
        }
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void cancelarPedido() throws PedidoInvalidoException {

        validarPedidoAberto();

        for (ItemPedido item : itens) {
            item.getProduto().reporEstoque(item.getQuantidade());
        }

        setSituacao(SituacaoPedido.CANCELADO);
    }

    public void realizarPagamento(FormaPagamento formaPagamento)
            throws PedidoInvalidoException {

        validarPedidoAberto();
        validarFormaPagamento(formaPagamento);
        validarItens();

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

    public void removeItem(ItemPedido item)
            throws PedidoInvalidoException {

        validarPedidoAberto();

        if (item == null) {
            throw new IllegalArgumentException("Item é obrigatório.");
        }

        if (itens.remove(item)) {
            item.getProduto().reporEstoque(item.getQuantidade());
        }
    }

    public void addItem(Produto produto, int quantidade)
            throws PedidoInvalidoException, EstoqueInsuficienteException {

        validarPedidoAberto();

        if (produto == null) {
            throw new IllegalArgumentException("Produto é obrigatório.");
        }

        ItemPedido novoItem = new ItemPedido(
                produto,
                quantidade,
                produto.getPreco()
        );

        produto.baixarEstoque(quantidade);

        itens.add(novoItem);
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
