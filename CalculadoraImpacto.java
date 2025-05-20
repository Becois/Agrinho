import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Calculadora de Impacto da Agricultura Familiar
 *
 * Esta classe calcula o impacto econômico da agricultura familiar com base nos
 * produtos agrícolas fornecidos e sua contribuição para o abastecimento da cidade.
 */
public class CalculadoraImpacto {

    private List<ProdutoAgricola> produtosDisponiveis;
    private NumberFormat currencyFormatter;

    /**
     * Construtor da classe CalculadoraImpacto.
     * Inicializa a lista de produtos disponíveis e o formatador de moeda.
     */
    public CalculadoraImpacto() {
        this.produtosDisponiveis = new ArrayList<>();
        this.currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        // Inicializa com dados simulados
        carregarDadosSimulados();
    }

    /**
     * Método para carregar os dados dos produtos agrícolas simulados.
     * Este método adiciona produtos à lista de produtos disponíveis.
     */
    private void carregarDadosSimulados() {
        produtosDisponiveis.add(new ProdutoAgricola("Tomate", 60.0, 5.50, 10000.0));
        produtosDisponiveis.add(new ProdutoAgricola("Alface", 80.0, 3.00, 5000.0));
        produtosDisponiveis.add(new ProdutoAgricola("Leite", 45.0, 4.20, 20000.0));
        produtosDisponiveis.add(new ProdutoAgricola("Batata", 70.0, 4.00, 8000.0));
    }

    /**
     * Método para obter a lista de produtos agrícolas disponíveis.
     *
     * @return A lista de produtos agrícolas disponíveis.
     */
    public List<ProdutoAgricola> getProdutosDisponiveis() {
        return produtosDisponiveis;
    }

    /**
     * Calcula o impacto de um produto agrícola específico.
     *
     * @param produto O objeto ProdutoAgricola para calcular o impacto.
     * @return Um objeto ImpactoProduto contendo os resultados do cálculo.
     */
    public ImpactoProduto calcularImpactoProduto(ProdutoAgricola produto) {
        double quantidadeFamiliar = (produto.getContribuicaoFamiliarPorcentagem() / 100.0) * produto.getConsumoMensalCidadeKg();
        double valorEconomicoFamiliar = quantidadeFamiliar * produto.getValorMedioPorKg();

        return new ImpactoProduto(
                produto.getNome(),
                produto.getContribuicaoFamiliarPorcentagem(),
                quantidadeFamiliar,
                valorEconomicoFamiliar
        );
    }

    /**
     * Calcula o impacto total para uma lista de produtos selecionados.
     *
     * @param produtosSelecionados Lista de nomes de produtos a serem considerados.
     * @return Um objeto SumarioImpacto contendo os totais e detalhes do impacto.
     */
    public SumarioImpacto calcularImpactoTotal(List<String> produtosSelecionados) {
        List<ImpactoProduto> impactosDetalhados = new ArrayList<>();
        double totalEconomiaFamiliar = 0.0;

        for (String nomeProduto : produtosSelecionados) {
            ProdutoAgricola produto = produtosDisponiveis.stream()
                    .filter(p -> p.getNome().equalsIgnoreCase(nomeProduto))
                    .findFirst()
                    .orElse(null);

            if (produto != null) {
                ImpactoProduto impacto = calcularImpactoProduto(produto);
                impactosDetalhados.add(impacto);
                totalEconomiaFamiliar += impacto.getValorEconomicoGerado();
            } else {
                System.out.println("Produto '" + nomeProduto + "' não encontrado.");
                // Aqui, você poderia lançar uma exceção ou tratar de outra forma
            }
        }
        return new SumarioImpacto(impactosDetalhados, totalEconomiaFamiliar);
    }

    /**
     * Classe interna para representar o impacto de um único produto agrícola.
     */
    public static class ImpactoProduto {
        private String nomeProduto;
        private double contribuicaoPorcentagem;
        private double quantidadeFornecida;
        private double valorEconomicoGerado;

        public ImpactoProduto(String nomeProduto, double contribuicaoPorcentagem, double quantidadeFornecida, double valorEconomicoGerado) {
            this.nomeProduto = nomeProduto;
            this.contribuicaoPorcentagem = contribuicaoPorcentagem;
            this.quantidadeFornecida = quantidadeFornecida;
            this.valorEconomicoGerado = valorEconomicoGerado;
        }

        // Getters
        public String getNomeProduto() {
            return nomeProduto;
        }

        public double getContribuicaoPorcentagem() {
            return contribuicaoPorcentagem;
        }

        public double getQuantidadeFornecida() {
            return quantidadeFornecida;
        }

        public double getValorEconomicoGerado() {
            return valorEconomicoGerado;
        }
