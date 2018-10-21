package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Dictionary {
    private  String name; // từ điển anh việt hay việt anh ?
    private  String path; // Đường dẫn đến từ điển
    private  HashMap<String, String> data;
    private  ArrayList<String> Words;

    /**
     * Getter, setter
     * Event: chọn từ điển nào thì khởi tạo lại Dictionary mới
     */
    public Dictionary(String Name) {
        this.name = Name;
        if (this.name == "E_V") this.path = "src\\sample\\E_V.txt";
        else this.path = "src\\sample\\V_E.txt";
        Words = new ArrayList<>();
        data = new HashMap<>();
    }

    public ArrayList<String> getWords() {
        return Words;
    }

    public void setWords(ArrayList<String> words) {
        Words = words;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    /**
     * Đọc file
     * Words chứa các từ của từ điển, không chứa nghĩa
     * data chứa cả từ và nghĩa
     * readfile thêm dữ liệu trong txt vào data và words
     */
    public  void readfile() {
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
    }

    /**
     * Tìm từ
     * Dựa trên binarySearch của thầy
     * @param w : từ cần tìm
     * @param words : danh sách từ
     * @return vị trí của từ cần tìm hoặc vị trí của từ cần thêm vào ( theo thứ từ từ điển)
     */
    public int SearchWord(String w, ArrayList<String> words) {
        if (words.get(0).compareTo(w) >= 0) return 0;       // So sánh bằng compareTo - từ điển
        int start = 0, end = words.size();
        while (start < end - 1) {
            int temp = (start + end) / 2;
            if (words.get(temp).compareTo(w) < 0) start = temp;       // So sánh bằng compareTo - từ điển
            else end = temp;
        }
        return end;
    }

    /**
     * Thêm từ vào Dictionary(data và Words)
     */
    public void addWord(String newWord, String newDefinition) {
        newWord = newWord.toLowerCase();
        data.put(newWord, newDefinition);
        int position = SearchWord(newWord, Words);
        Words.add(position, newWord);
    }

    /**
     * Thêm từ vào trong file
     */
    public void updateFile() {
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter writer = null;
        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "UTF8");
            writer = new BufferedWriter(outputStreamWriter);
            for (String word : Words) {
                writer.write(word);
                String def = data.get(word);
                if (def != null) {
                    writer.write(data.get(word));
                }
                writer.newLine();
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Xóa từ
     * Tìm từ bằng binary search (hàm SearchWord) và xóa
     * Nếu từ w có trong từ điển thì xóa
     * @param w: từ cần xóa
     */
    public void removeWord(String w){
            int position = SearchWord(w,Words);
            if (position>=0 && position<=Words.size()-1){
                Words.remove(position);
                data.remove(w);
            }
    }

    /**
     * Hàm sửa từ
     * @param w: từ cần sửa
     * @param modifiedWord: từ đã được sửa
     * @param modifiedDef: nghĩa đã được sửa
     */
    public void modifyWord(String w, String modifiedWord, String modifiedDef){
        if (modifiedWord == null)               // không sửa từ
            data.replace(w,modifiedDef);
        else if (modifiedDef == null) {         // không thêm nghía
            String newDef = new String();
            removeWord(w);                      // xóa key - value trong data và từ w trong Words
            data.replace(modifiedWord,newDef);  // thêm từ mới
        }
        else{                                   // cả modifiedWord và modifiedDef không bị null
            removeWord(w);
            addWord(modifiedWord,modifiedDef);
        }
    }

}
