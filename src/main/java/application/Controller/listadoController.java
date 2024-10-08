package application.Controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

import static application.Utils.CambioEscenas.cambioEscena;

public class listadoController {
    public AnchorPane rootPane;

    public void atras(ActionEvent event) {
        cambioEscena("inicio.fxml", rootPane);
    }
}
