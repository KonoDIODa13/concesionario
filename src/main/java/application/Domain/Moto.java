package application.Domain;

public class Moto extends Vehiculo {
    public int plazas;
    public String tipo;

    public Moto(String matricula, String marca, String modelo, Double precio, String carga, int plazas, String tipo) {
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
        return "Moto: Matricula = " + matricula;
    }
}
