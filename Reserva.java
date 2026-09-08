public class Reserva {
    private String CPF;
    private String nome;
    private Espetaculo espetaculo;
    private int qtdeIngressos;
    private String[] assentos;

    public Reserva(String CPF, String nome, Espetaculo espetaculo, int qtdeIngressos, String[] assentos) {
        this.CPF = CPF;
        this.nome = nome;
        this.espetaculo = espetaculo;
        this.qtdeIngressos = qtdeIngressos;
        this.assentos = assentos;
    }


    public String toString() {
        return ("CPF: " + CPF + " - Nome: " + nome + " - Espetáculo:" + espetaculo + " - Quantidade de Ingressos: " + qtdeIngressos + " - Assentos: " + assentos);
    }
}