import java.io.FileNotFoundException;
import java.util.Comparator;

public class EscalonadorPrio extends Escalonador implements Preempcao{

    public int quantum;

    public EscalonadorPrio(String arquivo) throws FileNotFoundException {
        super(arquivo);
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    @Override
    public void ordena() {
        super.processosEspera.sort(Comparator.comparing(Processo::getPrioridade));
    }
    @Override
    public void executa() {
        while (processosEspera.size() != 0 ) {
            if(processosEspera.get(0).getDuracao() <= quantum){
                super.executa();
            } else {
                Processo processoDemorado = processosEspera.get(0);
                atualizaTempoDeDuracao(processoDemorado);
                atualizaTempo(processoDemorado);
                processosEspera.remove(processoDemorado);
                processosEspera.add(processoDemorado);
            }
        }
    }

    @Override
    public void atualizaTempoDeDuracao(Processo processo) {
        processo.setDuracao(processo.getDuracao() - quantum);
    }

    @Override
    public boolean verificaQuantum(Processo processo) {
        return processo.getDuracao() <= quantum;
    }

    @Override
    public double calculaTempoDeExecucao() {
        return super.calculaTempoDeExecucao();
    }

    @Override
    public double calculaTempoDeEspera() {
        return super.calculaTempoDeEspera();
    }

    public void atualizaTempo(Processo processo) {
        if (verificaQuantum(processo)) {
            super.atualizaTempo(processo);
        } else {
            tempo += quantum;
        }
    }
}
