import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;

public class CalculadoraImpacto {

    private List<ProdutoAgricola> produtosDisponiveis;

    public CalculadoraImpacto() {
        this.produtosDisponiveis = new ArrayList<>();
        // Inicializa com dados simulados
        carregarDadosSimulados();
    }

    private void carregarDadosSimulados() {
        produtosDisponiveis.add(new ProdutoAgricola("Tomate", 60.0, 5.50, 10000.0));
        produtosDisponiveis.add(new ProdutoAgricola("Alface", 80.0, 3.00, 5000.0));
        produtosDisponiveis.add(new ProdutoAgricola("Leite", 45.0, 4.20, 20000.0));
        produtosDisponiveis.add(new ProdutoAgricola("Batata", 70.0, 4.00, 8000.0));
    }

    public List<ProdutoAgricola> getProdutosDisponiveis() {
        return produtosDisponiveis;
    }

    /**
     * Calcula o impacto de um produto agrícola específico.
     * @param produto O objeto ProdutoAgricola para calcular o impacto.
     * @return Um objeto ImpactoProduto contendo os resultados.
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
     * @param produtosSelecionados Lista de nomes de produtos a serem considerados.
     * @return Um objeto SumarioImpacto contendo os totais e detalhes.
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
            }
        }
        return new SumarioImpacto(impactosDetalhados, totalEconomiaFamiliar);
    }

    // Classe interna para representar o impacto de um único produto
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
        public String getNomeProduto() { return nomeProduto; }
        public double getContribuicaoPorcentagem() { return contribuicaoPorcentagem; }
        public double getQuantidadeFornecida() { return quantidadeFornecida; }
        public double getValorEconomicoGerado() { return valorEconomicoGerado; }

        @Override
        public String toString() {
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            String unidade = nomeProduto.equalsIgnoreCase("Leite") ? "Litros" : "Kg";
            return String.format(
                "  - %s:\n" +
                "    Contribuição AF: %.2f%%\n" +
                "    Qtd. Fornecida AF (mensal): %.2f %s\n" +
                "    Valor Econômico Gerado (mensal): %s",
                nomeProduto, contribuicaoPorcentagem, quantidadeFornecida, unidade,
                currencyFormatter.format(valorEconomicoGerado)
            );
        }
    }

    // Classe interna para representar o resumo do impacto total
    public static class SumarioImpacto {
        private List<ImpactoProduto> detalhes;
        private double totalEconomia;

        public SumarioImpacto(List<ImpactoProduto> detalhes, double totalEconomia) {
            this.detalhes = detalhes;
            this.totalEconomia = totalEconomia;
        }

        public List<ImpactoProduto> getDetalhes() { return detalhes; }
        public double getTotalEconomia() { return totalEconomia; }

        @Override
        public String toString() {
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            StringBuilder sb = new StringBuilder();
            sb.append("\n=== Sumário de Impacto ===\n");
            for (ImpactoProduto ip : detalhes) {
                sb.append(ip.toString()).append("\n");
            }
            sb.append(String.format("\nTotal Econômico Gerado pela Agricultura Familiar (produtos selecionados): %s\n",
                                    currencyFormatter.format(totalEconomia)));
            return sb.toString();
        }
    }
}