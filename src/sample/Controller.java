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

    private boolean AnhDict = true;
    public static Map<String,String> dataEV;
    public static ArrayList<String> wordTargetEV;
    public static Map<String,String> dataVE;
    public static ArrayList<String> wordTargetVE;
    public static Map<String,String> currData = new HashMap<>();
    public static ArrayList<String> currWordTarget = new ArrayList<>();

    /**
     * Đọc file từ điển Anh - Việt hoặc Việt - Anh
     * @param path - dữ liệu truyền vào là "A_V"(Anh - Việt) hoặc "V_A"(Việt - Anh)
     */
    public  static  void loadDict(String path){
        dataEV = new HashMap<>();
        wordTargetEV = new ArrayList<>();
        dataVE = new HashMap<>();
        wordTargetVE = new ArrayList<>();
        //String line, word, def;

        if (path == "E_V") {
            path = "src\\sample\\E_V.txt";
        }
        else{
            path = "src\\sample\\V_E.txt";
        }

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

        /*
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
        Collections.sort(wordTargetEV);
        */
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
    //    currData = dataEV;
    //    currWordTarget = wordTargetEV;
    //    currDictLabel.setText("Anh - Việt");
        onClickAV();
        initAV.setSelected(true);
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

    public int searchWord1(String curWord, ArrayList<String> words) {
        if (words.get(0).compareTo(curWord)>=0){
            return 0;
        }
        int left = 0;
        int right = words.size();
        while (left<right-1){
            int mid = left +(right - left)/2;
            if (words.get(mid).compareTo(curWord)<0){
                left=mid;
            }
            else{
                right=mid;
            }
        }
        return right;
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

    public void translate (String curWord){
        try {
            currWordLabel.setText(curWord);
            int i = searchWord(curWord, currWordTarget);
            System.out.println(wordTargetEV.get(1000));
            System.out.println(i);
            String curMean = (currData.get(curWord));
            System.out.println(curMean);
            webEngine = webView.getEngine();
            webEngine.loadContent(curMean);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onClickSearch(){
        try {
            String word = searchText.getText();
            word.trim();
            word = word.toLowerCase();
            System.out.println(word);
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
                "\t1. Cao Quý Đăng\tMSSV: 17020680\n"+
                "\t2. Nguyễn Xuân Hiển\tMSSV: \n"+
                "Liên hệ: athenaQK@gmail.com");
        alert.show();
    }

}
