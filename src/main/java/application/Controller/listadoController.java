package application.Controller;

import application.DAO.VehiculoDAO;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

import static application.Utils.CambioEscenas.cambioEscena;

public class listadoController extends SuperController {

    public AnchorPane rootPane;

    public void atras(ActionEvent event) {
        cambioEscena("inicio.fxml", rootPane);
    }
}
