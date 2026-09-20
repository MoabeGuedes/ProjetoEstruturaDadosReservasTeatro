/*Guilherme Gomes Pinho - 10755529
Moabe Guedes - 10748053 */

import java.util.Arrays;

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

    public String assentosToString() {
        return Arrays.toString(assentos);
    }

    public String toString() {
        return ("CPF: " + CPF + " - Nome: " + nome + "\nEspetáculo: " + espetaculo + "\nQuantidade de Ingressos: " + qtdeIngressos + " - Assentos: " + assentosToString());
    }
}