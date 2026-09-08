/*Guilherme Gomes Pinho - 10755529
Moabe Guedes - 10748053 */
import java.io.BufferedReader;
import java.io.FileReader;

public class Teatro {
    private Vetor <Espetaculo> espetaculos;
    private Vetor <Reserva> reservas;

    public Teatro(Vetor<Espetaculo> espetaculos, Vetor<Reserva> reservas) {
        espetaculos = new Vetor <Espetaculo>(20);
        reservas = new Vetor <Reserva>(800);
    }


    public void carregaTXT() throws Exception {
        FileReader arquivo = new FileReader("espetaculo.txt");
        BufferedReader linha = new BufferedReader(arquivo);
        String aux = linha.readLine();
        int cont = 0;
        while (linha != null) {
            String[] vet = aux.split(";");
            Espetaculo e1 = new Espetaculo(Integer.parseInt(vet[0]), vet[1], vet[2], vet[3], Double.parseDouble(vet[4]));
            espetaculos.add(cont, e1);
            cont++;
            aux = linha.readLine();
        }
        arquivo.close();
    }




    public Vetor<Espetaculo> getEspetaculos() {
        return espetaculos;
    }

    public Vetor<Reserva> getReservas() {
        return reservas;
    }


}