package application.Controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.sql.SQLException;

import application.Utils.CambioEscenas;

// extiende de SuperController
public class InicioController extends SuperController {

    public AnchorPane rootPane;

    // cambia la escena a la de insertar
    public void mostrarInsertado(ActionEvent event) {
        CambioEscenas.cambioEscena("insertar.fxml", rootPane);
    }

    // cambia la escena a la del listado
    public void mostrarListado(ActionEvent event) {
        CambioEscenas.cambioEscena("listado.fxml", rootPane);
    }

    // cierra el programa y cierra la conexion
    public void salir(ActionEvent event) throws SQLException {
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            vehiculoCRUD.salir();
            System.exit(0); // CERRAR APLICACIÓN
        }
    }
}
