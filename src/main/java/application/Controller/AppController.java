package application.Controller;

import application.Utils.AlertUtils;
import application.Utils.R;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static application.Utils.CambioEscenas.cambioEscena;

public class AppController {
    public AnchorPane rootPane;

    public void mostrarInsertado(ActionEvent event) {
        cambioEscena("insertar.fxml", rootPane);
    }

    public void mostrarListado(ActionEvent event) {
        cambioEscena("listado.fxml",rootPane);
    }

    public void salir(ActionEvent event) {
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0); // CERRAR APLICACIÓN
        }
    }


}
