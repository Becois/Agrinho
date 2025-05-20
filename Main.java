import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CalculadoraImpacto calculadora = new CalculadoraImpacto();

        System.out.println("Produtos disponíveis:");
        for (ProdutoAgricola p : calculadora.getProdutosDisponiveis()) {
            System.out.println(" - " + p.getNome());
        }

        // Simula a seleção do usuário
        List<String> produtosDesejados = Arrays.asList("Tomate", "Leite", "Arroz"); // "Arroz" não existe nos dados simulados
        System.out.println("\nCalculando impacto para: " + produtosDesejados);

        CalculadoraImpacto.SumarioImpacto sumario = calculadora.calcularImpactoTotal(produtosDesejados);
        System.out.println(sumario);

        // Outro exemplo
        List<String> todosProdutos = Arrays.asList("Tomate", "Alface", "Leite", "Batata");
        System.out.println("\nCalculando impacto para TODOS os produtos disponíveis:");
        CalculadoraImpacto.SumarioImpacto sumarioTodos = calculadora.calcularImpactoTotal(todosProdutos);
        System.out.println(sumarioTodos);
    }
}