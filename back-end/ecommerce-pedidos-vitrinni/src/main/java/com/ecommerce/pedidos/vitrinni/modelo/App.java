package com.ecommerce.pedidos.vitrinni.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class App {

    public static void main(String[] args) {

        System.out.println("=== TESTE DE INTEGRIDADE ===");

        // =====================================================
        // OBJETOS VÁLIDOS
        // =====================================================

        System.out.println("\n--- Criando objetos válidos ---");

        Produto produto = new Produto(
                "001",
                "Notebook",
                "Notebook para estudos",
                new BigDecimal("3500.00"),
                10
        );

        Endereco endereco = new Endereco(
                "Rua Exemplo",
                "123",
                "Centro",
                "Ibaté",
                "SP",
                "14815-000"
        );

        Cliente cliente = new Cliente(
                "Marcos",
                "12345678900",
                "marcos@email.com",
                "16999999999",
                endereco
        );

        Pedido pedido = new Pedido(
                1,
                cliente,
                20261007
        );

        System.out.println("Produto criado: " + produto);
        System.out.println("Endereço criado: " + endereco);
        System.out.println("Cliente criado: " + cliente);
        System.out.println("Pedido criado: " + pedido);


        // =====================================================
        // TESTE 1 - CLIENTE OBRIGATÓRIO
        // =====================================================

        System.out.println("\n--- Teste: Pedido sem cliente ---");

        try {

            new Pedido(
                    2,
                    null,
                    20261007
            );

            System.out.println("ERRO: Pedido sem cliente foi aceito.");

        } catch (IllegalArgumentException e) {

            System.out.println("OK: Pedido sem cliente recusado.");
            System.out.println("Mensagem: " + e.getMessage());
        }


        // =====================================================
        // TESTE 2 - ITEM COM PRODUTO NULO
        // =====================================================

        System.out.println("\n--- Teste: Item sem produto ---");

        try {

            new ItemPedido(
                    null,
                    2,
                    new BigDecimal("100.00")
            );

            System.out.println("ERRO: Item sem produto foi aceito.");

        } catch (IllegalArgumentException e) {

            System.out.println("OK: Item sem produto recusado.");
            System.out.println("Mensagem: " + e.getMessage());
        }


        // =====================================================
        // TESTE 3 - QUANTIDADE INVÁLIDA
        // =====================================================

        System.out.println("\n--- Teste: Quantidade inválida ---");

        try {

            new ItemPedido(
                    produto,
                    0,
                    produto.getPreco()
            );

            System.out.println("ERRO: Quantidade inválida foi aceita.");

        } catch (IllegalArgumentException e) {

            System.out.println("OK: Quantidade inválida recusada.");
            System.out.println("Mensagem: " + e.getMessage());
        }


        // =====================================================
        // TESTE 4 - PREÇO INVÁLIDO
        // =====================================================

        System.out.println("\n--- Teste: Preço inválido ---");

        try {

            new ItemPedido(
                    produto,
                    2,
                    BigDecimal.ZERO
            );

            System.out.println("ERRO: Preço inválido foi aceito.");

        } catch (IllegalArgumentException e) {

            System.out.println("OK: Preço inválido recusado.");
            System.out.println("Mensagem: " + e.getMessage());
        }


        // =====================================================
        // TESTE 5 - PAGAMENTO DE PEDIDO VAZIO
        // =====================================================

        System.out.println("\n--- Teste: Pagamento de pedido vazio ---");

        FormaPagamento pix = new Pix(
                new BigDecimal("100.00"),
                LocalDate.now(),
                "16999999999",
                TipoChavePix.TELEFONE
        );

        try {

            pedido.realizarPagamento(pix);

            System.out.println("ERRO: Pedido vazio foi pago.");

        } catch (IllegalArgumentException e) {

            System.out.println("OK: Pedido vazio recusado para pagamento.");
            System.out.println("Mensagem: " + e.getMessage());
        }


        // =====================================================
        // TESTE 6 - ADICIONAR ITEM VÁLIDO
        // =====================================================

        System.out.println("\n--- Teste: Adicionar item válido ---");

        pedido.addItem(produto, 2);

        System.out.println("Item adicionado com sucesso.");
        System.out.println("Quantidade de itens: " + pedido.getItens().size());


        // =====================================================
        // TESTE 7 - CÁLCULO DO VALOR TOTAL
        // =====================================================

        System.out.println("\n--- Teste: Valor total ---");

        System.out.println(
                "Valor total do pedido: R$ "
                        + pedido.calcularValorTotal()
        );


        // =====================================================
        // TESTE 8 - PAGAMENTO VÁLIDO
        // =====================================================

        System.out.println("\n--- Teste: Pagamento válido ---");

        FormaPagamento pixValido = new Pix(
                pedido.calcularValorTotal(),
                LocalDate.now(),
                "16999999999",
                TipoChavePix.TELEFONE
        );

        try {

            pedido.realizarPagamento(pixValido);

            System.out.println("OK: Pagamento processado.");
            System.out.println(
                    "Forma de pagamento: "
                            + pedido.getFormaPagamento()
                            .getClass()
                            .getSimpleName()
            );

        } catch (IllegalArgumentException e) {

            System.out.println("ERRO: Pagamento válido foi recusado.");
            System.out.println("Mensagem: " + e.getMessage());
        }


        // =====================================================
        // TESTE 9 - LISTA DE ITENS PROTEGIDA
        // =====================================================

        System.out.println("\n--- Teste: Proteção da lista de itens ---");

        try {

            pedido.getItens().clear();

            System.out.println("ERRO: Lista interna pôde ser modificada.");

        } catch (UnsupportedOperationException e) {

            System.out.println("OK: Lista de itens protegida.");
        }


        // =====================================================
        // TESTE 10 - SITUAÇÃO INICIAL DO PEDIDO
        // =====================================================

        System.out.println("\n--- Teste: Situação inicial do pedido ---");

        Pedido pedidoAberto = new Pedido(
                2,
                cliente,
                20261007
        );

        if (pedidoAberto.getSituacao() == SituacaoPedido.ABERTO) {

            System.out.println("OK: Pedido iniciou como ABERTO.");

        } else {

            System.out.println(
                    "ERRO: Pedido iniciou com situação "
                            + pedidoAberto.getSituacao()
            );
        }


        // =====================================================
        // TESTE 11 - ABERTO → PAGO
        // =====================================================

        System.out.println("\n--- Teste: ABERTO → PAGO ---");

        pedidoAberto.addItem(produto, 1);

        FormaPagamento pixPagamento = new Pix(
                pedidoAberto.calcularValorTotal(),
                LocalDate.now(),
                "16999999999",
                TipoChavePix.TELEFONE
        );

        try {

            pedidoAberto.realizarPagamento(pixPagamento);

            if (pedidoAberto.getSituacao() == SituacaoPedido.PAGO) {

                System.out.println(
                        "OK: Pedido mudou de ABERTO para PAGO."
                );

            } else {

                System.out.println(
                        "ERRO: Pedido não mudou para PAGO. Situação atual: "
                                + pedidoAberto.getSituacao()
                );
            }

        } catch (IllegalArgumentException e) {

            System.out.println("ERRO: Pagamento foi recusado.");
            System.out.println("Mensagem: " + e.getMessage());
        }


        // =====================================================
        // TESTE 12 - PAGO → CANCELADO
        // =====================================================

        System.out.println("\n--- Teste: PAGO → CANCELADO ---");

        try {

            pedidoAberto.cancelarPedido();

            System.out.println(
                    "ERRO: Pedido pago pôde ser cancelado."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "OK: Pedido pago não pôde ser cancelado."
            );

            System.out.println("Mensagem: " + e.getMessage());
        }


        // =====================================================
        // TESTE 13 - CANCELADO → PAGO
        // =====================================================

        System.out.println("\n--- Teste: CANCELADO → PAGO ---");

        Pedido pedidoCancelado = new Pedido(
                3,
                cliente,
                20261007
        );

        pedidoCancelado.addItem(produto, 1);
        pedidoCancelado.cancelarPedido();

        System.out.println(
                "Situação antes da tentativa de pagamento: "
                        + pedidoCancelado.getSituacao()
        );

        FormaPagamento pixCancelado = new Pix(
                pedidoCancelado.calcularValorTotal(),
                LocalDate.now(),
                "16999999999",
                TipoChavePix.TELEFONE
        );

        try {

            pedidoCancelado.realizarPagamento(pixCancelado);

            System.out.println(
                    "ERRO: Pedido cancelado pôde ser pago."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "OK: Pedido cancelado não pôde ser pago."
            );

            System.out.println("Mensagem: " + e.getMessage());
        }


        // =====================================================
        // ISSUE #51 - FINALIZAR FORMAS DE PAGAMENTO
        // =====================================================

        System.out.println("\n========================================");
        System.out.println("       TESTES DA ISSUE #51");
        System.out.println("========================================");


        // =====================================================
        // TESTE 14 - PIX VÁLIDO
        // =====================================================

        System.out.println("\n--- Teste 14: Pix válido ---");

        try {

            Pedido pedidoPix = new Pedido(
                    4,
                    cliente,
                    20261007
            );

            pedidoPix.addItem(produto, 1);

            FormaPagamento pixTeste = new Pix(
                    pedidoPix.calcularValorTotal(),
                    LocalDate.now(),
                    "16999999999",
                    TipoChavePix.TELEFONE
            );

            pedidoPix.realizarPagamento(pixTeste);

            if (pedidoPix.getFormaPagamento() instanceof Pix) {

                System.out.println(
                        "OK: Pix processado e integrado ao Pedido."
                );

            } else {

                System.out.println(
                        "ERRO: Pix não foi integrado ao Pedido."
                );
            }

        } catch (IllegalArgumentException e) {

            System.out.println("ERRO: Pix válido foi recusado.");
            System.out.println("Mensagem: " + e.getMessage());
        }


        // =====================================================
        // TESTE 15 - BOLETO VÁLIDO
        // =====================================================

        System.out.println("\n--- Teste 15: Boleto válido ---");

        try {

            Pedido pedidoBoleto = new Pedido(
                    5,
                    cliente,
                    20261007
            );

            pedidoBoleto.addItem(produto, 1);

            FormaPagamento boletoTeste = new Boleto(
                    pedidoBoleto.calcularValorTotal(),
                    LocalDate.now(),
                    "12345678901234567890123456789012345678901234",
                    LocalDate.now().plusDays(10)
            );

            pedidoBoleto.realizarPagamento(boletoTeste);

            if (pedidoBoleto.getFormaPagamento() instanceof Boleto) {

                System.out.println(
                        "OK: Boleto processado e integrado ao Pedido."
                );

            } else {

                System.out.println(
                        "ERRO: Boleto não foi integrado ao Pedido."
                );
            }

        } catch (IllegalArgumentException e) {

            System.out.println("ERRO: Boleto válido foi recusado.");
            System.out.println("Mensagem: " + e.getMessage());
        }


        // =====================================================
        // TESTE 16 - CARTÃO DE CRÉDITO VÁLIDO
        // =====================================================

        System.out.println(
                "\n--- Teste 16: Cartão de crédito válido ---"
        );

        try {

            Pedido pedidoCartao = new Pedido(
                    6,
                    cliente,
                    20261007
            );

            pedidoCartao.addItem(produto, 1);

            FormaPagamento cartaoTeste = new CartaoCredito(
                    pedidoCartao.calcularValorTotal(),
                    LocalDate.now(),
                    "1234567812345678",
                    BandeiraCartao.VISA,
                    3
            );

            pedidoCartao.realizarPagamento(cartaoTeste);

            if (pedidoCartao.getFormaPagamento()
                    instanceof CartaoCredito) {

                System.out.println(
                        "OK: Cartão processado e integrado ao Pedido."
                );

            } else {

                System.out.println(
                        "ERRO: Cartão não foi integrado ao Pedido."
                );
            }

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "ERRO: Cartão válido foi recusado."
            );

            System.out.println(
                    "Mensagem: " + e.getMessage()
            );
        }


        // =====================================================
        // TESTE 17 - PIX COM CHAVE INVÁLIDA
        // =====================================================

        System.out.println(
                "\n--- Teste 17: Pix com chave inválida ---"
        );

        try {

            new Pix(
                    new BigDecimal("100.00"),
                    LocalDate.now(),
                    "chave-invalida",
                    TipoChavePix.TELEFONE
            );

            System.out.println(
                    "ERRO: Pix com chave inválida foi aceito."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "OK: Pix com chave inválida foi recusado."
            );

            System.out.println(
                    "Mensagem: " + e.getMessage()
            );
        }


        // =====================================================
        // TESTE 18 - BOLETO COM CÓDIGO INVÁLIDO
        // =====================================================

        System.out.println(
                "\n--- Teste 18: Boleto com código inválido ---"
        );

        try {

            new Boleto(
                    new BigDecimal("100.00"),
                    LocalDate.now(),
                    "123",
                    LocalDate.now().plusDays(10)
            );

            System.out.println(
                    "ERRO: Boleto com código inválido foi aceito."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "OK: Boleto com código inválido foi recusado."
            );

            System.out.println(
                    "Mensagem: " + e.getMessage()
            );
        }


        // =====================================================
        // TESTE 19 - CARTÃO SEM BANDEIRA
        // =====================================================

        System.out.println(
                "\n--- Teste 19: Cartão sem bandeira ---"
        );

        try {

            new CartaoCredito(
                    new BigDecimal("100.00"),
                    LocalDate.now(),
                    "1234567812345678",
                    null,
                    3
            );

            System.out.println(
                    "ERRO: Cartão sem bandeira foi aceito."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "OK: Cartão sem bandeira foi recusado."
            );

            System.out.println(
                    "Mensagem: " + e.getMessage()
            );
        }


        // =====================================================
        // TESTE 20 - CARTÃO COM PARCELAS INVÁLIDAS
        // =====================================================

        System.out.println(
                "\n--- Teste 20: Cartão com parcelas inválidas ---"
        );

        try {

            new CartaoCredito(
                    new BigDecimal("100.00"),
                    LocalDate.now(),
                    "1234567812345678",
                    BandeiraCartao.VISA,
                    0
            );

            System.out.println(
                    "ERRO: Cartão com parcelas inválidas foi aceito."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "OK: Cartão com parcelas inválidas foi recusado."
            );

            System.out.println(
                    "Mensagem: " + e.getMessage()
            );
        }


        // =====================================================
        // TESTES DA CLASSE ENDERECO
        // =====================================================

        System.out.println("\n========================================");
        System.out.println("       TESTES DA CLASSE ENDERECO");
        System.out.println("========================================");


        // =====================================================
        // TESTE 21 - ENDEREÇO VÁLIDO
        // =====================================================

        System.out.println("\n--- Teste 21: Endereço válido ---");

        try {

            Endereco enderecoTeste = new Endereco(
                    "Rua das Flores",
                    "100",
                    "Centro",
                    "São Carlos",
                    "SP",
                    "13560-000"
            );

            System.out.println(
                    "OK: Endereço válido criado."
            );

            System.out.println(
                    "Endereço: " + enderecoTeste
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "ERRO: Endereço válido foi recusado."
            );

            System.out.println(
                    "Mensagem: " + e.getMessage()
            );
        }


        // =====================================================
        // TESTE 22 - ENDEREÇO SEM LOGRADOURO
        // =====================================================

        System.out.println(
                "\n--- Teste 22: Endereço sem logradouro ---"
        );

        try {

            new Endereco(
                    "",
                    "100",
                    "Centro",
                    "São Carlos",
                    "SP",
                    "13560-000"
            );

            System.out.println(
                    "ERRO: Endereço sem logradouro foi aceito."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "OK: Endereço sem logradouro foi recusado."
            );

            System.out.println(
                    "Mensagem: " + e.getMessage()
            );
        }


        // =====================================================
        // TESTE 23 - ESTADO INVÁLIDO
        // =====================================================

        System.out.println(
                "\n--- Teste 23: Estado inválido ---"
        );

        try {

            new Endereco(
                    "Rua das Flores",
                    "100",
                    "Centro",
                    "São Carlos",
                    "Sao Paulo",
                    "13560-000"
            );

            System.out.println(
                    "ERRO: Estado inválido foi aceito."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "OK: Estado inválido foi recusado."
            );

            System.out.println(
                    "Mensagem: " + e.getMessage()
            );
        }


        // =====================================================
        // TESTE 24 - CEP INVÁLIDO
        // =====================================================

        System.out.println(
                "\n--- Teste 24: CEP inválido ---"
        );

        try {

            new Endereco(
                    "Rua das Flores",
                    "100",
                    "Centro",
                    "São Carlos",
                    "SP",
                    "123"
            );

            System.out.println(
                    "ERRO: CEP inválido foi aceito."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "OK: CEP inválido foi recusado."
            );

            System.out.println(
                    "Mensagem: " + e.getMessage()
            );
        }


        // =====================================================
        // TESTE 25 - CLIENTE SEM ENDEREÇO
        // =====================================================

        System.out.println(
                "\n--- Teste 25: Cliente sem endereço ---"
        );

        try {

            new Cliente(
                    "Cliente Teste",
                    "12345678900",
                    "teste@email.com",
                    "16999999999",
                    null
            );

            System.out.println(
                    "ERRO: Cliente sem endereço foi aceito."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "OK: Cliente sem endereço foi recusado."
            );

            System.out.println(
                    "Mensagem: " + e.getMessage()
            );
        }


        // =====================================================
        // RESULTADO FINAL
        // =====================================================

        System.out.println("\n=== ESTADO FINAL ===");

        System.out.println(
                "Pedido principal: " + pedido
        );

        System.out.println(
                "Situação do pedido principal: "
                        + pedido.getSituacao()
        );

        System.out.println(
                "Valor total: R$ "
                        + pedido.calcularValorTotal()
        );

        System.out.println(
                "Pedido cancelado: " + pedidoCancelado
        );

        System.out.println(
                "Situação do pedido cancelado: "
                        + pedidoCancelado.getSituacao()
        );

        System.out.println(
                "Endereço do cliente principal: "
                        + cliente.getEndereco()
        );

        System.out.println("\n=== TESTES FINALIZADOS ===");
    }
}