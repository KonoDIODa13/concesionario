package application.Domain;

import java.util.Date;

public abstract class Vehiculo {
    private int id;
    public String matricula;
    public String marca;
    public String modelo;
    public Date fechaSalida;
    public Double precio; //€
    public String carga;


    public Vehiculo() {
    }

    public Vehiculo(int id, String matricula, String marca, String modelo, Date fechaSalida, Double precio, String carga) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.fechaSalida = fechaSalida;
        this.precio = precio;
        this.carga = carga;
    }

    public String getMatricula() {
        return matricula;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public String getCarga() {
        return carga;
    }

    public void setFechaSalida(Date fechaSalida) {
this.fechaSalida = fechaSalida;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
