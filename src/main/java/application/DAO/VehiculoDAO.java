package application.DAO;

import application.Domain.Coche;
import application.Domain.TipoCoche;
import application.Domain.Vehiculo;
import application.Utils.R;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
    }

    public void desconectar() throws SQLException {
        conexion.close();
    }

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

    public boolean insertarCoche(Coche coche) throws SQLException {
        /*
            INSERT INTO Coche (Matricula, Marca, Modelo, Precio, Carga, Plazas, Tipo)
            VALUES ("1234ABC","Nissan","Almera Tino", 1500.00, "300KG", 5,"FAMILIAR");
         */
        boolean comprobado = false;
        String sql = "INSERT INTO Coche (Matricula, Marca, Modelo, Precio, Carga, Plazas, Tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, coche.getMatricula());
        sentencia.setString(2, coche.getMarca());
        sentencia.setString(3, coche.getModelo());
        sentencia.setDouble(4, coche.getPrecio());
        sentencia.setString(5, coche.getCarga());
        sentencia.setInt(6, coche.getPlazas());
        sentencia.setString(7, coche.getTipo());

        ResultSet resultado = sentencia.executeQuery();

        if (resultado.next()) {
            comprobado = true;
        }
        return comprobado;
    }

    public ArrayList<Vehiculo> getListado() throws SQLException {
        ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
        String sql = "SELECT * FROM COCHES";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();

        while (resultado.next()) {
            // int id = resultado.getInt(1);
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
        return vehiculos;
    }

}
