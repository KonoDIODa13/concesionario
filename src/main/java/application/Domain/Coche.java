package application.Domain;

import java.util.Date;

public class Coche extends Vehiculo {
    public int plazas;
    public String tipo;

    public Coche() {
    }

    public Coche(int id, String matricula, String marca, String modelo, Date fechaSalida, Double precio, String carga, int plazas, String tipo) {
        super(id, matricula, marca, modelo, fechaSalida, precio, carga);
        this.plazas = plazas;
        this.tipo = tipo;
    }

    public int getPlazas() {
        return plazas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "plazas=" + plazas +
                ", tipo=" + tipo +
                ", matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", fechaSalida=" + fechaSalida +
                ", precio=" + precio +
                ", carga='" + carga + '\'' +
                '}';
    }
}
