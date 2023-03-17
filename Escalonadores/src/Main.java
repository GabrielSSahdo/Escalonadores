import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
    	
    	//Scan do arquivo que precisa estar no src e ao colocar o nome do arquivo será scanneado.
        System.out.println("Forneça o pathFile do arquivo de processos: ");
        Scanner scan = new Scanner(System.in);
        String pathArquivo = scan.nextLine();
        Scanner arquivo = new Scanner(new File(pathArquivo));
        String leitorArquivo = arquivo.nextLine();
        String[] dados = leitorArquivo.split(" ");
        String tipo = (dados[dados.length-1]);

        switch (tipo){//Definição dos tipos de escalonadores usados nos processos.
            case "1":
                System.out.println("Você pode usar o Escalonador FCFS ou o Escalonador SJF");
                System.out.println("Digite 1 - FCFS ou 2 - SJF");
                break;
            case "2":
                System.out.println("Você pode usar o Escalonador SJF ou o Escalonador RR");
                System.out.println("Digite 2 - SJF ou 3 - RR");
                break;
            case "3":
                System.out.println("Você pode usar o Escalonador RR ou o Escalonador Priop");
                System.out.println("Digite 3 - RR ou 4 - Priop");
        }

        Scanner input = new Scanner(System.in);
        int tipoEscalonador = input.nextInt();


        if( tipoEscalonador == 3 || tipoEscalonador == 4){//Recepção do quantum em preempção.
            int quantumValor;
                System.out.println("Foi selecionado um escalonador com preempção, você deve informar o quantum em segundos: ");
                Scanner quantum = new Scanner(System.in);
                quantumValor = Integer.parseInt(quantum.nextLine());

            switch (tipoEscalonador){//Definição do escalonador em preempção.
                case 3:
                    System.out.println("Está sendo iniciado o Escalonador RR");

                    EscalonadorRR escalonadorRR = new EscalonadorRR(pathArquivo);
                    escalonadorRR.setQuantum(quantumValor);
                    escalonadorRR.inicia();
                    break;
                case 4:
                    System.out.println("Está sendo iniciado o Escalonador Priop");
                    EscalonadorPrio escalonadorPrio = new EscalonadorPrio(pathArquivo);
                    escalonadorPrio.setQuantum(quantumValor);
                    escalonadorPrio.inicia();
            }
        } else {
            switch (tipoEscalonador){// Prints para caso de não preempção.
                case 1:
                    System.out.println("Está sendo iniciado o Escalonador FCFS");
                    EscalonadorFCFS escalonadorFCFS = new EscalonadorFCFS(pathArquivo);
                    escalonadorFCFS.inicia();
                    break;
                case 2:
                    System.out.println("Está sendo iniciado o Escalonador SJF");
                    EscalonadorSJF escalonadorSJF = new EscalonadorSJF(pathArquivo);
                    escalonadorSJF.inicia();
                    break;

            }
        }
        scan.close();
        arquivo.close();
        input.close();

    }
}