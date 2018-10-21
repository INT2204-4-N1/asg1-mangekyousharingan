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
import java.util.*;


public class Controller implements Initializable {
    @FXML
    private RadioMenuItem initAV;
    @FXML
    private RadioMenuItem initVA;
    @FXML
    private ToggleGroup tg_gr;
    @FXML
    private Button searchButton;
    @FXML
    private Label currDictLabel;
    @FXML
    private Label currWordLabel;
    @FXML
    private TextField searchText;
    @FXML
    private WebView webView;
    private WebEngine webEngine;

    public boolean AnhDict = true;
    public static Map<String,String> dataEV = new HashMap<>();
    public static ArrayList<String> wordTargetEV = new ArrayList<>();
    public static Map<String,String> dataVE = new HashMap<>();
    public static ArrayList<String> wordTargetVE = new ArrayList<>();
    public static Map<String,String> currData = new HashMap<>();
    public static ArrayList<String> currWordTarget = new ArrayList<>();

    /**
     * Đọc file từ điển Anh - Việt hoặc Việt - Anh
     * @param path - dữ liệu truyền vào là "A_V"(Anh - Việt) hoặc "V_A"(Việt - Anh)
     */
    public  static  void loadDict(String path){
        if (path == "E_V") {
            path = "src\\sample\\E_V.txt";
        }
        else{
            path = "src\\sample\\V_E.txt";
        }

        // Đọc file từ điển Anh - Việt
        if (path.equalsIgnoreCase("src\\sample\\E_V.txt")){
            String line, word, def;
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
                        word = word.trim();                 // word là từ trên dòng thứ index
                        def = line.substring(index);        // Phần nghĩa (từ <html> đến <ul><html>)
                        dataEV.put(word, def);              // Thêm từ và nghĩa vào data
                        wordTargetEV.add(word);             // thêm từ vào Words
                    }
                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            Collections.sort(wordTargetEV);
        }

        // Đọc file từ điển Việt - Anh
        if (path.equalsIgnoreCase("src\\sample\\V_E.txt")){
            String line,word,def;
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
                        word = word.trim();                 // word là từ trên dòng thứ index
                        def = line.substring(index);        // Phần nghĩa (từ <html> đến <ul><html>)
                        dataVE.put(word, def);              // Thêm từ và nghĩa vào data
                        wordTargetVE.add(word);             // thêm từ vào Words
                    }
                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            Collections.sort(wordTargetVE);
        }
    }

    public void updateDict(String path) {
        if (path == "E_V") {
            path = "src\\sample\\E_V.txt";
        }
        else{
            path = "src\\sample\\V_E.txt";
        }

        // Ghi file từ điển Anh - Việt
        if (path.equalsIgnoreCase("src\\sample\\E_V.txt")){
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "UTF8");
                BufferedWriter writer = new BufferedWriter(outputStreamWriter);
                for (String word : wordTargetEV ) {
                    writer.write(word);
                    String def = dataEV.get(word);
                    if (def != null) {
                        writer.write(dataEV.get(word));
                    }
                    writer.newLine();
                }
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            Collections.sort(wordTargetEV);
        }
        // Ghi file từ điển Việt - Anh
        if (path.equalsIgnoreCase("src\\sample\\V_E.txt")){
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "UTF8");
                BufferedWriter writer = new BufferedWriter(outputStreamWriter);
                for (String word : wordTargetVE ) {
                    writer.write(word);
                    String def = dataVE.get(word);
                    if (def != null) {
                        writer.write(dataVE.get(word));
                    }
                    writer.newLine();
                }
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            }
            Collections.sort(wordTargetVE);
        }

    }
    public void onClickAV(){
        currData = dataEV;
        currWordTarget = wordTargetEV;
        currDictLabel.setText("Anh - Việt");
    }

    public void onClickVA(){
        currData = dataVE;
        currWordTarget = wordTargetVE;
        currDictLabel.setText("Việt - Anh");
    }

    public AutoCompletionBinding autoCompletionBinding;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDict("V_E");
        loadDict("E_V");
        currDictLabel.setText("Anh - Việt");
        onClickAV();
        initAV.setUserData("av");
        initVA.setUserData("va");
        initAV.setSelected(true);
        autoCompletionBinding = TextFields.bindAutoCompletion(searchText,dataEV.keySet());

        // Code menu trong Từ điển
        tg_gr.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (tg_gr.getSelectedToggle()!=null){
                    if (tg_gr.getSelectedToggle().getUserData().toString().compareTo("av") == 0){
                        AnhDict = true;
                        onClickAV();
                        autoCompletionBinding.dispose();
                        autoCompletionBinding = TextFields.bindAutoCompletion(searchText,dataEV.keySet());
                    }
                    else {
                        AnhDict = false;
                        onClickVA();
                        autoCompletionBinding.dispose();
                        autoCompletionBinding = TextFields.bindAutoCompletion(searchText,dataVE.keySet());
                    }
                }
            }
        });
    }

    public int searchWord(String w, ArrayList<String> words) {
        if (words.get(0).compareTo(w) >= 0) {           // So sánh bằng compareTo - từ điển
            return 0;
        }
        int start = 0;
        int end = words.size();
        while (start < end - 1) {
            int temp = (start + end) / 2;
            if (words.get(temp).compareTo(w) < 0) {     // So sánh bằng compareTo - từ điển
                start = temp;
            }
            else {
                end = temp;
            }
        }
        return end;
    }

    public void onClickRemove(){
        String word = searchText.getText();
        removeWord(word);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn đã xóa bỏ thành công");
        alert.setContentText("Từ được xóa bỏ là "+word);
        alert.show();
    }

    public void translate (String curWord){
        try {
            String wordLabel = curWord;
            wordLabel = wordLabel.toUpperCase();
            //System.out.println(wordLabel);                // test
            //System.out.println(wordTargetVE.get(0));      // test
            currWordLabel.setText(wordLabel);
            int i = searchWord(curWord, currWordTarget);
            //System.out.println(wordTargetEV.get(0));        // test
            //System.out.println(i);                        // test
            String curMean = (currData.get(curWord));
            //System.out.println(curMean);                  // test
            webEngine = webView.getEngine();
            webEngine.loadContent(curMean);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onClickSearch(){
        try {
            String word = searchText.getText();
            //word.trim();
            //word = word.toLowerCase();
            //System.out.println(word);                     // test
            translate(word);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onClickTacGia(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Tác giả");
        alert.setHeaderText("Cảm ơn bạn đã sử dụng phần mềm!");
        alert.setContentText("MangekyouSharingan Team (From Uchiha)\n"+
                "Member:\n"+
                "\t1. Cao Quý Đăng\t\tMSSV: 17020680\n"+
                "\t2. Nguyễn Xuân Hiển\tMSSV: 17020730\n"+
                "\tLiên hệ: athenaQK@gmail.com");
        alert.show();
    }
    public void removeWord(String word){
        int position = searchWord(word,currWordTarget);
        if (position>=0 && position<=currWordTarget.size()-1){
            currWordTarget.remove(position);
            currData.remove(word);
        }
        if (AnhDict == true){
            wordTargetEV = currWordTarget;
            dataEV = currData;
            updateDict("E_V");
        }
        else{
            wordTargetVE = currWordTarget;
            dataVE = currData;
            updateDict("V_E");
        }
    }

}
