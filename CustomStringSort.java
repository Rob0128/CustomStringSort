// 13707293
// robertjohnhill1@gmail.com

import java.io.*;
import java.util.*;


interface PredicateStringPair
{
    boolean better( String a, String b );

}


public class CustomStringSort {


    public static Comparator<String> createComparator( PredicateStringPair pred ) {

        //
        Comparator<String> comp = (String a, String b) -> {
            if (pred.better(a, b)) return -1;
            else if (pred.better(b, a)) return 1;
            else return 0;
        };

        return comp;
    }

    public static void sortStrings( ArrayList<String> lst, PredicateStringPair pred )
    {
        Comparator<String> c = createComparator(pred);
        Collections.sort(lst, c);

    }

    public static void sortStringsLonger( ArrayList<String> lst )
    {
        //returns true on a pair of Strings a, b if and only if the length of a is strictly greater than the length of b
     PredicateStringPair p = (a, b) -> {
         if (a.length() > b.length()) return true;
         else return false;
     };
     sortStrings(lst, p);

    }


    public static void sortStringsNumAs( ArrayList<String> lst )
    {
        //returns true on a pair of Strings a, b if and only if the length of a is strictly greater than the length of b
        PredicateStringPair p = (a, b) -> {
            String[] A = a.split("");
            String[] B = b.split("");
            int acount = 0;
            int bcount = 0;
            for (String i : A){
                if (i == "a" || i == "A"){
                    acount += 1;
                }
            }
            for (String i : B){
                if (i == "a" || i == "A"){
                    bcount += 1;
                }
            }

            if (acount > bcount) return true;
            else return false;
        };
        sortStrings(lst, p);
 
    }

    public static void sortStringsDictionary( ArrayList<String> lst )
    {
        //returns true on a pair of Strings s1, s2 if and only if s1 comes before s2 according to dictionary ordering
        PredicateStringPair p = (a, b) -> {
            String[] newA = a.split("");
            String[] newB = b.split("");
            int min;
            boolean mismatch = false;
            String adif = "";
            String bdif = "";
            if (a.length() < b.length()) {
                min = a.length();
            } else {
                min = b.length();
            }

            for (int i = 0; i < min; i++) {
                if (!newA[i].equals(newB[i])) {
                    mismatch = true;
                    adif = newA[i];
                    bdif = newB[i];
                    break;

                }
            }
            if (mismatch == false) {
                if(a.length() < b.length()) {
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                if(adif.matches("[a-zA-Z]") && !bdif.matches("[a-zA-Z]")) {
                    return true;
                }
                else if (bdif.matches("[a-zA-Z]") && !adif.matches("[a-zA-Z]")) {
                    return false;
                }
                else if (adif.matches("[a-zA-Z]") && bdif.matches("[a-zA-Z]")){
                    //Automates the creation of the custom alphabetical ordering
                    ArrayList<String> alph = new ArrayList<String>();
                    for (int i = 0; i < 26; i++){
                        alph.add(Character.toString(97 + i));
                        alph.add(Character.toString(65 + i));
                    }
                    if (alph.indexOf(adif) < alph.indexOf(bdif)){
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                else {
                    if (adif.compareTo(bdif) > 1) {
                        return false;
                    }
                    else{
                        return true;
                    }


                }

            }

        };
        sortStrings(lst, p);
    }


  
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<String>();

        fruits.add("Strawberry");
        fruits.add("strawberries");
        fruits.add("Kiwi");
        fruits.add("kiwis");
        fruits.add("Banana");
        fruits.add("bananas");
        fruits.add("Banana");
        fruits.add("bananas");

        System.out.println( fruits );
        sortStringsLonger( fruits ); //longer
        System.out.println("afterlonger");
        System.out.println( fruits );
        
        sortStringsNumAs( fruits );
        System.out.println("afterNum");
        System.out.println( fruits );

        fruits.add( "@plum" );
        fruits.add( "@kumquat" );
        fruits.add( "banana" );
        fruits.add( "3 oranges" );
        fruits.add( "9 oranges" );
        fruits.add( "33 oranges" );
        System.out.println( fruits );
        sortStringsDictionary( fruits );
        System.out.println( fruits );
    }
}
