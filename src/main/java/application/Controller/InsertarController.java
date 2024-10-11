package application.Controller;

import application.Domain.TipoCoche;
import application.Domain.TipoMoto;
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

public class InsertarController extends SuperController {

    public AnchorPane rootPane;

    @FXML
    public RadioButton rbCoche, rbMoto;

    @FXML
    public ToggleGroup tgTipo;

    @FXML
    TextField tfMatricula, tfMarca, tfModelo, tfPrecio, tfCarga, tfPlazas;

    @FXML
    private ComboBox<TipoCoche> cbTipoCoche;
    @FXML
    private ComboBox<TipoMoto> cbTipoMoto;

    int tipoVehiculo = 0;

    public void mostrarCB(ActionEvent event) {
        String tipos = tgTipo.getSelectedToggle().getUserData().toString();
        switch (tipos) {
            case "coche":
                cbTipoCoche.getItems().add(TipoCoche.DEPORTIVO);
                cbTipoCoche.getItems().add(TipoCoche.FAMILIAR);
                cbTipoCoche.getItems().add(TipoCoche.LIMUSINA);
                cbTipoCoche.getItems().add(TipoCoche.TODOTERRENO);
                cbTipoCoche.setVisible(true);
                cbTipoMoto.setVisible(false);
                tipoVehiculo = 1;
                break;

            case "moto":
                cbTipoMoto.getItems().add(TipoMoto.DEPORTIVA);
                cbTipoMoto.getItems().add(TipoMoto.MONTANNA);
                cbTipoMoto.setVisible(true);
                cbTipoCoche.setVisible(false);
                tipoVehiculo = 2;
                break;
        }
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
        if (tipoVehiculo == 1)
            insertarCampo(campos, cbTipoCoche.getValue().toString());
        else
            insertarCampo(campos, cbTipoMoto.getValue().toString());

        if (vehiculoCRUD.insertarCoche(campos)) {
            AlertUtils.mostrarConfirmacion("vehiculo creado");
            CambioEscenas.cambioEscena("listado.fxml", rootPane);
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
