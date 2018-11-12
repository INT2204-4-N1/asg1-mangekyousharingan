package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class CambridgeController implements Initializable {
    @FXML
    private WebView webCambridge;
    private WebEngine engineCambridge;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String url = "https://dictionary.cambridge.org/vi/dictionary/";
        engineCambridge = webCambridge.getEngine();
        engineCambridge.load(url);
    }
}
