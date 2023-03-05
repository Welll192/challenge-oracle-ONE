package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MasaController extends CambioDeVistasController implements Initializable {

    public Button btnConvertir;
    Alert alert = new Alert(Alert.AlertType.WARNING);
    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
    Alert alert3 = new Alert(Alert.AlertType.ERROR);


    @FXML
    private ComboBox<String> cbMasa1;

    @FXML
    private ComboBox<String> cbMasa2;
    @FXML
    private Button btnVolver;

    @FXML
    private Label lblResultado;

    @FXML
    private TextField txtMasa;

    @FXML
    void onConvertir(ActionEvent event) {

        try {
            if(!txtMasa.getText().equals("")){
                if (!txtMasa.getText().matches("^?[0-9]+([\\.,][0-9]+)?$")) {
                    alert.setTitle("Error de conversión");
                    alert.setHeaderText("Tiene que ingresar solo valores positivos!");
                    alert.showAndWait();
                }else{
                    String primerSimbolo = cbMasa1.getSelectionModel().getSelectedItem().substring(0,3).replaceAll("[^\\dA-Za-z]", "");
                    String segundoSimbolo = cbMasa2.getSelectionModel().getSelectedItem().substring(0,3).replaceAll("[^\\dA-Za-z]", "");

                    float cantidadMasa = Float.parseFloat(txtMasa.getText());
                    float resultado = getCambio(primerSimbolo,segundoSimbolo,cantidadMasa);

                    lblResultado.setText(String.format(" %.4f %s    <>    %.4f %s ",cantidadMasa,primerSimbolo,resultado,segundoSimbolo));
                }
            }else {
                alert2.setTitle("Masa no dada");
                alert2.setHeaderText("Por favor ingrese la Masa!");
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

    private final String[] masas ={
            "T  - Tonelada",
            "Kg - Kilogramo",
            "g  - Gramo",
            "oz - Onza",
            "lb - Libra",
    };
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbMasa1.getItems().addAll(masas);
        cbMasa2.getItems().addAll(masas);
    }

    private float getCambio(String from, String to, float cantidadMasa){

        float resultado=0f;
        switch (from) {
            case "T" -> {
                if (to.equals("T")) {
                    resultado = cantidadMasa;
                }
                if (to.equals("Kg")) {
                    resultado = 1000 * cantidadMasa;
                }
                if (to.equals("g")) {
                    resultado = 1000000 * cantidadMasa;
                }
                if (to.equals("oz")) {
                    resultado = 35274 * cantidadMasa;
                }
                if (to.equals("lb")) {
                    resultado = 2204.62f * cantidadMasa;
                }
            }
            case "Kg" -> {
                if (to.equals("T")) {
                    resultado = 0.001f * cantidadMasa;
                }
                if (to.equals("Kg")) {
                    resultado = cantidadMasa;
                }
                if (to.equals("g")) {
                    resultado = 1000 * cantidadMasa;
                }
                if (to.equals("oz")) {
                    resultado = 35.274f * cantidadMasa;
                }
                if (to.equals("lb")) {
                    resultado = 2.20462f * cantidadMasa;
                }
            }
            case "g" -> {
                if (to.equals("T")) {
                    resultado = cantidadMasa / 1000000;
                }
                if (to.equals("Kg")) {
                    resultado = 0.001f * cantidadMasa;
                }
                if (to.equals("g")) {
                    resultado = cantidadMasa;
                }
                if (to.equals("oz")) {
                    resultado = cantidadMasa / 28.35f;
                }
                if (to.equals("lb")) {
                    resultado = cantidadMasa / 453.6f;
                }
            }
            case "oz" -> {
                if (to.equals("T")) {
                    resultado = cantidadMasa / 35270;
                }
                if (to.equals("Kg")) {
                    resultado = cantidadMasa / 35.274f;
                }
                if (to.equals("g")) {
                    resultado = cantidadMasa * 28.35f;
                }
                if (to.equals("oz")) {
                    resultado = cantidadMasa;
                }
                if (to.equals("lb")) {
                    resultado = cantidadMasa / 16;
                }
            }
            case "lb" -> {
                if (to.equals("T")) {
                    resultado = cantidadMasa / 2205;
                }
                if (to.equals("Kg")) {
                    resultado = cantidadMasa / 2.205f;
                }
                if (to.equals("g")) {
                    resultado = cantidadMasa * 453.6f;
                }
                if (to.equals("oz")) {
                    resultado = cantidadMasa * 16;
                }
                if (to.equals("lb")) {
                    resultado = cantidadMasa;
                }
            }
        }
     return resultado;
    }

}
