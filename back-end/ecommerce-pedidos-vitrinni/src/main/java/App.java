

import java.math.BigDecimal;

import com.ecommerce.pedidos.vitrinni.modelo.*;

public class App {
    public static void main(String[] args) {

        // Criar produto
        Produto produto = new Produto(
            "001",
            "Notebook",
            "Notebook para estudos",
            new BigDecimal("3500.00"),
            10
        );

        // Criar cliente
        Cliente cliente = new Cliente(
            "Marcos",
            "12345678900",
            "marcos@email.com",
            "16999999999",
            "Ibaté - SP"
        );

        // Criar pedido
        Pedido pedido = new Pedido();
        pedido.setNumero(1);
        pedido.setCliente(cliente);
        pedido.setData(20261002);
        pedido.setSituacao("ABERTO");

        // Feature:
        // Criar ItemPedido quando a classe estiver implementada.

        // Feature:
        // Adicionar os itens ao pedido.

        // Feature:
        // Calcular o valor total quando essa funcionalidade estiver disponível.

        System.out.println("Produto: " + produto);
        System.out.println("Cliente: " + cliente);
        System.out.println("Pedido: " + pedido);
    }
}
