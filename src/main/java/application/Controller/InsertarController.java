package application.Controller;

import application.Domain.TipoCoche;
import application.Utils.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Utils.CambioEscenas;

public class InsertarController extends SuperController implements Initializable {

    public AnchorPane rootPane;
    @FXML
    TextField tfMatricula, tfMarca, tfModelo, tfPrecio, tfCarga, tfPlazas;

    @FXML
    private ComboBox<TipoCoche> cbTipo;

    int tCoche = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbTipo.getItems().add(TipoCoche.DEPORTIVO);
        cbTipo.getItems().add(TipoCoche.FAMILIAR);
        cbTipo.getItems().add(TipoCoche.LIMUSINA);
        cbTipo.getItems().add(TipoCoche.TODOTERRENO);
    }

    @FXML
    public void insertar(ActionEvent event) throws SQLException {
        List<String> campos = new ArrayList<>();
        insertarCampo(campos, tfMatricula.getText());
        insertarCampo(campos, tfMarca.getText());
        insertarCampo(campos, tfModelo.getText());
        insertarCampo(campos, tfPrecio.getText());
        insertarCampo(campos, tfCarga.getText());
        insertarCampo(campos, tfPlazas.getText());
        insertarCampo(campos, cbTipo.getValue().toString());

        if (vehiculoCRUD.insertarCoche(campos)) {
            AlertUtils.mostrarConfirmacion("vehiculo creado");
        } else {
            AlertUtils.mostrarError("error al insertar");
        }
    }

    public void insertarCampo(List<String> campos, String campo) {
        campos.add(campo);
    }

    public void atras(ActionEvent event) {
        CambioEscenas.cambioEscena("inicio.fxml", rootPane);
    }

}
