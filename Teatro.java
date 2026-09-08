public class Teatro {
    private Vetor <Espetaculo> espetaculos;
    private Vetor <Reserva> reservas;

    public Teatro(Vetor<Espetaculo> espetaculos, Vetor<Reserva> reservas) {
        this.espetaculos = espetaculos;
        this.reservas = reservas;
    }


    public Vetor<Espetaculo> getEspetaculos() {
        return espetaculos;
    }

    public Vetor<Reserva> getReservas() {
        return reservas;
    }


}