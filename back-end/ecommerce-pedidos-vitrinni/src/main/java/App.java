import java.math.BigDecimal;
import java.time.LocalDate;

import com.ecommerce.pedidos.vitrinni.modelo.*;

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

        Cliente cliente = new Cliente(
                "Marcos",
                "12345678900",
                "marcos@email.com",
                "16999999999",
                "Ibaté - SP"
        );

        Pedido pedido = new Pedido(
                1,
                cliente,
                20261007,
                SituacaoPedido.ABERTO
        );

        System.out.println("Produto criado: " + produto);
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
                    20261007,
                    SituacaoPedido.ABERTO
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
                "Valor total do pedido: R$ " + pedido.calcularValorTotal()
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
                            + pedido.getFormaPagamento().getClass().getSimpleName()
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
        // RESULTADO FINAL
        // =====================================================

        System.out.println("\n=== ESTADO FINAL ===");
        System.out.println("Pedido: " + pedido);
        System.out.println("Valor total: R$ " + pedido.calcularValorTotal());

        System.out.println("\n=== TESTES FINALIZADOS ===");
    }
}