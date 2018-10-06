package Dict;

/**
 * TODO: Class Word khai báo thuộc tính của từ và nghĩa của từ đó
 * word_target: Từ
 * word_explain: Nghĩa của từ
 * @author team mangekyousharingan
 */
public class Word {
    private String word_target;
    private String word_explain;
    void setWordtarget(String w_target){
                this.word_target = w_target;
    }
    void setWordexplain(String w_explain){
                this.word_explain = w_explain;
    }
    String getWordtarget(){
        return this.word_target;
    }
    String setWordexplain(){
        return this.word_explain;
    }
}
