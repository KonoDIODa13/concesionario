package application.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CambioEscenas {
    static public void cambioEscena(String fxmlnName, AnchorPane rootPane) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI(fxmlnName));

            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.sizeToScene();
            newStage.show();

            Stage currentStage = (Stage) rootPane.getScene().getWindow();
            currentStage.close();

        } catch (IOException IOex) {
            AlertUtils.mostrarError("Error al cargar el FXML");
        }
    }
}
