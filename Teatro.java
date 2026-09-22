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
            System.out.println("\n=== Teatro Mack ===");
            System.out.println("1. Carregar espetáculos");
            System.out.println("2. Exibir espetáculos");
            System.out.println("3. Fazer reserva");
            System.out.println("4. Consultar mapa de assentos");
            System.out.println("5. Consultar reserva");
            System.out.println("6. Estatísticas");
            System.out.println("7. Sair");
            System.out.println("========================================\n");
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
                    System.out.println("Grupo: Guilherme Pinho - Moabe Guedes\nSistema Finalizado");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.\n");
            }

        } while (selecaoMenu != 7);
    }


    public void exibirEspetaculos() throws Exception {
        for (int i = 0; i < espetaculos.size(); i++) {
            System.out.println(espetaculos.get(i).toString());
        }
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

    public Reserva solicitarReserva(Espetaculo espetaculo) throws Exception { 
        String cpf, nome;
        int qtdeIngressos;
        do {
            System.out.println("Digite o CPF: ");
            cpf = entrada.nextLine();
            if (cpf.length() != 11) {
                System.out.println("CPF inválido. O CPF deve conter 11 dígitos.");
            }
            
        } while (cpf.length() != 11);

        do {
            System.out.println("Digite o nome: ");
            nome = entrada.nextLine();
            if (nome.length() < 3) {
                System.out.println("Nome inválido. O nome deve conter pelo menos 3 caracteres.");
            }
        } while (nome.length() < 3);

        do {
            System.out.println("Digite a quantidade de ingressos (máximo 4): ");
            qtdeIngressos = entrada.nextInt();
            entrada.nextLine();
            if (qtdeIngressos > 4) {
                System.out.println("Quantidade de ingressos inválida. O máximo permitido é 4.");
            }
            if (qtdeIngressos < 1) {
                System.out.println("Quantidade de ingressos inválida. O mínimo permitido é 1.");
            }
        } while (qtdeIngressos > 4 || qtdeIngressos < 1);

        exibirAssentos(espetaculo);

        //verificar se os assentos estão disponíveis e marcar como ocupados fazer dps

        System.out.println("Digite os assentos desejados, um de cada vez (ex: A1, B2, C3): ");
        String[] assentos = new String[qtdeIngressos];
        char[][] mapaAssentos = espetaculo.getAssentos();
        char[] letras = {'A', 'B', 'C', 'D', 'E'};

        for (int i = 0; i < qtdeIngressos; i++) {
            
            do {
                assentos[i] = entrada.nextLine().toUpperCase();

            if (assentos[i].length() != 2
                || assentos[i].charAt(0) < 'A'
                || assentos[i].charAt(0) > 'E'
                || assentos[i].charAt(1) < '1'
                || assentos[i].charAt(1) > '8') {

                System.out.println("Assento inválido. Digite novamente (ex: A1, B2, C3): ");
            }

            } while (assentos[i].length() != 2
                    || assentos[i].charAt(0) < 'A'
                    || assentos[i].charAt(0) > 'E'
                    || assentos[i].charAt(1) < '1'
                    || assentos[i].charAt(1) > '8');

            for (int j = 0; j < letras.length; j++) {
                for (int k = 0; k < 8; k++) {
                    if (assentos[i].equals(letras[j] + Integer.toString(k + 1))) {
                        if (mapaAssentos[j][k] == 'X') {
                            System.out.println("Assento " + assentos[i] + " já está ocupado. Escolha outro assento.");
                            if (i > 0) {
                                i--;
                            }
                        } else {
                            mapaAssentos[j][k] = 'X';
                            System.out.println("Assento " + assentos[i] + " reservado com sucesso.");
                        }
                    }
                    
                }
            }
        }

        System.out.println("\nReserva realizada com sucesso!\n");
        System.out.println("Resumo da reserva:");
        System.out.println("Espetáculo: " + espetaculo.getNome());
        System.out.println("Assentos reservados: ");
        for (int i = 0; i < qtdeIngressos; i++) {
            System.out.print(assentos[i] + " ");
        }
        System.out.println("\nValor total: R$" + (espetaculo.getPreco() * qtdeIngressos));

        Reserva reserva = new Reserva(cpf, nome, espetaculo, qtdeIngressos, assentos);
        reservas.add(reservas.size(), reserva);

        return reserva;
    }


    public int buscarPosicaoEspetaculo(int codigo) throws Exception {
        for (int i = 0; i < espetaculos.size(); i++) {
            Espetaculo s = espetaculos.get(i);
            if (codigo == s.getCodigo()) {
                return i;
            }
        }
        return -1;
    }



    public void fazerReserva() throws Exception{
        System.out.println("Digite o código do espetáculo que deseja reservar: ");
        while (!entrada.hasNextInt()) {
            System.out.println("Entrada inválida. Digite apenas números.");
            entrada.nextLine();
        }

        int codigo = entrada.nextInt();
        
        entrada.nextLine();
        boolean existe = buscarEspetaculos(codigo);
        int posicao = buscarPosicaoEspetaculo(codigo);

        if (existe) {
            System.out.println("Espetáculo encontrado:");
            System.out.println(espetaculos.get(posicao).toString());
            solicitarReserva(espetaculos.get(posicao));

        } else {
            System.out.println("Espetáculo não encontrado.");
        }
    }

        public void exibirAssentos(Espetaculo espetaculo) {
        char[][] a = espetaculo.getAssentos();
        char[] letras = {'A', 'B', 'C', 'D', 'E'};

        System.out.println("   1 2 3 4 5 6 7 8");

        for (int i = 0; i < 5; i++) {
            System.out.print(letras[i] + "  ");
            for (int j = 0; j < 8; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    
    public void consultarMapaAssentos() throws Exception {
        System.out.println("Digite o código do espetáculo que deseja consultar o mapa de assentos: ");
        while (!entrada.hasNextInt()) {
            System.out.println("Entrada inválida. Digite apenas números.");
            entrada.nextLine();
        }
        int codigo = entrada.nextInt();
        entrada.nextLine();
        boolean existe = buscarEspetaculos(codigo);
        int posicao = buscarPosicaoEspetaculo(codigo);

        if (existe) {
            exibirAssentos(espetaculos.get(posicao));
        } else {
            System.out.println("Espetáculo não encontrado.");
        }
    }

    //getters e setters
        public Vetor<Espetaculo> getEspetaculos() {
        return espetaculos;
    }

    public Vetor<Reserva> getReservas() {
        return reservas;
    }


    
    public void consultarReserva() throws Exception {
        System.out.println("Digite o CPF da reserva que deseja consultar: ");
        String cpf = entrada.nextLine();
        boolean reservaEncontrada = false;
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reservaAtiva = reservas.get(i);
            if (reservaAtiva.getCPF().equals(cpf)) {
                System.out.println("\nReserva encontrada:");
                System.out.println("Cliente: " + reservaAtiva.getNome());
                System.out.println("Espetáculo: " + reservaAtiva.getEspetaculo());
                System.out.println("Assentos reservados: " + reservaAtiva.assentosToString());
                System.out.println("Valor total: R$" + (reservaAtiva.getEspetaculo().getPreco() * reservaAtiva.getQtdeIngressos()));
                reservaEncontrada = true;

            }
        }
        if (!reservaEncontrada) {
            System.out.println("Reserva não encontrada para o CPF informado.");
        }
    }

    public void estatisticas() throws Exception {

        if (espetaculos == null || espetaculos.size() == 0) {
            System.out.println("Nenhum espetáculo carregado para calcular estatísticas.");
            return;
        }


        System.out.println("Estatísticas:");
        mediaIngressosPorReserva();
        maiorFaturamento();


    }

    public void mediaIngressosPorReserva() throws Exception {
        System.out.println("Média de ingressos vendidos por reserva:");
        for (int i = 0; i < espetaculos.size(); i++) {
            Espetaculo espetaculo = espetaculos.get(i);
            double mediaIngressos = calcularMediaIngressos(espetaculo.getCodigo());
            System.out.println(espetaculo.getNome() + ": " + mediaIngressos);
        }
    }

    public double calcularMediaIngressos(int codigo) throws Exception {
        if (reservas.isEmpty()) {
            return 0.0;
        }
        double totalIngressos = 0;
        double contReservas = 0;
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reservaAtiva = reservas.get(i);
            if (reservaAtiva.getEspetaculo().getCodigo() == codigo) {
                totalIngressos += reservaAtiva.getQtdeIngressos();
                contReservas++;
            }
        }

        if (contReservas == 0) {
            return 0.0;
        }
        return totalIngressos / contReservas;
    }

    public double maiorFaturamento() throws Exception {
        double maiorFaturamento = 0;
        Espetaculo espetaculoMaiorFaturamento = null;

        for (int i = 0; i < espetaculos.size(); i++) {
            Espetaculo espetaculoAtual = espetaculos.get(i);
            double faturamentoAtual = calcularFaturamento(espetaculoAtual);

            if (faturamentoAtual > maiorFaturamento) {
                maiorFaturamento = faturamentoAtual;
                espetaculoMaiorFaturamento = espetaculoAtual;
            }
        }

        if (espetaculoMaiorFaturamento != null) {
            System.out.println("\nEspetáculo com maior faturamento: " + espetaculoMaiorFaturamento.getNome() + " - Faturamento: R$" + maiorFaturamento);
        } else {
            System.out.println("Nenhum espetáculo cadastrado para calcular o faturamento.");
        }

        return maiorFaturamento;
    }

    public double calcularFaturamento(Espetaculo espetaculo) throws Exception {
        double faturamento = 0;
        char[][] assentos = espetaculo.getAssentos();

        for (int i = 0; i < assentos.length; i++) {
            for (int j = 0; j < assentos[i].length; j++) {
                if (assentos[i][j] == 'X') {
                    faturamento += espetaculo.getPreco();
                }
            }
        }

        return faturamento;
    }
}