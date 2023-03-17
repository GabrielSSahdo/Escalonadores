import java.io.FileNotFoundException;
import java.util.Comparator;

public class EscalonadorFCFS extends Escalonador {


    public EscalonadorFCFS(String arquivo) throws FileNotFoundException {
        super(arquivo);
    }

    @Override
    public void ordena() {// Ordenação baseada com o tempo de entrada na fila dos processos.
        super.processosEspera.sort(Comparator.comparing(Processo::getTempoDeEntrada));
    }
}
