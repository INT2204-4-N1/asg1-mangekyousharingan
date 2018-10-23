package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class GoogleAPIController implements Initializable {
    @FXML
    private WebView webGoogle;
    private WebEngine engineGoogle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String url = "https://translate.google.com/?hl=vi";
        engineGoogle = webGoogle.getEngine();
        engineGoogle.load(url);
    }
}
