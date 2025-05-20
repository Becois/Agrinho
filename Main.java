/**
 * Classe principal para interação com o usuário via terminal.
 * Esta classe contém o método main, que é o ponto de entrada da aplicação.
 */
public class Main {
    /**
     * Método principal da aplicação.
     *
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        CalculadoraImpacto calculadora = new CalculadoraImpacto();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo à Calculadora de Impacto da Agricultura Familiar!");
        // [Image of Mercado de Produtos Agrícolas]

        // Exibe os produtos disponíveis
        System.out.println("\nProdutos disponíveis:");
        List<ProdutoAgricola> produtosDisponiveis = calculadora.getProdutosDisponiveis();
        for (int i = 0; i < produtosDisponiveis.size(); i++) {
            System.out.println((i + 1) + ". " + produtosDisponiveis.get(i).getNome());
        }

        // Obtém a seleção do usuário
        List<String> produtosSelecionados = new ArrayList<>();
        System.out.println("\nSelecione os produtos pelo número (separados por vírgula, ex: 1,3,4):");
        String input = scanner.nextLine();
        String[] indices = input.split(",");

        for (String indiceStr : indices) {
            try {
                int indice = Integer.parseInt(indiceStr.trim()) - 1; // Ajusta para o índice base 0
                if (indice >= 0 && indice < produtosDisponiveis.size()) {
                    produtosSelecionados.add(produtosDisponiveis.get(indice).getNome());
                } else {
                    System.out.println("Número de produto inválido: " + (indice + 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida: " + indiceStr);
            }
        }

        // Calcula e exibe o impacto
        if (produtosSelecionados.isEmpty()) {
            System.out.println("Nenhum produto selecionado. Saindo.");
            return;
        }

        System.out.println("\nCalculando impacto para: " + produtosSelecionados);
        CalculadoraImpacto.SumarioImpacto sumario = calculadora.calcularImpactoTotal(produtosSelecionados);
        System.out.println(sumario);

        scanner.close();
    }
}