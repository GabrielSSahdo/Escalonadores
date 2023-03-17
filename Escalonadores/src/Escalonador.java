import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public abstract class Escalonador{

    double tempoDeEspera = 0;
    double tempoDeEntrada = 0;
    double tempoDeDuracao = 0;
    int tempo = 0;
    List<Processo> processosPreparacao = new ArrayList<>();
    List<Processo> processosEspera = new ArrayList<>();
    List<Processo> processosConcluidos = new ArrayList<>();
    public Escalonador(String arquivo) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(arquivo));

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String[] dados = linha.split(" ");

            Processo processo = new Processo(dados[0], Integer.parseInt(dados[1]), Integer.parseInt(dados[2]), Integer.parseInt(dados[3]), Integer.parseInt(dados[4]));
            processosPreparacao.add(processo);
        }
        scanner.close();
    }
    public void verificaTempoDeEntrada(){//Verificação de tempo de entrada por unidade de tempo para ordenação.
        processosEspera.addAll(processosPreparacao);
        processosEspera.sort(Comparator.comparing(Processo::getTempoDeEntrada));
    }
    public void executa(){//Executa a partir da entrada do primeiro processo no escalonador.

        while(processosEspera.size()!=0){
            Processo processo = processosEspera.get(0);
            atualizaTempo(processo);
            processosConcluidos.add(processo);
            processosEspera.remove(processo);
        }
    }
    public void mostraProcessosConcluidos(){//Mostra ordenação final dos executados.

        StringBuilder string = new StringBuilder();

        for (Processo processo: processosConcluidos) {
            string.append(processo.getPid()).append(", ");

        }
        string = new StringBuilder(string.substring(0, string.length() - 2) + ".");
        System.out.println(string);
    }
    public double calculaTempoDeExecucao(){
        double tempoDeEntrada = 0;

        for (Processo processo: processosEspera) {
            tempoDeDuracao += processo.getDuracao();
            tempoDeEntrada += processo.getTempoDeEntrada();
        }

        return tempoDeDuracao + tempoDeEspera - tempoDeEntrada;
    }

    public double calculaTempoDeEspera(){

        int i = 0;
        while (i < processosEspera.size()){
            tempoDeEspera += processosEspera.get(i).getDuracao()*(processosEspera.size()-(i+1));
            tempoDeEntrada += processosEspera.get(i).getTempoDeEntrada();
            i++;
        }
        return tempoDeEspera;
    }
    public void atualizaTempo(Processo processo){

        tempo += processo.getDuracao();
    }
    public abstract void ordena();
    public String getTempoMedioExecucao(){
        return "Tempo médio de Execução: " + Math.round((calculaTempoDeExecucao()/processosConcluidos.size())*100.0)/100.0;
    }
    public String getTempoMedioDeEspera() {
        return "Tempo médio de Espera: " + Math.round(((calculaTempoDeEspera()-tempoDeEntrada)/processosConcluidos.size())*100.0)/100.0;
    }
    public void inicia(){

        verificaTempoDeEntrada();
        ordena();
        calculaTempoDeEspera();
        calculaTempoDeExecucao();
        executa();
        mostraProcessosConcluidos();
        System.out.println(getTempoMedioExecucao());
        System.out.println(getTempoMedioDeEspera());
    }

}
