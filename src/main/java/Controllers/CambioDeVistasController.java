package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CambioDeVistasController {
    public void cambioDeScenas(String nombreEscena, Button btn) throws IOException {
        String urlPersonalizada = String.format("/views/%s.fxml",nombreEscena);
        FXMLLoader loader =new FXMLLoader(getClass().getResource(urlPersonalizada));
        Parent root = loader.load();
        Scene scene = new Scene(root, 580, 350);
        Stage stage = new Stage();
        stage.setTitle("Challenge-one conversor!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        Stage myStage = (Stage) btn.getScene().getWindow();
        myStage.close();
    }
}
