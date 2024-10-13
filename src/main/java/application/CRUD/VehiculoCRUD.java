package application.CRUD;

import application.DAO.VehiculoDAO;
import application.Domain.Coche;
import application.Domain.Moto;
import application.Domain.Vehiculo;
import application.Utils.AlertUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class VehiculoCRUD {
    VehiculoDAO vehiculoDAO;

    // funcion que comprueba la conexion con la bd.
    public VehiculoCRUD() {
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

    // funcion que me devuelve la lista con todos los vehiculos
    public List<Vehiculo> getVehiculos() throws SQLException {
        return vehiculoDAO.getListado();
    }

    // funcion que desconecta con la base de datos
    public void salir() throws SQLException {
        vehiculoDAO.desconectar();
    }

    public boolean insertarVehiculo(List<String> campos) throws SQLException {
        // primero compruebo que los campos son del tipo que necesito
        if (!comprobaciones(campos)) return false;

        List<Vehiculo> vehiculos = getVehiculos();
        String matricula = campos.get(0);
        String marca = campos.get(1);
        String modelo = campos.get(2);
        double precio = Double.parseDouble(campos.get(3));
        String carga = campos.get(4);
        int plazas = Integer.parseInt(campos.get(5));
        String tipo = campos.get(6);
        int tipoV = Integer.parseInt(campos.get(7));

        // el tipo que le paso, me dirá si estoy insertando un coche o una moto
        if (tipoV == 1) {
            Coche coche = new Coche(matricula, marca, modelo, precio, carga, plazas, tipo);

            // aqui compruebo si el vehiculo ya esta en la lista.
            if (vehiculos.contains(coche)) {
                AlertUtils.mostrarError("El coche: " + matricula + " ya existe en la bd.");
                return false;
            }

            // aqui compruebo si hay un vehiculo con esa misma matricula en la lista.
            if (vehiculos.stream().anyMatch(vehiculo -> vehiculo.getMatricula().equalsIgnoreCase(matricula))) {
                AlertUtils.mostrarError("El coche: " + matricula + " ya existe en la bd.");
                return false;
            }

            return vehiculoDAO.insertarCoche(coche);

        } else {
            Moto moto = new Moto(matricula, marca, modelo, precio, carga, plazas, tipo);

            // aqui compruebo si el vehiculo ya esta en la lista.
            if (vehiculos.contains(moto)) {
                AlertUtils.mostrarError("la moto: " + matricula + " ya existe en la bd.");
                return false;
            }

            // aqui compruebo si hay un vehiculo con esa misma matricula en la lista.
            if (vehiculos.stream().anyMatch(vehiculo -> vehiculo.getMatricula().equalsIgnoreCase(matricula))) {
                AlertUtils.mostrarError("la moto: " + matricula + " ya existe en la bd.");
                return false;
            }

            return vehiculoDAO.insertarMoto(moto);
        }
    }
    // funcion para eliminar el vehiculo
    public void eliminarVehiculo(Vehiculo vehiculo) {
        try {
            if (vehiculoDAO.eliminarVehiculo(vehiculo)) {
                AlertUtils.mostrarConfirmacion("Vehiculo borrado con exito");
            } else {
                AlertUtils.mostrarError("Error al borrar Vehiculo");
            }
        } catch (SQLException e) {
            AlertUtils.mostrarError("Hubo algun error al eliminar el vehiculo de la base de datos.");
        }
    }

    /*
     funcion para modificar el vehiculo,
     tengo la lista con los campos metidos en el formulario y los parseo a lo que me interesan
     tengo el vehiculo antes de cambiar para conseguir el id de dicho vehiculo.
    */
    public boolean modificarVehiculo(List<String> campos, Vehiculo preVehiculo) throws SQLException {
        // compruebo si los campos correctos
        if (!comprobaciones(campos)) return false;

        String matricula = campos.get(0);
        String marca = campos.get(1);
        String modelo = campos.get(2);
        double precio = Double.parseDouble(campos.get(3));
        String carga = campos.get(4);
        int plazas = Integer.parseInt(campos.get(5));
        String tipo = campos.get(6);
        int tipoV = Integer.parseInt(campos.get(7));

        // gracias al tipoV puedo saber si lo que tengo que modificar es un coche o una moto (al igual que al insertar)
        if (tipoV == 1) {
            Coche coche = new Coche(matricula, marca, modelo, precio, carga, plazas, tipo);

            // llamo al vehiculoDAO que es el que realmente va a conectarse a la bd para modificar el coche
            return vehiculoDAO.modificarCoche(coche, preVehiculo);

        } else {
            Moto moto = new Moto(matricula, marca, modelo, precio, carga, plazas, tipo);

            // llamo al vehiculoDAO que es el que realmente va a conectarse a la bd para modificar el coche
            return vehiculoDAO.modificarMoto(moto, preVehiculo);
        }
    }

    public boolean compruebaCampo(String contenido, String campo, String tipo) {
        boolean bool = true;

        // aqui compruebo si el campo esta vacio
        if (campo.isEmpty()) {
            AlertUtils.mostrarError("El campo " + campo + " esta vacio");
            bool = false;
        }
        // aqui compruebo que si el tipo de dato que tengo que meter (en este caso double), el contenido se adecua.
        if (tipo.equals("Double")) {

            // el .matches() es un metodo de los Strings para comprobar que tiene patron que pasas por argumento.
            if (!contenido.matches("\\d+") && !contenido.matches("\\d+\\.\\d+")) { // \\d+ es para numeros y el \\. es que es necesario el .
                AlertUtils.mostrarError("El campo " + campo + " no es de tipo: " + tipo);
                bool = false;
            }
            // aqui compruebo si el tipo de dato que tengo que meter es int y si es de tipo numerico con el .matches(\\d+)
        } else if (tipo.equals("Int")) {
            if (!contenido.matches("\\d+")) {
                AlertUtils.mostrarError("El campo " + campo + " no es de tipo: " + tipo);
                bool = false;
            }
        }

        return bool;
    }

    // realizo las comprobaciones para todas los campos metidos desde el formulario
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
