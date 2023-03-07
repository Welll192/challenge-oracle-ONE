package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TemperaturaController extends CambioDeVistasController implements Initializable {

    public Button btnConvertir;
    Alert alert = new Alert(Alert.AlertType.WARNING);
    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
    Alert alert3 = new Alert(Alert.AlertType.ERROR);


    @FXML
    private ComboBox<String> cbTemperatura1;

    @FXML
    private ComboBox<String> cbTemperatura2;
    @FXML
    private Button btnVolver;


    @FXML
    private Label lblResultado;

    @FXML
    private TextField txtTemperatura;

    @FXML
    void onConvertir(ActionEvent event) {


        try {
            if(!txtTemperatura.getText().equals("")){
                if (!txtTemperatura.getText().matches("^-?[0-9]+([\\.,][0-9]+)?$")) {
                    alert.setTitle("Error de conversión");
                    alert.setHeaderText("Tiene que ingresar solo valores numéricos!");
                    alert.showAndWait();
                }else{

                    String primerSimbolo = cbTemperatura1.getSelectionModel().getSelectedItem().substring(1,3).replaceAll("[^\\dA-Za-z]", "");
                    String segundoSimbolo = cbTemperatura2.getSelectionModel().getSelectedItem().substring(1,3).replaceAll("[^\\dA-Za-z]", "");

                    float cantidadTemperatura = Float.parseFloat(txtTemperatura.getText());
                    float resultado = getCambio(primerSimbolo,segundoSimbolo,cantidadTemperatura);

                    lblResultado.setText(String.format(" %.2f °%s    <>    %.2f °%s ",cantidadTemperatura,primerSimbolo,resultado,segundoSimbolo));
                }
            }else {
                alert2.setTitle("Temperatura no dada");
                alert2.setHeaderText("Por favor ingrese la Temperatura!");
                alert2.showAndWait();
            }
        }catch (Exception e){
            alert3.setTitle("Error de sistema");
            alert3.setHeaderText("Por favor inténtelo más tarde!");
            alert3.showAndWait();
        }

    }

    @FXML
    void onVolver(ActionEvent event) throws IOException {
        cambioDeScenas("menuprincipal",btnVolver);
    }

    private final String[] temperaturas ={
            "° C - Celsius",
            "° F - Fahrenheit",
            "  K - Kelvin",
            "° R - Rankine",
            "°Re - Réaumur"
    };
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            cbTemperatura1.getItems().addAll(temperaturas);
            cbTemperatura2.getItems().addAll(temperaturas);
    }

    private float getCambio(String from, String to, float cantidadTemperatura){

        float resultado=0f;
        switch (from) {
            case "C" -> {
                if (to.equals("C")) {
                    resultado = cantidadTemperatura;
                }
                if (to.equals("F")) {
                    resultado = 9 * cantidadTemperatura / 5 + 32;
                }
                if (to.equals("K")) {
                    resultado = cantidadTemperatura + 273.15f;
                }
                if (to.equals("R")) {
                    resultado = 9 * cantidadTemperatura / 5 + 491.67f;
                }
                if (to.equals("Re")) {
                    resultado = cantidadTemperatura * 0.8f;
                }
            }
            case "F" -> {
                if (to.equals("C")) {
                    resultado = (cantidadTemperatura - 32) * 5 / 9;
                }
                if (to.equals("F")) {
                    resultado = cantidadTemperatura;
                }
                if (to.equals("K")) {
                    resultado = (cantidadTemperatura - 32) * 5 / 9 + 273.15f;
                }
                if (to.equals("R")) {
                    resultado = cantidadTemperatura + 459.67f;
                }
                if (to.equals("Re")) {
                    resultado = (cantidadTemperatura - 32) * 4 / 9;
                }
            }
            case "K" -> {
                if (to.equals("C")) {
                    resultado = cantidadTemperatura - 273.15f;
                }
                if (to.equals("F")) {
                    resultado = (cantidadTemperatura - 273.15f) * 9 / 5 + 32;
                }
                if (to.equals("K")) {
                    resultado = cantidadTemperatura;
                }
                if (to.equals("R")) {
                    resultado = cantidadTemperatura * 1.8f;
                }
                if (to.equals("Re")) {
                    resultado = 4 * (cantidadTemperatura - 273.15f) / 5;
                }
            }
            case "R" -> {
                if (to.equals("C")) {
                    resultado = 5 * (cantidadTemperatura - 491.67f) / 9;
                }
                if (to.equals("F")) {
                    resultado = cantidadTemperatura - 459.67f;
                }
                if (to.equals("K")) {
                    resultado = cantidadTemperatura / 1.8f;
                }
                if (to.equals("R")) {
                    resultado = cantidadTemperatura;
                }
                if (to.equals("Re")) {
                    resultado = 4 * (cantidadTemperatura - 491.67f) / 9;
                }
            }
            case "Re" -> {
                if (to.equals("C")) {
                    resultado = cantidadTemperatura / 0.8f;
                }
                if (to.equals("F")) {
                    resultado = (cantidadTemperatura * 9 / 4) + 32;
                }
                if (to.equals("K")) {
                    resultado = (cantidadTemperatura * 5 / 4) + 273.15f;
                }
                if (to.equals("R")) {
                    resultado = (cantidadTemperatura * 9 / 4) + 491.67f;
                }
                if (to.equals("Re")) {
                    resultado = cantidadTemperatura;
                }
            }
        }
     return resultado;
    }

}
