public class Espetaculo{
    private int codigo;
    private String nome;
    private String data;
    private String horario;
    private double preco;
    private char[][] assentos;


    public Espetaculo(int codigo, String nome, String data, String horario, double preco){
        this.codigo = codigo;
        this.nome = nome;
        this.data = data;
        this.horario = horario;
        this.preco = preco;
        
    }



    //gets e sets

    public int getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public String getData() {
        return data;
    }
    public String getHorario() {
        return horario;
    }
    public double getPreco() {
        return preco;
    }
    public char[][] getAssentos() {
        return assentos;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }
    public void setPreco(double preco) {
        this.preco = preco; 
    }
    public void setAssentos(char[][] assentos) {
        this.assentos = assentos;
    }



}