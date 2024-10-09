package application.Controller;

import application.CRUD.VehiculoCRUD;
import application.DAO.VehiculoDAO;
import application.Utils.AlertUtils;
import javafx.event.ActionEvent;

import java.io.IOException;

import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.sql.SQLException;

import static application.Utils.CambioEscenas.cambioEscena;

public class AppController extends SuperController{

    public AnchorPane rootPane;

    public AppController() {

    }

    public void mostrarInsertado(ActionEvent event) {
        cambioEscena("insertar.fxml", rootPane);
    }

    public void mostrarListado(ActionEvent event) {
        cambioEscena("listado.fxml", rootPane);
    }

    public void salir(ActionEvent event) throws SQLException {
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            vehiculoCRUD.salir();
            System.exit(0); // CERRAR APLICACIÓN
        }
    }
}
