package application.DAO;

import application.Domain.Coche;
import application.Domain.Moto;
import application.Domain.Vehiculo;
import application.Utils.R;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class VehiculoDAO {

    private Connection conexion;

    public void conectar() throws ClassNotFoundException, SQLException, IOException {
        Properties configuration = new Properties();
        configuration.load(R.getProperties("database.properties"));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
    }

    public void desconectar() throws SQLException {
        conexion.close();
    }

    /*
        public void buscarMatricula() throws SQLException {
            String sql = "SELECT * FROM coche where matricula =?";

            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, "1234ABC");
            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()) {
                Coche coche = new Coche();
                coche.setMatricula(resultado.getString(1));
                coche.setMarca(resultado.getString(2));
                coche.setModelo(resultado.getString(3));
                coche.setPrecio(resultado.getDouble(4));
                coche.setCarga(resultado.getString(5));
                coche.setPlazas(resultado.getInt(6));
                coche.setTipo(resultado.getString(7));
                System.out.println(coche);
            }
        }
    */
    public boolean insertarCoche(Coche coche) throws SQLException {
        String insertVehiculo = "INSERT INTO Vehiculo (Matricula, Marca, Modelo, Precio, Carga, Tipo) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement sentenciaVehiculo = conexion.prepareStatement(insertVehiculo);
        sentenciaVehiculo.setString(1, coche.getMatricula());
        sentenciaVehiculo.setString(2, coche.getMarca());
        sentenciaVehiculo.setString(3, coche.getModelo());
        sentenciaVehiculo.setDouble(4, coche.getPrecio());
        sentenciaVehiculo.setString(5, coche.getCarga());
        sentenciaVehiculo.setString(6, "coche");
        if (sentenciaVehiculo.executeUpdate() > 0) {

            int idvehiculo = getId(coche.getMatricula());
            System.out.println(idvehiculo);
            String insertCoche = "INSERT INTO Coche (IdVehiculo, Plazas, Tipo) VALUES (?,?,?)";
            PreparedStatement sentenciaCoche = conexion.prepareStatement(insertCoche);
            sentenciaCoche.setInt(1, idvehiculo);
            sentenciaCoche.setInt(2, coche.getPlazas());
            sentenciaCoche.setString(3, coche.getTipo());

            return sentenciaCoche.executeUpdate() > 0;
        }
        return false;
    }

    public int getId(String matricula) throws SQLException {
        int id = -1;
        String sql = "SELECT Id FROM Vehiculo WHERE Matricula= ?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, matricula);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            id = resultado.getInt(1);
        }
        return id;
    }

    public boolean insertarMoto(Moto moto) throws SQLException {
        String insertVehiculo = "INSERT INTO Vehiculo (Matricula, Marca, Modelo, Precio, Carga, tipo) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement sentenciaVehiculo = conexion.prepareStatement(insertVehiculo);
        sentenciaVehiculo.setString(1, moto.getMatricula());
        sentenciaVehiculo.setString(2, moto.getMarca());
        sentenciaVehiculo.setString(3, moto.getModelo());
        sentenciaVehiculo.setDouble(4, moto.getPrecio());
        sentenciaVehiculo.setString(5, moto.getCarga());
        sentenciaVehiculo.setString(6, "moto");
        if (sentenciaVehiculo.executeUpdate() > 0) {

            int idvehiculo = getId(moto.getMatricula());
            System.out.println(idvehiculo);
            String insertMoto = "INSERT INTO Moto (IdVehiculo, Plazas, Tipo) VALUES (?,?,?)";
            PreparedStatement sentenciaMoto = conexion.prepareStatement(insertMoto);
            sentenciaMoto.setInt(1, idvehiculo);
            sentenciaMoto.setInt(2, moto.getPlazas());
            sentenciaMoto.setString(3, moto.getTipo());

            return sentenciaMoto.executeUpdate() > 0;
        }
        return false;
    }

    public List<Vehiculo> getListado() throws SQLException {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT Matricula, Marca, Modelo, Precio, Carga, vehiculo.tipo, Plazas, coche.Tipo " +
                "FROM vehiculo, coche " +
                "WHERE vehiculo.Id = coche.IdVehiculo" +
                " UNION ALL " +
                "SELECT Matricula, Marca, Modelo, Precio, Carga, vehiculo.tipo, Plazas, moto.Tipo " +
                "FROM vehiculo, moto " +
                "WHERE vehiculo.Id = moto.IdVehiculo;";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            String matricula = resultado.getString(1);
            String marca = resultado.getString(2);
            String modelo = resultado.getString(3);
            double precio = resultado.getDouble(4);
            String carga = resultado.getString(5);
            String tipoV = resultado.getString(6);
            int plazas = resultado.getInt(7);
            String tipo = resultado.getString(8);

            if (tipoV.equals("coche")) {
                Coche coche = new Coche(matricula, marca, modelo, precio, carga, plazas, tipo);
                vehiculos.add(coche);

            } else {
                Moto moto = new Moto(matricula, marca, modelo, precio, carga, plazas, tipo);
                vehiculos.add(moto);
            }
        }

        return vehiculos;
    }

    public boolean eliminarVehiculo(Vehiculo vehiculo) throws SQLException {
        String sql = "DELETE FROM VEHICULO WHERE Matricula=?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, vehiculo.getMatricula());
        return sentencia.executeUpdate() > 0;
    }

}
