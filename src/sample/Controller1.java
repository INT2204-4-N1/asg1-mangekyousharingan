package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Controller1 {
    @FXML
    private TextField wordText;
    @FXML
    private TextField typeText;
    @FXML
    private TextField meanText;

    public void onClickThem(){
        Controller action = new Controller();
        String str1 = wordText.getText();
        System.out.println(str1);
        String str2 = typeText.getText();
        String str3 = meanText.getText();
        //String mean = "<html></i><br/><ul><li><b><i> "+str2+"</i></b><ul><li><font color='#cc0000'><b> "+str3;
        String mean = "<html><ul><li><b><i> "+str2+"</i></b><ul><li><font color='#cc0000'><b> "+str3;
        Controller.currWordTarget.add(str1);
        Controller.currData.put(str1,mean);
        if (Controller.AnhDict == true){
            Controller.dataEV = Controller.currData;
            Controller.wordTargetEV = Controller.currWordTarget;
            action.updateDict("E_V");
            //action.loadDict("E_V");
            //action.setAutoCompleteUpdate();
        }
        else{
            Controller.dataVE = Controller.currData;
            Controller.wordTargetVE = Controller.currWordTarget;
            action.updateDict("V_E");
            //action.loadDict("V_E");
            //action.setAutoCompleteUpdate();
        }
        Alert alert = new Alert((Alert.AlertType.CONFIRMATION));
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn đã thêm thành công!");
        alert.setContentText("Bạn đã thêm thành công từ "+str1+" vào trong từ điển.");
        alert.show();
    }

    public void onClickHuy(){
        Controller.stageAdd.close();
    }
}
