package application.CRUD;

import application.DAO.VehiculoDAO;
import application.Domain.Coche;
import application.Domain.TipoCoche;
import application.Domain.Vehiculo;
import application.Utils.AlertUtils;
import com.sun.source.tree.BreakTree;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoCRUD {
    VehiculoDAO vehiculoDAO;

    public VehiculoCRUD()  {
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
        /*
        try {
            vehiculos = getVehiculos();
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("error al cargar la lista de vehiculos");
        }*/
    }

    public List<Vehiculo> getVehiculos() throws SQLException {
        return vehiculoDAO.getListado();
    }

    public void salir() throws SQLException {
        vehiculoDAO.desconectar();
    }

    public boolean insertarCoche(List<String> campos) throws SQLException {
        if (!comprobaciones(campos)) return false;
        String matricula = campos.get(0);
        String marca = campos.get(1);
        String modelo = campos.get(2);
        double precio = Double.parseDouble(campos.get(3));
        String carga = campos.get(4);
        int plazas = Integer.parseInt(campos.get(5));
        String tipo = campos.get(6);
        Coche coche = new Coche(matricula, marca, modelo, precio, carga, plazas, tipo);
        if (vehiculoDAO.insertarCoche(coche) > 0) return true;
        return false;
    }

    public boolean compruebaCampo(String contenido, String campo, String tipo) {
        boolean bool = true;
        if (campo.isEmpty()) {
            AlertUtils.mostrarError("El campo " + campo + " esta vacio");
            bool = false;
        }
        if (tipo.equals("Double") || tipo.equals("Int")) {
            if (!contenido.matches("\\d+")) {
                AlertUtils.mostrarError("El campo " + campo + "no es de tipo: " + tipo);
                bool = false;
            }
        }

        return bool;
    }

    public boolean comprobaciones(List<String> campos) {
        return compruebaCampo(campos.get(0), "matricula", "String") &&
                compruebaCampo(campos.get(1), "marca", "String") &&
                compruebaCampo(campos.get(2), "modelo", "String") &&
                compruebaCampo(campos.get(3), "precio", "Double") &&
                compruebaCampo(campos.get(4), "carga", "String") &&
                compruebaCampo(campos.get(5), "plazas", "Int") &&
                compruebaCampo(campos.get(6), "tipo", "String");

    }


}
