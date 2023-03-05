package Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.ResourceBundle;


public class MonedaController extends CambioDeVistasController implements Initializable {
    public Button btnConvertir;
    Alert alert = new Alert(Alert.AlertType.WARNING);
    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
    Alert alert3 = new Alert(Alert.AlertType.ERROR);

    @FXML
    private ComboBox<String> cbMoneda1;
    @FXML
    private ComboBox<String> cbMoneda2;
    @FXML
    private Button btnVolver;
    @FXML
    private TextField txtMoneda1;

    @FXML
    private Label lblResultado;

    @FXML
    void onConvertir(ActionEvent event) {
        try {
            if(!txtMoneda1.getText().equals("")) {
                //regex para numero reales: ^-?[0-9]+([\\.,][0-9]+)?$
                if (!txtMoneda1.getText().matches("^?[0-9]+([\\.,][0-9]+)?$")) {
                    alert.setTitle("Error de conversión");
                    alert.setHeaderText("Tiene que ingresar solo valores positivos!");
                    alert.showAndWait();
                }else {
                    float importe = Float.parseFloat(txtMoneda1.getText());
                    String divisa1 = cbMoneda1.getSelectionModel().getSelectedItem().substring(0,3);
                    String divisa2 = cbMoneda2.getSelectionModel().getSelectedItem().substring(0,3);
                    float resultado = getCambio(divisa1,divisa2, importe);

                    lblResultado.setText(String.format("%.2f %s <> %.2f %s", importe,divisa1,resultado,divisa2));
                }
            }else {
                alert2.setTitle("Importe Vacío");
                alert2.setHeaderText("Por favor ingrese el importe!");
                alert2.showAndWait();
            }
        }catch (Exception e){
            alert3.setTitle("Error de sistema");
            alert3.setHeaderText("Por favor inténtelo más tarde!");
            alert3.showAndWait();
        }

    }
    
    @FXML
    void OnVolver(ActionEvent event) throws IOException {
        cambioDeScenas("menuprincipal",btnVolver);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbMoneda1.getItems().addAll(monedas);
        cbMoneda2.getItems().addAll(monedas);


    }

