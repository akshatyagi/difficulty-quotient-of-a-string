package difficulty_level;
import java.util.Scanner;
import java.lang.String;
/**
 *
 * @author Akshat
 */
class Difficulty_Check{
    static class Node{
        String data;
        Node next;
        Node(String d){data = d; next = null;}
    }
    Node head;
    public void push(String d){
        Node new_node = new Node(d);
        if(head == null){new_node.next = null; head = new_node;return;}
        new_node.next = head;
        head = new_node;        
    }    
    public int Difficulty(String str, int length){
        int d_q = 0;
        String word = "";
        if(str == null){return d_q;}
        for(int i = 0; i<length;i++){  
            word = word + str.charAt(i);
            if(str.charAt(i) == ' ' || i == length-1){
                push(word.trim());
                word = "";               
            }      
        }        
        return cal_dq(head);   
    }  
    public int cal_dq(Node current){
        int hardcount = 0, easycount = 0;
        while(current!=null){
            if(is_hard(current.data) == true){hardcount++;}
            else{easycount++;}
            current = current.next;
        }
        return (5*hardcount) - (2*easycount);
    }
    boolean is_vowel(int i, String word){
        return word.charAt(i)=='a' || word.charAt(i)=='e' || word.charAt(i)=='i' || word.charAt(i)=='o' || word.charAt(i)=='u'?true:false;
    }
    public boolean is_hard(String word){
        boolean is_hard = false;
        int vowel = 0, consonant = 0, easy = 0, hard = 0;
        int three_count = 0;
        char vowels[] = {'a','e','i','o','u'};
        for(int i=0; i<word.length();i++){          
            if(is_vowel(i,word)){                 
                vowel++;              
            }else if(!is_vowel(i,word)){
                consonant++;
            }   
            if((i>1) && !is_vowel(i,word) && !is_vowel(i-1,word) && !is_vowel(i-2,word) ){
                three_count = 1;
            }           
        }
        if(consonant>vowel || three_count == 1){is_hard = true;}   
        return is_hard;
    }
}
public class Difficulty_level {
    public static void main(String[] args) {      
        Scanner sc = new Scanner(System.in);
        Difficulty_Check dc = new Difficulty_Check();        
        String str = sc.nextLine();
        int len = str.length();
        System.out.println(dc.Difficulty(str,len));
    }  
}
