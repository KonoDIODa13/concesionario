package application.CRUD;

import application.DAO.VehiculoDAO;
import application.Domain.Coche;
import application.Domain.Vehiculo;
import application.Utils.AlertUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehiculoCRUD {
    ArrayList<Vehiculo> vehiculos;
    VehiculoDAO vehiculoDAO;

    public VehiculoCRUD() {
        vehiculos = new ArrayList<>();
        vehiculoDAO = new VehiculoDAO();
        try {
            vehiculoDAO.conectar();
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error al conectar con la base de datos");
        } catch (ClassNotFoundException cnfe) {
            AlertUtils.mostrarError("Error al iniciar la aplicación");
        } catch (IOException ioe) {
            AlertUtils.mostrarError("Error al cargar la configuración");
        }
    }

    public ArrayList<Vehiculo> getVehiculos() throws SQLException {
        vehiculos = vehiculoDAO.getListado();
        return vehiculos;
    }

    public void salir() throws SQLException {
        vehiculoDAO.desconectar();
    }

    public boolean insertarCoche(Coche coche) throws SQLException {
        return vehiculoDAO.insertarCoche(coche);

    }

}
