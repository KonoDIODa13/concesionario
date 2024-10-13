package application.Domain;

import javafx.scene.control.SingleSelectionModel;
//calse coche que extiende de vehiculo
public class Coche extends Vehiculo {
    private int plazas;
    private String tipo;

    public Coche(String matricula, String marca, String modelo, double precio, String carga, int plazas, String tipo) {
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
        return "Coche: Matricula = " + getMatricula() + " Marca = " + getMarca() + " Modelo= " + getModelo();
    }

}
