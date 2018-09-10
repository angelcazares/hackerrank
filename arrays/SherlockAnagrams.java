import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SherlockAnagrams {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {

        ArrayList<String> stringWords = new ArrayList<String>();
        int i = 0, f = 1;
        String tmp;

        /* Get all the possible words */
        for(int x = 1; x <= s.length(); x ++) {
            i = 0;
            f = i + x;
            while(f <= s.length()) {
                tmp = s.substring(i++, f++);
                stringWords.add(tmp);
            }
        }
        
        int sum = 0;
        String tmpx;
        String tmpy;
        
        HashMap<Character, Integer> tmpMap = new HashMap<Character, Integer>();
        for(int x = 0; x < stringWords.size(); x ++) {
            tmpx = stringWords.get(x);                    
            for(int y = x + 1; y < stringWords.size(); y++) {
                tmpy = stringWords.get(y);
                if(tmpx.length() == tmpy.length() && isAnagram(tmpy, tmpx) ) {
                    sum += 1;
                }
            }
        }
        return sum;
    }
    
    /*
    *  Get a hashmap representation of a string
    */
    private static HashMap<Character, Integer> stringToHashMap(String str) {
        char[] charArray = str.toCharArray();
        HashMap<Character, Integer> res = new HashMap<Character, Integer>();
        int sum = 0;
        for(int x = 0; x < charArray.length; x ++) {
            sum = res.containsKey(charArray[ x ]) ? res.get(charArray[ x ]) + 1 : 1;
            res.put(charArray[ x ], sum);
        }
        return res;
    }
    
    /*
    *  Validate if a string is an anagram of another string 
    */
    private static boolean isAnagram(String str, String str2) {
        char[] charArray = str.toCharArray();
        int sum = 0;
        
        HashMap<Character, Integer> coli = stringToHashMap(str2);
        
        for(int x = 0; x < charArray.length; x ++) {
            if(coli.containsKey(charArray[ x ])) {
                 sum = coli.get(charArray[ x ]) - 1;
                 coli.put(charArray[ x ], sum);
            } else {
                return false;
            }
        }
        
        for(Integer num : coli.values()) {
            if( num != 0 ) {
                return false;
            }
        }
        
        return true;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
