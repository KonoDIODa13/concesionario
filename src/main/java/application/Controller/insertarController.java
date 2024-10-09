package application.Controller;

import application.DAO.VehiculoDAO;
import application.Domain.Coche;
import application.Utils.AlertUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

import static application.Utils.CambioEscenas.cambioEscena;

public class insertarController extends SuperController {

    public AnchorPane rootPane;
    @FXML
    TextField matriculaText, marcaText, modeloText, precioText, cargaText, plazasText, tipoText;


   /* @FXML
    public void buscarMatricula(ActionEvent event) throws SQLException {
        vehiculoCRUD.buscarMatricula();
    }*/

    @FXML
    public void insertarCoche(ActionEvent event) throws SQLException {
        String matricula = matriculaText.getText();
        String marca = marcaText.getText();
        String modelo = modeloText.getText();
        double precio = Double.parseDouble(precioText.getText());
        String carga = cargaText.getText();
        int plazas = Integer.parseInt(plazasText.getText());
        String tipo = tipoText.getText();
        Coche coche = new Coche(matricula, marca, modelo, precio, carga, plazas, tipo);
        if (vehiculoCRUD.insertarCoche(coche)) {
            AlertUtils.mostrarError("vehiculo creado");
        } else {
            AlertUtils.mostrarError("error al insertar");
        }


    }

    public void atras(ActionEvent event) {
        cambioEscena("inicio.fxml", rootPane);
    }
}
