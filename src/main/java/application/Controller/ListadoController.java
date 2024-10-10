package application.Controller;


import application.Domain.Vehiculo;
import application.Utils.AlertUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import application.Utils.CambioEscenas;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ListadoController extends SuperController implements Initializable {

    public AnchorPane rootPane;
    @FXML
    public ListView<Vehiculo> lvVehiculos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lvVehiculos.getItems().clear();
        try {
            List<Vehiculo> vehiculos = vehiculoCRUD.getVehiculos();
            lvVehiculos.setItems(FXCollections.observableList(vehiculos));
        } catch (SQLException e) {
            AlertUtils.mostrarError("error al cargar la lista");
        }

    }

    public void atras(ActionEvent event) {
        CambioEscenas.cambioEscena("inicio.fxml", rootPane);
    }

}
