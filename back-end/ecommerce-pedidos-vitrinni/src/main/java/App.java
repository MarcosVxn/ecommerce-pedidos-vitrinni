import java.math.BigDecimal;

import com.ecommerce.pedidos.vitrinni.modelo.*;

public class App {
    public static void main(String[] args) {

        System.out.println("=== TESTE DAS VALIDAÇÕES ===");

        // =========================
        // VALORES VÁLIDOS
        // =========================

        System.out.println("\n--- Valores válidos ---");

        Produto produto = new Produto(
            "001",
            "Notebook",
            "Notebook para estudos",
            new BigDecimal("3500.00"),
            10
        );

        System.out.println("Produto criado com preço válido: " + produto);

        produto.setPreco(new BigDecimal("4000.00"));
        System.out.println("Preço válido alterado com sucesso: " + produto.getPreco());

        produto.setQuantidade(20);
        System.out.println("Quantidade válida alterada com sucesso.");

        produto.baixarEstoque(5);
        System.out.println("Baixa de estoque válida realizada com sucesso.");

        // =========================
        // VALORES INVÁLIDOS
        // =========================

        System.out.println("\n--- Valores inválidos ---");

        try {
            produto.setPreco(new BigDecimal("0"));
        } catch (IllegalArgumentException e) {
            System.out.println("Preço inválido recusado: " + e.getMessage());
        }

        try {
            produto.setPreco(new BigDecimal("-100"));
        } catch (IllegalArgumentException e) {
            System.out.println("Preço negativo recusado: " + e.getMessage());
        }

        try {
            produto.setQuantidade(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Quantidade inválida recusada: " + e.getMessage());
        }

        try {
            produto.setQuantidade(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Quantidade negativa recusada: " + e.getMessage());
        }

        try {
            produto.baixarEstoque(100);
        } catch (IllegalArgumentException e) {
            System.out.println("Baixa maior que o estoque recusada: " + e.getMessage());
        }

        try {
            produto.baixarEstoque(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Baixa de quantidade zero recusada: " + e.getMessage());
        }

        // =========================
        // OUTRAS CLASSES
        // =========================

        Cliente cliente = new Cliente(
            "Marcos",
            "12345678900",
            "marcos@email.com",
            "16999999999",
            "Ibaté - SP"
        );

        Pedido pedido = new Pedido();
        pedido.setNumero(1);
        pedido.setCliente(cliente);
        pedido.setData(20261002);
        pedido.setSituacao("ABERTO");

        System.out.println("\n--- Objetos criados ---");
        System.out.println("Produto: " + produto);
        System.out.println("Cliente: " + cliente);
        System.out.println("Pedido: " + pedido);
    }
}