package application.Domain;

// clase vehiculo que es abstracta por lo que no se podrá crear objetos de tipo Vehiculo.
public abstract class Vehiculo {
    private String matricula;
    private String marca;
    private String modelo;
    private double precio; //€
    private String carga;

    public Vehiculo(String matricula, String marca, String modelo, double precio, String carga) {
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

    public double getPrecio() {
        return precio;
    }
}
