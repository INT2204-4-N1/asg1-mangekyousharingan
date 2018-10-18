package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Dictionary {
    String name; // từ điển anh việt hay việt anh ?
    String path; // Đường dẫn đến từ điển
    HashMap<String,String> data;
    ArrayList<String> Words;
    /**
     * Getter, setter
     * Event: chọn từ điển nào thì khởi tạo lại Dictionary mới
     */
    public void Dictionary(String Name, String Path) {
        this.name = Name;
        this.path = Path;
        Words = new ArrayList<>();
        data = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    /**
     * Đọc file
     * trả về arraylist Words
     * Words chứa các từ của từ điển, không chứa nghĩa
     */
    public ArrayList<String> readfile() {
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
                    word = word.trim();             // word là từ trên dòng thứ index
                    def = line.substring(index);    // Phần nghĩa (từ <html> đến <ul><html>)
                    data.put(word, def);            // Thêm từ và nghĩa vào data
                    Words.add(word);                // thêm từ vào Words
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return Words;
    }

    /**
     * Thêm từ vào Dictionary(data và Words)
     */
    public void update(){

    }
}
