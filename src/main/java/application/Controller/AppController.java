package application.Controller;

import application.DAO.VehiculoDAO;
import application.Domain.Coche;
import application.Utils.AlertUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class AppController {
    private final VehiculoDAO vehiculoDao;
    TextField matriculaText, marcaText, modeloText, precioText, cargaText, plazasText, tipoText;
    DatePicker fechaSalidaDP;

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
    }

    @FXML
    public void buscarMatricula(ActionEvent event) throws SQLException {
        vehiculoDao.buscarMatricula();
    }

    @FXML
    public void insertarCoche(ActionEvent event) throws SQLException {
        String matricula = matriculaText.getText();
        String marca = marcaText.getText();
        String modelo = modeloText.getText();
        double precio = Double.parseDouble(precioText.getText());
        String carga = cargaText.getText();
        int plazas = Integer.parseInt(plazasText.getText());
        String tipo = tipoText.getText();
        Coche coche = new Coche(matricula, marca, modelo, precio, carga, plazas, tipo);
        if(vehiculoDao.insertarCoche(coche)){
            AlertUtils.mostrarError("vehiculo creado");
        }


    }
}
