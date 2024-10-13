package application.vehiculos;

import application.Utils.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("inicio.fxml"));

        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
       // para arrancar la aplicacion mejor utilizar el metodo que no enseñastes del fichero de editar las configuraciones
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }


    public static void main(String[] args) {
        launch();
    }
}