public class Processo {
	/**
	 * Declarações das propriedades dos processos.
	 */
    private final String pid;
    private int tempoDeEntrada, duracao;
    private final int prioridade, tipo;

    public Processo(String pid, int tempoDeEntrada, int duracao, int prioridade, int tipo) {
        this.pid = pid;
        this.tempoDeEntrada = tempoDeEntrada;
        this.duracao = duracao;
        this.prioridade = prioridade;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return pid + " " + tempoDeEntrada + " " +  duracao  + " " +  prioridade  + " " + tipo;
    }

    public String getPid() {
        return pid;
    }

    public int getTempoDeEntrada() {
        return tempoDeEntrada;
    }

    public void setTempoDeEntrada(int tempoDeEntrada) {
        this.tempoDeEntrada = tempoDeEntrada;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getPrioridade() {
        return prioridade;
    }
}
