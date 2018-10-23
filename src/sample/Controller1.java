package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.Collections;

public class Controller1 {
    @FXML
    private TextField wordText;
    @FXML
    private TextField typeText;
    @FXML
    private TextField meanText;

    /**
     * Hàm bắt sự kiện của chức năng thêm từ
     */
    public void onClickThem(){
        Controller action = new Controller();
        String str1 = wordText.getText();
        //System.out.println(Controller.currWordTarget.size());
        String str2 = typeText.getText();
        String str3 = meanText.getText();
        //String mean = "<html></i><br/><ul><li><b><i> "+str2+"</i></b><ul><li><font color='#cc0000'><b> "+str3;
        String mean = "<html><ul><li><b><i> "+str2+"</i></b><ul><li><font color='#cc0000'><b> "+str3;
        int index = action.searchWord(str1,Controller.currWordTarget);
        if (Controller.currWordTarget.get(index).equals(str1) == true){
            Controller.currWordTarget.add(index,str1);
            Controller.currData.put(str1,mean);
            Alert alert = new Alert((Alert.AlertType.CONFIRMATION));
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn đã thêm thành công!");
            alert.setContentText("Bạn đã sửa nghĩa từ "+str1+" trong từ điển.");
            alert.show();
        }
        else{
            Controller.currWordTarget.add(str1);
            Controller.currData.put(str1,mean);
            Collections.sort(Controller.currWordTarget);
            Alert alert = new Alert((Alert.AlertType.CONFIRMATION));
            alert.setTitle("Thông báo");
            alert.setHeaderText("Bạn đã thêm thành công!");
            alert.setContentText("Bạn đã thêm thành công từ "+str1+" vào trong từ điển.");
            alert.show();
        }
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
        //Controller.stageAdd.close();
    }

    /**
     * Hàm bắt sự kiện của Button "Hủy" trong cửa sổ thêm từ
     */
    public void onClickHuy(){
        Controller.stageAdd.close();
    }
}
