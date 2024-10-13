package application.Controller;


import application.Domain.Vehiculo;
import application.Utils.AlertUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import application.Utils.CambioEscenas;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ListadoController extends SuperController implements Initializable {

    public AnchorPane rootPane;
    @FXML
    public ListView<Vehiculo> lvVehiculos;
    public Button btnEliminar;
    public Button btnModificar;

    List<Vehiculo> vehiculos = null;
    Vehiculo vehiculoSeleccionado = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lvVehiculos.getItems().clear();
        cargarlista();
    }

    public void atras(ActionEvent event) {
        CambioEscenas.cambioEscena("inicio.fxml", rootPane);
    }

    public void annadirVehiculo(ActionEvent event) {
        CambioEscenas.cambioEscena("insertar.fxml", rootPane);
    }

    public void eliminarVehiculo(ActionEvent event) {
        if (vehiculoSeleccionado != null) {
            int opcion = JOptionPane.showConfirmDialog(null, "Desea Borrar el vehiculo cuya matricula es: " + vehiculoSeleccionado.getMatricula());

            if (opcion == JOptionPane.YES_OPTION) {
                vehiculoCRUD.eliminarVehiculo(vehiculoSeleccionado);
                cargarlista();
            }
        }
    }

    public void modificaVehiculo(ActionEvent event) {
    }

    public void getVehiculo(MouseEvent mouseEvent) {
        try {
            vehiculoSeleccionado = lvVehiculos.getSelectionModel().getSelectedItem();
            //cargarPelicula(peliculaSeleccionada);
        } catch (NullPointerException e) {
            AlertUtils.mostrarError("No has seleccionado ningun dato.\n");
        }
    }

    public void cargarlista() {
        try {
            vehiculos = vehiculoCRUD.getVehiculos();
            lvVehiculos.setItems(FXCollections.observableList(vehiculos));
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error al cargar la lista de vehiculos");
        }
    }
}
