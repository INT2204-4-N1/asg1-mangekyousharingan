package Dict;

import java.util.ArrayList;
/**
 * TODO: Class Dictionary khai báo thuộc tính của từ và nghĩa của từ đó
 * DDictionarry: Danh sách Word
 * @author team mangekyousharingan
 */

public class Dictionary {
    ArrayList<Word> Dictionary = new ArrayList<Word>();
    public void AddToDict(Word word){
        this.Dictionary.add(word);
    }
}
