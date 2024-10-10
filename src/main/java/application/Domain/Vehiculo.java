package application.Domain;

public abstract class Vehiculo {
    public String matricula;
    public String marca;
    public String modelo;
    public Double precio; //€
    public String carga;

    public Vehiculo(String matricula, String marca, String modelo, Double precio, String carga) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.carga = carga;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCarga() {
        return carga;
    }

    public Double getPrecio() {
        return precio;
    }
}
