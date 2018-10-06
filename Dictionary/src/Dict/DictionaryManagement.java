package Dict;

import java.util.Scanner;

public class DictionaryManagement {
    Word word = new Word();
    public void insertFromCommandline(){
        int NW;
        Scanner sc = new Scanner(System.in);
        NW = sc.nextInt();
        for (int i = 0; i < NW; i++){
            word.setWordtarget(sc.nextLine());
            word.setWordexplain(sc.nextLine());
        }
    }
    public static void main(String[] args){
        DictionaryManagement DictM = new DictionaryManagement();
        DictM.insertFromCommandline();
    }
}
