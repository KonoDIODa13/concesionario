package application.Domain;

public class Coche extends Vehiculo {
    public int plazas;
    public String tipo;

    public Coche(String matricula, String marca, String modelo, Double precio, String carga, int plazas, String tipo) {
        super(matricula, marca, modelo, precio, carga);
        this.plazas = plazas;
        this.tipo = tipo;
    }

    public int getPlazas() {
        return plazas;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Coche: Matricula = " + matricula;
    }
}
