package application.DAO;

import application.Domain.Coche;
import application.Domain.TipoCoche;
import application.Utils.R;

import java.io.IOException;
import java.sql.*;
import java.util.Locale;
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
        System.out.println("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC"+
                username+ password);
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
    }

    public void desconectar() throws SQLException {
        conexion.close();
    }

    public void buscarMatricula() throws SQLException {
        String sql = "SELECT * FROM concesionario.coche where Matricula= '1234ABC'";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        //sentencia.setString(1, "1234ABC");
        ResultSet resultado = sentencia.executeQuery();
       // while (resultado.next()) {
            Coche coche = new Coche();
            coche.setId(resultado.getInt(1));
            coche.setMatricula(resultado.getString(2));
            coche.setMarca(resultado.getString(3));
            coche.setModelo(resultado.getString(4));
            coche.setFechaSalida(resultado.getDate(5));
            coche.setPrecio(resultado.getDouble(6));
            coche.setCarga(resultado.getString(7));
            coche.setPlazas(resultado.getInt(8));
            coche.setTipo(resultado.getString(9));
            System.out.println(coche);

        //}

       /* return new Coche(
                resultado.getString(1), resultado.getString(2), resultado.getString(3),
                resultado.getDate(4), resultado.getDouble(5), resultado.getString(6),
                resultado.getInt(5), TipoCoche.FAMILIAR);
                */

    }

}
