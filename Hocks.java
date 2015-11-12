package CZ2002;

import java.util.ArrayList;

public class Hocks {
    
    public static void main(String[] args){
        
        ArrayList <String[]> arr = new <String[]> ArrayList();
        String[] strArr = new String[2];
        
        strArr[0] = "Hello";
        strArr[1] = "Goodbye";
        
        arr.add(strArr);
        
        System.out.println("arr.size() = " + arr.size());
        
        for (int i = 0; i < arr.size(); i++){
            for (String s : arr.get(i)){
                System.out.print(s + " ");
            }
            System.out.println();
        }
        System.out.println("---------------");
        
        strArr = new String[2];
        strArr[0] = "Alpha";
        strArr[1] = "Beta";
        
        arr.add(strArr);
        
        System.out.println("arr.size() = " + arr.size());
        
        for (int i = 0; i < arr.size(); i++){
            for (String s : arr.get(i)){
                System.out.print(s + " ");
            }
            System.out.println("++++++++++++++");
        }
        
    }
    
}
