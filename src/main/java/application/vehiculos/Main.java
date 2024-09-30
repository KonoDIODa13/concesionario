package application.vehiculos;

import application.Controller.AppController;
import application.Utils.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        AppController controller = new AppController();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("inicio.fxml"));
        loader.setController(controller);
        VBox vbox = loader.load();

        controller.cargarDatos();

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
        DigestUtils.sha3_256("hola");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }


    public static void main(String[] args) {
        launch();
    }
}