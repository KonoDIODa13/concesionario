package application.Controller;

import application.Domain.TipoCoche;
import application.Domain.TipoMoto;
import application.Utils.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.Utils.CambioEscenas;
import javafx.scene.text.Text;

import javax.swing.*;

// extiende de SuperController
public class InsertarController extends SuperController {

    public AnchorPane rootPane;

    @FXML
    public RadioButton rbCoche, rbMoto;

    @FXML
    public ToggleGroup tgTipo;

    @FXML
    TextField tfMatricula, tfMarca, tfModelo, tfPrecio, tfCarga, tfPlazas;

    @FXML
    Text tTipo;

    @FXML
    private ComboBox<TipoCoche> cbTipoCoche;
    @FXML
    private ComboBox<TipoMoto> cbTipoMoto;

    int tipoVehiculo = 0;
    // creo esta variable para controlar el tipo de vehiculo que inserto: 1 para coche, 2 para moto.

    // en este metodo segun el radio button seleccionado escribo las opciones del tipo de vehiculo.
    public void mostrarCB(ActionEvent event) {
        String tipos = tgTipo.getSelectedToggle().getUserData().toString();
        cbTipoCoche.getItems().clear();
        cbTipoMoto.getItems().clear();
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
        tTipo.setVisible(true);
    }

    @FXML
    public void insertar(ActionEvent event) throws SQLException {
        // los el contenido de los campos los inserto en la  lista de strings que luego pasare al vehiculoCRUD para realizar las comprobaciones.
        List<String> campos = new ArrayList<>();
        insertarCampo(campos, tfMatricula.getText());
        insertarCampo(campos, tfMarca.getText());
        insertarCampo(campos, tfModelo.getText());
        insertarCampo(campos, tfPrecio.getText());
        insertarCampo(campos, tfCarga.getText());
        insertarCampo(campos, tfPlazas.getText());

        // si el tipo es 1 insertaré un coche y cogeré el campo del coche, si no insertaré una moto y cogeré el campo de moto.
        if (tipoVehiculo == 1) insertarCampo(campos, cbTipoCoche.getValue().toString());
        else insertarCampo(campos, cbTipoMoto.getValue().toString());

        // aqui insertaré a la lista el tipo de vehiculo (si es moto o coche).
        insertarCampo(campos, String.valueOf(tipoVehiculo));

        if (vehiculoCRUD.insertarVehiculo(campos)) {

            // un optionpane para preguntar si quiere insertar mas vehiculos o ir directamente a la ver la lista de vehiculos
            int opcion = JOptionPane.showConfirmDialog(null, "vehiculo creado.\n?quieres insertar otro vehiculo?", "confirmacion", JOptionPane.YES_NO_OPTION);

            if (opcion != JOptionPane.YES_OPTION) CambioEscenas.cambioEscena("listado.fxml", rootPane);
            else limpiaCampos();
        } else {
            AlertUtils.mostrarError("error al insertar");
        }
    }

    // funcion para automatizar el añadido a la lista de strings los campos del formulario
    public void insertarCampo(List<String> campos, String campo) {
        campos.add(campo);
    }

    // en caso de quiera seguir insertando vehiculos, se lipiará los campos.
    public void limpiaCampos() {
        tfMatricula.setText("");
        tfMarca.setText("");
        tfModelo.setText("");
        tfPrecio.setText("");
        tfCarga.setText("");
        tfPlazas.setText("");
        cbTipoCoche.getItems().clear();
        cbTipoMoto.getItems().clear();
        tipoVehiculo = 0;
        tgTipo.selectToggle(null);
        ocultarTipos();
    }

    // oculta los tipos (combox) para cuando se limpian los campos
    public void ocultarTipos() {
        tTipo.setVisible(false);
        cbTipoCoche.setVisible(false);
        cbTipoMoto.setVisible(false);
    }

    // cambia la escena a la de inicio
    public void atras(ActionEvent event) {
        CambioEscenas.cambioEscena("inicio.fxml", rootPane);
    }

}
