package application.Utils;

import application.Controller.ModificarController;
import application.Domain.Vehiculo;
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

    static public void cambioEscena(String fxmlnName, AnchorPane rootPane, Vehiculo vehiculo) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI(fxmlnName));

            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            //El unico cambio respecto al cambio pantallas normal es que le paso el controller para poder pasarle parametros
            ModificarController modificarController = loader.getController();
            modificarController.setVehiculo(vehiculo);

            newStage.sizeToScene();
            newStage.show();

            Stage currentStage = (Stage) rootPane.getScene().getWindow();
            currentStage.close();

        } catch (IOException IOex) {
            AlertUtils.mostrarError("Error al cargar el FXML");
        }
    }
}
