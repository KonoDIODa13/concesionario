package application.Controller;

import application.Domain.*;
import application.Utils.AlertUtils;
import application.Utils.CambioEscenas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ModificarController extends SuperController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private ComboBox<TipoCoche> cbTipoCoche;

    @FXML
    private ComboBox<TipoMoto> cbTipoMoto;

    @FXML
    private RadioButton rbCoche, rbMoto;

    @FXML
    private Text tTipo;

    @FXML
    private TextField tfMatricula, tfMarca, tfModelo, tfPrecio, tfCarga, tfPlazas;

    @FXML
    private ToggleGroup tgTipo;

    int tipoVehiculo = 0;

    Vehiculo preVehiculo = null;

    public void setVehiculo(Vehiculo vehiculo) {
        cargarData(vehiculo);
        preVehiculo = vehiculo;
    }

    public void cargarData(Vehiculo vehiculo) {
        if (vehiculo instanceof Coche coche) {
            tfMatricula.setText(coche.getMatricula());
            tfMarca.setText(coche.getMarca());
            tfModelo.setText(coche.getModelo());
            tfPrecio.setText(Double.toString(coche.getPrecio()));
            tfCarga.setText(coche.getCarga());
            tfPlazas.setText(Integer.toString(coche.getPlazas()));
            tgTipo.selectToggle(rbCoche);
            insertarCamposCB("coche");
            TipoCoche motoSeleccionada = null;
            for (TipoCoche tipo : TipoCoche.values()) {
                if (tipo.toString().equals(coche.getTipo())) {
                    motoSeleccionada = tipo;
                }
            }
            cbTipoCoche.setValue(motoSeleccionada);
        } else if (vehiculo instanceof
                Moto moto) {
            tfMatricula.setText(moto.getMatricula());
            tfMarca.setText(moto.getMarca());
            tfModelo.setText(moto.getModelo());
            tfPrecio.setText(Double.toString(moto.getPrecio()));
            tfCarga.setText(moto.getCarga());
            tfPlazas.setText(Integer.toString(moto.getPlazas()));
            tgTipo.selectToggle(rbMoto);
            insertarCamposCB("moto");
            TipoMoto motoSeleccionada = null;
            for (TipoMoto tipo : TipoMoto.values()) {
                if (tipo.toString().equals(moto.getTipo())) {
                    motoSeleccionada = tipo;
                }
            }
            cbTipoMoto.setValue(motoSeleccionada);

        }
    }

    @FXML
    void atras(ActionEvent event) {
        CambioEscenas.cambioEscena("listado.fxml", rootPane);
    }

    @FXML
    void modificar(ActionEvent event) throws SQLException {
        List<String> campos = new ArrayList<>();
        insertarCampo(campos, tfMatricula.getText());
        insertarCampo(campos, tfMarca.getText());
        insertarCampo(campos, tfModelo.getText());
        insertarCampo(campos, tfPrecio.getText());
        insertarCampo(campos, tfCarga.getText());
        insertarCampo(campos, tfPlazas.getText());

        if (tipoVehiculo == 1) insertarCampo(campos, cbTipoCoche.getValue().toString());
        else insertarCampo(campos, cbTipoMoto.getValue().toString());

        insertarCampo(campos, String.valueOf(tipoVehiculo));

        int opcion = JOptionPane.showConfirmDialog(null, "seguro que quieres modificar el vehiculo cuya matricula era: " + preVehiculo.getMatricula());

        if (opcion == JOptionPane.YES_OPTION) {
            if (vehiculoCRUD.modificarVehiculo(campos, preVehiculo)) {
                CambioEscenas.cambioEscena("listado.fxml", rootPane);
            } else AlertUtils.mostrarError("Error al modificar el vehiculo");
        }
    }

    @FXML
    void mostrarCB(ActionEvent event) {
        String tipos = tgTipo.getSelectedToggle().getUserData().toString();
        cbTipoCoche.getItems().clear();
        cbTipoMoto.getItems().clear();
        switch (tipos) {
            case "coche":
                insertarCamposCB("coche");
                break;

            case "moto":
                insertarCamposCB("moto");
                break;
        }
        tTipo.setVisible(true);
    }

    public void insertarCamposCB(String tipoV) {
        switch (tipoV) {
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

    public void insertarCampo(List<String> campos, String campo) {
        campos.add(campo);
    }
}
