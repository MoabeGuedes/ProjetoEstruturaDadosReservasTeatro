/*Guilherme Gomes Pinho - 10755529
Moabe Guedes - 10748053 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Teatro {
    private Vetor <Espetaculo> espetaculos;
    private Vetor <Reserva> reservas;
    Scanner entrada = new Scanner(System.in);
    Boolean espetaculoCarregado = false;

    public Teatro() {
        this.espetaculos = new Vetor <Espetaculo>(20);
        this.reservas = new Vetor <Reserva>(800);
    }


    public void carregaTXT() throws Exception {
        FileReader arquivo = new FileReader("dadosEspetaculos.txt");
        BufferedReader linha = new BufferedReader(arquivo);
        String aux = linha.readLine();
        int cont = 0;
        while (aux != null) {
            String[] vet = aux.split(";");
            Espetaculo e1 = new Espetaculo(Integer.parseInt(vet[0]), vet[1], vet[2], vet[3], Double.parseDouble(vet[4]));
            espetaculos.add(cont, e1);
            cont++;
            aux = linha.readLine();
        }
        espetaculoCarregado = true;
        arquivo.close();
    }

    public void exibirMenu() throws Exception {
        int selecaoMenu;

        do {
            System.out.println("=== Teatro Mack ===");
            System.out.println("1. Carregar espetáculos");
            System.out.println("2. Exibir espetáculos");
            System.out.println("3. Fazer reserva");
            System.out.println("4. Consultar mapa de assentos");
            System.out.println("5. Consultar reserva");
            System.out.println("6. Estatísticas");
            System.out.println("7. Sair");
            System.out.println("========================================");
            System.out.print("Selecione uma opção: ");

            while (entrada.hasNextInt() == false) {
                System.out.println("Opção inválida. Digite um número de 1 a 7.");
                entrada.next();
            }
            selecaoMenu = entrada.nextInt();
            entrada.nextLine();

            switch (selecaoMenu) {

                case 1:
                    carregaTXT();
                    System.out.println("Espetáculos Carregados");
                    break;

                case 2:
                    if (espetaculoCarregado == false) {
                        System.out.println("É necessário carregar os espetáculos antes de acessar essa opção.");
                        break;
                    }
                    exibirEspetaculos();
                    break;

                case 3:
                    if (espetaculoCarregado == false) {
                        System.out.println("É necessário carregar os espetáculos antes de acessar essa opção.");
                        break;
                    }
                    fazerReserva();
                    break;

                case 4:
                    if (espetaculoCarregado == false) {
                        System.out.println("É necessário carregar os espetáculos antes de acessar essa opção.");
                        break;
                    }
                    consultarMapaAssentos();
                    break;

                case 5:
                    if (espetaculoCarregado == false) {
                        System.out.println("É necessário carregar os espetáculos antes de acessar essa opção.");
                        break;
                    }
                    consultarReserva();
                    break;

                case 6:
                    if (espetaculoCarregado == false) {
                        System.out.println("É necessário carregar os espetáculos antes de acessar essa opção.");
                        break;
                    }
                    estatisticas();
                    break;

                case 7:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.\n");
            }

        } while (selecaoMenu != 3);
    }

    public boolean buscarEspetaculos(int codigo) throws Exception {
        for (int i = 0; i < espetaculos.size(); i++) {
            Espetaculo s = espetaculos.get(i);
            if (codigo == s.getCodigo()) {
                return true;
            }
        }
        return false;
    }

    public void exibirEspetaculos() throws Exception {
        for (int i = 0; i < espetaculos.size(); i++) {
            System.out.println(espetaculos.get(i).toString());
    }
}

    public void solicitarReserva() throws Exception { 
        String CPF, nome;
        int qtdeIngressos;
    }






    public void fazerReserva() throws Exception{
        System.out.println("Digite o código do espetáculo que deseja reservar: ");
        int codigo = entrada.nextInt();
        boolean existe = buscarEspetaculos(codigo);
        if (existe) {
            System.out.println("Espetáculo encontrado. Realizando reserva...");
            
        } else {
            System.out.println("Espetáculo não encontrado.");
        }
    }

    public Vetor<Espetaculo> getEspetaculos() {
        return espetaculos;
    }

    public Vetor<Reserva> getReservas() {
        return reservas;
    }

    //implementar futuramente (pro menu nao dar erro por enquanto)
    public void consultarMapaAssentos() {

    }

    public void consultarReserva() {
        
    }

    public void estatisticas() {

    }

}