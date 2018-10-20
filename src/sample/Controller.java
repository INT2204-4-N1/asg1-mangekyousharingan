package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    private RadioMenuItem initAV;
    @FXML
    private RadioMenuItem initVA;
    @FXML
    private Button searchButton;    //bt_tr
    @FXML
    private ToggleGroup tog_gr;
    @FXML
    private Label currDict;
    @FXML
    private Label currWord;
    @FXML
    private TextField searchText;
    @FXML
    private WebView webView;
    private WebEngine webEngine;

    private boolean AnhDict = true;
    public static Map<String,String> dataEV;
    public static ArrayList<String> wordTargetEV;
    public static Map<String,String> dataVE;
    public static ArrayList<String> wordTargetVE;

    public  static  void loadDict(String path){
        dataEV = new HashMap<>();
        wordTargetEV = new ArrayList<>();
        String line, word, def;
        if (path == "E_V") {
            path = "src\\sample\\E_V.txt";
        }
        else{
            path = "src\\sample\\V_E.txt";
        }
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path), "UTF8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            while ((line = reader.readLine()) != null) {
                int index = line.indexOf("<html>");
                int index2 = line.indexOf("<ul>");
                if (index2 != -1 && index > index2) {
                    index = index2;
                }
                if (index != -1) {
                    word = line.substring(0, index);
                    word = word.trim();             // word là từ trên dòng thứ index
                    def = line.substring(index);    // Phần nghĩa (từ <html> đến <ul><html>)
                    dataEV.put(word, def);            // Thêm từ và nghĩa vào data
                    wordTargetEV.add(word);                // thêm từ vào Words
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AutoCompletionBinding autoCompletionBinding;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDict("E_V");
    //    loadDict("V_E");
    //    dictEV.readfile();
    //    dictVE.readfile();
    //    autoCompletionBinding = TextFields.bindAutoCompletion(searchText,wordTargetEV);
    //    initAV.setUserData("av");
    //    initVA.setUserData("va");
    //    initAV.setSelected(true);


    //    searchButton.setDisable(true);

    //    tog_gr.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
    //        @Override
    //        public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
    //            if (tog_gr.getSelectedToggle()!=null){
    //                if (tog_gr.selectToggle().)
    //            }
    //        }
    //    });
    }

    public int SearchWord1(String w, ArrayList<String> words) {
        if (words.get(0).compareTo(w) >= 0) return 0;       // So sánh bằng compareTo - từ điển
        int start = 0, end = words.size();
        while (start < end - 1) {
            int temp = (start + end) / 2;
            if (words.get(temp).compareTo(w) < 0) start = temp;       // So sánh bằng compareTo - từ điển
            else end = temp;
        }
        return end;
    }

    public void translate (String curWord){
        try {
            currWord.setText(curWord);
            int i = SearchWord1(curWord, wordTargetEV);
            System.out.println(wordTargetEV.get(1000));
            System.out.println(i);
            String curMean = (dataEV.get(curWord));
            System.out.println(curMean);
            webEngine = webView.getEngine();
            webEngine.loadContent(curMean);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void onClinkSearch(){
        //webEngine = webView.getEngine();
        //webEngine.loadContent("Hahahaha");
        try {
            String word = searchButton.getText();
            word.trim();
            word = word.toLowerCase();
            translate(word);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
