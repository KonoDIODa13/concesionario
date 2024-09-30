package application.Domain;

import java.util.Date;

public class Coche extends Vehiculo {
    public String marca;
    public String modelo;
    public Date fecha;
    public Double precio;

    public Coche(String matricula, String marca, String modelo, Date fecha, Double precio) {
        super(matricula);
        this.marca = marca;
        this.modelo = modelo;
        this.fecha = fecha;
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
