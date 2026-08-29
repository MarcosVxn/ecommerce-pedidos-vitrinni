import com.ecommerce.pedidos.vitrinni.modelo.*;

public class App {
    public static void main(String[] args) {
        Produto p = new Produto();
        Produto r = new Produto("COD001",
        "MouseTek",
        "Mouse Gamer",
         300.50 ,
          50);

        System.out.println(p);

        

        r.baixarEstoque(15);

        System.out.println(r);
    }
}
