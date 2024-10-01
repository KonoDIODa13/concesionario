package application.Controller;

import application.DAO.VehiculoDAO;
import application.Utils.AlertUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;

public class AppController {
    private final VehiculoDAO vehiculoDao;
    public AppController() {
        vehiculoDao = new VehiculoDAO();
        try {
            vehiculoDao.conectar();
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error al conectar con la base de datos");
        } catch (ClassNotFoundException cnfe) {
            AlertUtils.mostrarError("Error al iniciar la aplicación");
        } catch (IOException ioe) {
            AlertUtils.mostrarError("Error al cargar la configuración");
        }
        //System.out.println(System.getProperty("user.home"));
    }
    @FXML
    public void buscarMatricula(ActionEvent event) throws SQLException {
        vehiculoDao.buscarMatricula();

    }
}
