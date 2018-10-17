package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Label currWord;
    @FXML
    private RadioMenuItem initAV;
    @FXML
    private RadioMenuItem initVA;

    private HashMap<String,String> data = new HashMap<>();
    private ArrayList<String> wordTarget = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
