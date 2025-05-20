public class ProdutoAgricola {
    private String nome;
    private double contribuicaoFamiliarPorcentagem; // Ex: 60.0 para 60%
    private double valorMedioPorKg; // Preço médio por Kg/Litro
    private double consumoMensalCidadeKg; // Consumo mensal da cidade em Kg/Litro

    public ProdutoAgricola(String nome, double contribuicaoFamiliarPorcentagem, double valorMedioPorKg, double consumoMensalCidadeKg) {
        this.nome = nome;
        this.contribuicaoFamiliarPorcentagem = contribuicaoFamiliarPorcentagem;
        this.valorMedioPorKg = valorMedioPorKg;
        this.consumoMensalCidadeKg = consumoMensalCidadeKg;
    }

    // Getters para acessar os atributos
    public String getNome() {
        return nome;
    }

    public double getContribuicaoFamiliarPorcentagem() {
        return contribuicaoFamiliarPorcentagem;
    }

    public double getValorMedioPorKg() {
        return valorMedioPorKg;
    }

    public double getConsumoMensalCidadeKg() {
        return consumoMensalCidadeKg;
    }

    // Opcional: toString para facilitar a impressão
    @Override
    public String toString() {
        return "ProdutoAgricola{" +
               "nome='" + nome + '\'' +
               ", contribuicaoFamiliarPorcentagem=" + contribuicaoFamiliarPorcentagem +
               ", valorMedioPorKg=" + valorMedioPorKg +
               ", consumoMensalCidadeKg=" + consumoMensalCidadeKg +
               '}';
    }
}