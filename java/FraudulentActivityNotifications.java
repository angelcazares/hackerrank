import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/*
    HackerLand National Bank has a simple policy for warning clients about possible fraudulent account activity. 
    If the amount spent by a client on a particular day is greater than or equal to  the client's median spending for a trailing number of days, 
    they send the client a notification about potential fraud. T
    he bank doesn't send the client any notifications until they have at least that trailing number of prior days' transaction data.

    Given the number of trailing days  and a client's total daily expenditures for a period of  days, 
    find and print the number of times the client will receive a notification over all  days.
*/
public class FraudulentActivityNotifications {
 
    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        int start, end;
        int count = 0;
        double media = 0d;
        int [] temp = new int [201];
       
        /* Initialize with zeros */
        for(int x = 0; x <= 200; x++) {
            temp[ x ] = 0;   
        }
        
        /* First section */
        for(int x = 0; x < d; x++) {
            temp[ expenditure[ x ] ] ++;
        }
        
        for(int x = 0; x < expenditure.length - d; x++) {
            start = x;
            end = start + d;
            
            if( x > 0) {
                temp[ expenditure[ x - 1 ] ] --;
                temp[ expenditure[ end - 1 ] ] ++;
            }
            
            media = getMedian(expenditure, d, temp) * 2;
            count = expenditure[ end ] >= media  ? count + 1 : count;
        }
        
        return count;
    }
    
    /*
    *  Get the median of a set of elements
    *  Iterate to get the element in the mid position of an ordered list
    */
    private static double getMedian(int[] expenditure, int d, int[] temp) {
        
        int div = d / 2;
        int count = 0;
        int tmp = 0;
        int previous = -1;
        
        for(int x = 0; x <= 200; x++) {
            tmp = temp[ x ];
            while( tmp-- > 0 ) {
                count ++;
                
                if( count > div) {
                    return d % 2 == 0 ? (x + previous) / 2.0 : x;
                }
                previous = x;
            }
        }
        
        return 1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");
        int n = Integer.parseInt(nd[0]);
        int d = Integer.parseInt(nd[1]);
        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
