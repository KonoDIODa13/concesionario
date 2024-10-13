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

    public boolean insertarCoche(Coche coche) throws SQLException {
        //primero inserto en la tabla vehiculo.
        if (insertarVehiculo(coche, "coche")) {
            //una vez insertado el vehiculo, busco el id del vehiculo para insertarselo a al coche
            int idvehiculo = getId(coche.getMatricula());

            String insertCoche = "INSERT INTO Coche (IdVehiculo, Plazas, Tipo) VALUES (?,?,?)";
            PreparedStatement sentenciaCoche = conexion.prepareStatement(insertCoche);
            sentenciaCoche.setInt(1, idvehiculo);
            sentenciaCoche.setInt(2, coche.getPlazas());
            sentenciaCoche.setString(3, coche.getTipo());

            return sentenciaCoche.executeUpdate() > 0;
        }
        return false;
    }

    public boolean insertarMoto(Moto moto) throws SQLException {
        //primero inserto en la tabla vehiculo.
        if (insertarVehiculo(moto, "moto")) {
            //una vez insertado el vehiculo, busco el id del vehiculo para insertarselo a la moto
            int idvehiculo = getId(moto.getMatricula());

            String insertMoto = "INSERT INTO Moto (IdVehiculo, Plazas, Tipo) VALUES (?,?,?)";
            PreparedStatement sentenciaMoto = conexion.prepareStatement(insertMoto);
            sentenciaMoto.setInt(1, idvehiculo);
            sentenciaMoto.setInt(2, moto.getPlazas());
            sentenciaMoto.setString(3, moto.getTipo());
            return sentenciaMoto.executeUpdate() > 0;
        }
        return false;
    }

    public boolean modificarCoche(Coche coche, Vehiculo preVehiculo) throws SQLException {
        //primero consigo el id del vehiculo (antes de la modificación) para modificar en las dos tablas.
        int id = getId(preVehiculo.getMatricula());
        if (modificarVehiculo(coche, "coche", id)) {
            //Aqui utilizo el idVehiculo que esta en la tabla de coche para modificarlo
            String sql = "UDPATE Coche SET Plazas=?,tipo=? WHERE idVehiculo=?";
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, coche.getPlazas());
            sentencia.setString(2, coche.getTipo());
            sentencia.setInt(3, id);
            return sentencia.executeUpdate() > 0;
        }
        return false;
    }

    public boolean modificarMoto(Moto moto, Vehiculo preVehiculo) throws SQLException {
        //primero consigo el id del vehiculo (antes de la modificación) para modificar en las dos tablas.
        int id = getId(preVehiculo.getMatricula());
        if (modificarVehiculo(moto, "moto", id)) {
            //Aqui utilizo el idVehiculo que esta en la tabla de moto para modificarlo
            String sql = "UPDATE Moto SET Plazas=?,tipo=? WHERE idVehiculo=?";
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, moto.getPlazas());
            sentencia.setString(2, moto.getTipo());
            sentencia.setInt(3, id);
            return sentencia.executeUpdate() > 0;
        }
        return false;
    }

    public boolean modificarVehiculo(Vehiculo vehiculo, String tipoV, int id) throws SQLException {
        //Aqui utilizo el id del vehiculo
        String sql = "UPDATE Vehiculo SET Matricula= ?, Marca= ?, Modelo= ? ,Precio= ? ,Carga= ? ,Tipo= ? WHERE id= ?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, vehiculo.getMatricula());
        sentencia.setString(2, vehiculo.getMarca());
        sentencia.setString(3, vehiculo.getModelo());
        sentencia.setDouble(4, vehiculo.getPrecio());
        sentencia.setString(5, vehiculo.getCarga());
        sentencia.setString(6, tipoV);
        sentencia.setInt(7, id);
        return sentencia.executeUpdate() > 0;

    }

    public boolean insertarVehiculo(Vehiculo vehiculo, String tipoV) throws SQLException {
        String insertVehiculo = "INSERT INTO Vehiculo (Matricula, Marca, Modelo, Precio, Carga, Tipo) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement sentenciaVehiculo = conexion.prepareStatement(insertVehiculo);
        sentenciaVehiculo.setString(1, vehiculo.getMatricula());
        sentenciaVehiculo.setString(2, vehiculo.getMarca());
        sentenciaVehiculo.setString(3, vehiculo.getModelo());
        sentenciaVehiculo.setDouble(4, vehiculo.getPrecio());
        sentenciaVehiculo.setString(5, vehiculo.getCarga());
        sentenciaVehiculo.setString(6, tipoV);
        return sentenciaVehiculo.executeUpdate() > 0;
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


    public List<Vehiculo> getListado() throws SQLException {
        List<Vehiculo> vehiculos = new ArrayList<>();
        // la sql que conseguí que seleccionara todos los campos que necesitaba a la hora de mostrar en la lista gracias al union all
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
            //lo hago asi para diferenciarlo en la lista.
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

    // Debido a como esta creada la BD, no necesito borrar en la tabla coche o moto, se borra automaticamente.
    public boolean eliminarVehiculo(Vehiculo vehiculo) throws SQLException {
        String sql = "DELETE FROM VEHICULO WHERE Matricula=?";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, vehiculo.getMatricula());
        return sentencia.executeUpdate() > 0;
    }

}
