import java.io.FileNotFoundException;
import java.util.Comparator;

public class EscalonadorSJF extends Escalonador{
    public EscalonadorSJF(String arquivo) throws FileNotFoundException {
        super(arquivo);
    }
    @Override
    public void ordena() {//Ordenando baseado com o tempo de duração dos processos.
        super.processosEspera.sort(Comparator.comparing(Processo::getDuracao));
    }
}
