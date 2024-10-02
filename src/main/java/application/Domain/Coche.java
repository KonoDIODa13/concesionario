package application.Domain;

import java.time.LocalDate;
import java.util.Date;

public class Coche extends Vehiculo {
    public int plazas;
    public String tipo;

    public Coche() {
    }

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
                ", precio=" + precio +
                ", carga='" + carga + '\'' +
                '}';
    }
}
