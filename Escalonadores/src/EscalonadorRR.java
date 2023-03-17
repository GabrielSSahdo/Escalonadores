import java.io.FileNotFoundException;
import java.util.Comparator;

public class EscalonadorRR extends Escalonador implements Preempcao {
    public int quantum;
    public EscalonadorRR(String arquivo) throws FileNotFoundException {
        super(arquivo);
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    @Override
    public void atualizaTempoDeDuracao(Processo processo) {//Atualiza o tempo de duração quando quantum for menor que o tempo de duração.
        processo.setDuracao(processo.getDuracao() - quantum);
    }
    @Override
    public boolean verificaQuantum(Processo processo) {//Verifica se o quantum é menor ou igual que a duração.
        return processo.getDuracao() <= quantum;
    }
    @Override
    public void atualizaTempo(Processo processo) {//Atualiza o tempo de máquina.
        if(verificaQuantum(processo)){
            super.atualizaTempo(processo);
        }else{
            tempo += quantum;
        }
    }
    @Override
    public void ordena() {//Ordenação em cada unidade de tempo para tempo de entrada.
        super.processosEspera.sort(Comparator.comparing(Processo::getTempoDeEntrada));
    }

    @Override
    public double calculaTempoDeEspera() {
        return super.calculaTempoDeEspera();
    }

    @Override
    public double calculaTempoDeExecucao() {
        return super.calculaTempoDeExecucao();
    }

    @Override
    public void executa() {//Override do executa baseado no quantum.
        tempo = processosEspera.get(0).getTempoDeEntrada();

        while (processosEspera.size() != 0 ) {
            if(verificaQuantum(processosEspera.get(0))){
                super.executa();
            } else {
                Processo processoDemorado = processosEspera.get(0);
                atualizaTempoDeDuracao(processoDemorado);
                atualizaTempo(processoDemorado);
                processoDemorado.setTempoDeEntrada(tempo);
                processosEspera.remove(processoDemorado);
                processosEspera.add(processoDemorado);
            }
        }
    }
}
