package application.Domain;

public class Moto extends Vehiculo {
    public int plazas;
    public TipoMoto tipo;

    public Moto(String matricula, String marca, String modelo, Double precio, String carga, int plazas, TipoMoto tipo) {
        super(matricula, marca, modelo, precio, carga);
        this.plazas = plazas;
        this.tipo = tipo;
    }
}