    private float getCambio(String from, String to, float amount) throws IOException {

        String urlDivisa = "https://api.apilayer.com/fixer/convert?from=";
        String encodedQuery = URLEncoder.encode(String.format("%s&to=%s&amount=%.2f", from, to, amount), StandardCharsets.UTF_8.toString());
        String encodedUrl = urlDivisa + encodedQuery;
        String url = encodedUrl.replace("%3D","=").replace("%26","&").replace("+","");
        Properties properties = new Properties();
        try {
            FileInputStream ip = new FileInputStream("src/main/resources/config.properties");
            properties.load(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("apikey", properties.getProperty("key"))
                .build();

        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();

        JSONObject json = new JSONObject(jsonData);

        return Float.parseFloat(json.get("result").toString());

    }
    String [] monedas = {
            "AED - Dírham de los Emiratos Árabes Unidos",
            "AFN - afgano afgano",
            "ALL - lago albanés",
            "AMD - Dram armenio",
            "ANG - Gremio de las Antillas Neerlandesas",
            "AOA - Kwanza angoleño",
            "ARS - pesos argentinos",
            "AUD - Dólar australiano",
            "AWG - florín arubeño",
            "AZN - Manat de Azerbaiyán",
            "BAM - Marco bosnioherzegovino",
            "BBD - Dólar de Barbados",
            "BDT - Taka de Bangladesh",
            "BGN - Lev búlgaro",
            "BHD - dinar bahreiní",
            "BIF - Franco de Burundi",
            "BMD - dólar bermudeño",
            "BND - Dólar de Brunéi",
            "BOB - boliviano pais boliviano",
            "BRL - Real brasileño",
            "BSD - dólar bahameño",
            "BTC - Bitcoin",
            "BTN - idioma butanés",
            "BWP - Lluvia de Botswana",
            "BYR - rublo bielorruso",
            "BYN - nuevo rublo bielorruso",
            "BZD - Dólar de Belice",
            "CAD - Dolar canadiense",
            "CDF - franco congoleño",
            "CHF - Franco suizo",
            "CLF - Unidad Chilena de Cuenta (UF)",
            "CLP - peso chileno",
            "CNY - yuan chino",
            "COP - peso colombiano",
            "CRC - colón costarricense",
            "CUC - Peso Convertible Cubano",
            "CUP - peso cubano",
            "CVE - Escudo caboverdiano",
            "CZK - Corona de la República Checa",
            "DJF - franco yibutiano",
            "DKK - corona danesa",
            "DOP - peso dominicano",
            "DZD - dinar argelino",
            "EGP - Libra egipcia",
            "ERN - Nakfa de Eritrea",
            "ETB - Birr etíope",
            "EUR - Euro",
            "FJD - dólar fiyiano",
            "FKP - Libra malvinense",
            "GBP - Libra esterlina",
            "GEL - Lari georgiano",
            "GGP - Libra de Guernsey",
            "GHS - Cedi ghanés",
            "GIP - Libra gibraltareña",
            "GMD - Dalasi de Gambia",
            "GNF - franco guineano",
            "GTQ - Quetzal guatemalteco",
            "GYD - dólar guyanés",
            "HKD - Dolar de Hong Kong",
            "HNL - Lempira hondureño",
            "HRK - Moneda croata",
            "HTG - Gourde haitiano",
            "HUF - florín húngaro",
            "IDR - En rupia indonesia",
            "ILS - Nuevo séquel israelí",
            "IMP - Libra manesa",
            "INR - Rupia india",
            "IQD - dinar iraquí",
            "IRR - rial iraní",
            "ISK - corona islandesa",
            "JEP - Libra de Jersey",
            "JMD - dólar jamaiquino",
            "JOD - dinar jordano",
            "JPY - Yen japonés",
            "KES - chelín keniano",
            "KGS - Kirguistán Som",
            "KHR - Ferrocarril camboyano",
            "KMF - franco comorano",
            "KPW - Won norcoreano",
            "KRW - Won surcoreano",
            "KWD - dinar kuwaití",
            "KYD - Dólar de las Islas Caimán",
            "KZT - Tenge kazajo",
            "LAK - Torreón de Laos",
            "LBP - Libra libanesa",
            "LKR - Rupia de Sri Lanka",
            "LRD - dólar liberiano",
            "LSL - lesoto loti",
            "LTL - Litas lituana",
            "LVL - Lat letón",
            "LYD - dinar libio",
            "MAD - Dírham marroquí",
            "MDL - Leu moldavo",
            "MGA - Ariary malgache",
            "MKD - Denar macedonio",
            "MMK - Kyat birmano",
            "MNT - Tugrik mongol",
            "MOP - Paquete macaense",
            "MRO - Uguiya mauritana",
            "MUR - Rupia de Mauricio",
            "MVR - rufián maldivo",
            "MWK - Moneda de Malawi",
            "MXN - peso mexicano",
            "MYR - Ringgit malayo",
            "MZN - Metical mozambiqueño",
            "NAD - dólar namibio",
            "NGN - naira nigeriana",
            "NIO - Nicaragua Córdoba",
            "NOK - corona noruega",
            "NPR - rupia nepalí",
            "NZD - Dolar de Nueva Zelanda",
            "OMR - Rial omaní",
            "PAB - balboa panameño",
            "PEN - Nuevo sol peruano",
            "PGK - Papúa Nueva Guinea China",
            "PHP - peso filipino",
            "PKR - Rupias paquistaníes",
            "PLN - Zloty polaco",
            "PYG - guaraní paraguayo",
            "QAR - Rial qatarí",
            "RON - leu rumano",
            "RSD - dinar serbio",
            "RUB - Rublo ruso",
            "RWF - franco ruandés",
            "SAR - rial saudí",
            "SBD - Dólar de las Islas Salomón",
            "SCR - Rupia de Seychelles",
            "SDG - Libra sudanesa",
            "SEK - Corona sueca",
            "SGD - Dolar de Singapur",
            "SHP - Libra de Santa Elena",
            "SLL - Sierra Leona Leona",
            "SOS - chelín somalí",
            "SRD - dólar surinamés",
            "STD - São Tomé y Príncipe Dobra",
            "SVC - colón salvadoreño",
            "SYP - Libra siria",
            "SZL - Suazilandia Lilangeni",
            "THB - Baht tailandés",
            "TJS - Tayikistán Somoni",
            "TMT - Manat de Turkmenistán",
            "TND - dinar tunecino",
            "TOP - dólar tongano",
            "TRY - Lira turca",
            "TTD - Dólar de Trinidad y Tobago",
            "TWD - Nuevo dólar taiwanés",
            "TZS - chelín tanzano",
            "UAH - Grivna ucraniana",
            "UGX - Chelín ugandés",
            "USD - Dólar de los Estados Unidos",
            "UYU - peso uruguayo",
            "UZS - Uzbekistán lunes",
            "VEF - Fuerte de Bolívar venezolano",
            "VND - Dong vietnamita",
            "VUV - Gente de Vanuatu",
            "WST - Vista de Samoa",
            "XAF - Franco CFA BEAC",
            "XAG - Plata (onza troy)",
            "XAU - Oro (onza troy)",
            "XCD - Dólar del Caribe Oriental",
            "XDR - Derechos especiales de dibujo",
            "XOF - Franco CFA BCEAO",
            "XPF - Franco CFP",
            "YER - rial yemení",
            "ZAR - Rand sudafricano",
            "ZMW - Moneda de Zambia",
            "ZWL - dólar zimbabuense",

    };
}
