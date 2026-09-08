public class Teatro {
    private Vetor <Espetaculo> espetaculos;
    private Vetor <Reservas> reservas;

    public Teatro(Vetor<Espetaculo> espetaculos, Vetor<Reservas> reservas) {
        this.espetaculos = espetaculos;
        this.reservas = reservas;
    }


    public <Espetaculo> getEspetaculos() {
        return espetaculos;
    }

    public <Reservas> getReservas() {
        return reservas;
    }
    

}