package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuPrincipalController extends CambioDeVistasController implements Initializable {


    @FXML
    private Button btnSiguiente;
    @FXML
    private ComboBox<String> combobox;

    private final String[] items = {"Conversor de Moneda","Conversor de Temperatura","Conversor de Masa"};

    @FXML
    void OnSiguiente(ActionEvent event) throws IOException {

        switch (combobox.getSelectionModel().getSelectedIndex()) {
            case 0 -> cambioDeScenas("cambiodemoneda", btnSiguiente);
            case 1 -> cambioDeScenas("cambiodetemperatura", btnSiguiente);
            case 2 -> cambioDeScenas("cambiodemasa",btnSiguiente);

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        combobox.getItems().addAll(items);

        /*
        combobox.setOnAction(e->{
            String data = combobox.getSelectionModel().getSelectedItem();
            System.out.println(data);
        });
        */
    }

}