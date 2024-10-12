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
    public int insertarCoche(Coche coche) throws SQLException {
        String sql = "INSERT INTO Coche (Matricula, Marca, Modelo, Precio, Carga, Plazas, Tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, coche.getMatricula());
        sentencia.setString(2, coche.getMarca());
        sentencia.setString(3, coche.getModelo());
        sentencia.setDouble(4, coche.getPrecio());
        sentencia.setString(5, coche.getCarga());
        sentencia.setInt(6, coche.getPlazas());
        sentencia.setString(7, coche.getTipo());

        return sentencia.executeUpdate();
    }

    public int insertarMoto(Moto moto) throws SQLException {
        String sql = "INSERT INTO Moto (Matricula, Marca, Modelo, Precio, Carga, Plazas, Tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, moto.getMatricula());
        sentencia.setString(2, moto.getMarca());
        sentencia.setString(3, moto.getModelo());
        sentencia.setDouble(4, moto.getPrecio());
        sentencia.setString(5, moto.getCarga());
        sentencia.setInt(6, moto.getPlazas());
        sentencia.setString(7, moto.getTipo());

        return sentencia.executeUpdate();
    }

    public List<Vehiculo> getListado() throws SQLException {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM coche";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();
        System.out.println(resultado);
        while (resultado.next()) {
            //int id = resultado.getInt(1);
            String matricula = resultado.getString(2);
            String marca = resultado.getString(3);
            String modelo = resultado.getString(4);
            double precio = resultado.getDouble(5);
            String carga = resultado.getString(6);
            int plazas = resultado.getInt(7);
            String tipo = resultado.getString(8);

            Coche coche = new Coche(matricula, marca, modelo, precio, carga, plazas, tipo);

            vehiculos.add(coche);
        }

        String sql2 = "SELECT * FROM coche";

        PreparedStatement sentencia2 = conexion.prepareStatement(sql2);
        ResultSet resultado2 = sentencia2.executeQuery();
        System.out.println(resultado2);
        while (resultado2.next()) {
            //int id = resultado.getInt(1);
            String matricula = resultado2.getString(2);
            String marca = resultado2.getString(3);
            String modelo = resultado2.getString(4);
            double precio = resultado2.getDouble(5);
            String carga = resultado2.getString(6);
            int plazas = resultado2.getInt(7);
            String tipo = resultado2.getString(8);

            Moto moto = new Moto(matricula, marca, modelo, precio, carga, plazas, tipo);

            vehiculos.add(moto);
        }

        return vehiculos;
    }

}
