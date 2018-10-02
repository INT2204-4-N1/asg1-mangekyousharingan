package Dict;

import java.util.Scanner;

public class DictionaryManagement {
    Dictionary Dict = new Dictionary();
    public void insertFromCommandline(){
        int NW;
        Scanner sc = new Scanner(System.in);
        NW = sc.nextInt();
        for (int i = 0; i < NW; i++){
            Dict.wordarray[i].word_target = sc.nextLine();
            Dict.wordarray[i].word_explain = sc.nextLine();
        }
    }
    public static void main(String[] args){
        DictionaryManagement DictM = new DictionaryManagement();
        DictM.insertFromCommandline();
    }
}
