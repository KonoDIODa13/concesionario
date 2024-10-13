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

// extiende de SuperController
public class ListadoController extends SuperController implements Initializable {

    public AnchorPane rootPane;
    @FXML
    public ListView<Vehiculo> lvVehiculos;
    public Button btnEliminar;
    public Button btnModificar;

    List<Vehiculo> vehiculos = null;
    Vehiculo vehiculoSeleccionado = null;

    // funcion que se realiza cuando se crea el listadoController. lo utilizo para mostrar la lista de vehiculos.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lvVehiculos.getItems().clear();
        cargarlista();
    }

    // cambia la escena a la de inicio.
    public void atras(ActionEvent event) {
        CambioEscenas.cambioEscena("inicio.fxml", rootPane);
    }

    // cambia la escena a añadir.
    public void annadirVehiculo(ActionEvent event) {
        CambioEscenas.cambioEscena("insertar.fxml", rootPane);
    }

    // eliminará el vehiculo siempre y cuando se seleccione un vehiculo.
    public void eliminarVehiculo(ActionEvent event) {
        if (vehiculoSeleccionado != null) {
            int opcion = JOptionPane.showConfirmDialog(null, "Desea Borrar el vehiculo cuya matricula es: " + vehiculoSeleccionado.getMatricula());

            if (opcion == JOptionPane.YES_OPTION) {
                vehiculoCRUD.eliminarVehiculo(vehiculoSeleccionado);
                cargarlista();
                habilitarAcciones(true);
                vehiculoSeleccionado = null;
            }
        }
    }

    // cambiara a la escena de modificar (identica a insertar) pasandole el vehiculo seleccionado.
    public void modificaVehiculo(ActionEvent event) {
        if (vehiculoSeleccionado != null) {
            CambioEscenas.cambioEscena("modificar.fxml",rootPane, vehiculoSeleccionado);
        }
    }

    // seleccionará el vehiculo de la lista y habilirtará las acciones de eliminar y modificar.
    public void getVehiculo(MouseEvent mouseEvent) {
        try {
            vehiculoSeleccionado = lvVehiculos.getSelectionModel().getSelectedItem();
            habilitarAcciones(false);
        } catch (NullPointerException e) {
            AlertUtils.mostrarError("No has seleccionado ningun dato.\n");
        }
    }

    // metodo para cargar la lista.
    public void cargarlista() {
        try {
            vehiculos = vehiculoCRUD.getVehiculos();
            lvVehiculos.setItems(FXCollections.observableList(vehiculos));
        } catch (SQLException e) {
            AlertUtils.mostrarError("Error al cargar la lista de vehiculos");
        }
    }

    // habilita los botones
    public void habilitarAcciones(Boolean bool) {
        btnModificar.setDisable(bool);
        btnEliminar.setDisable(bool);
    }
}
