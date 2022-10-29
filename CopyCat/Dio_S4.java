// Program Dio_S4.java
//
// Solves the Diophantine equation: k = x^3 + y^3 + z^3 (significantly faster than the original version).
//
// Rev 0: 23-Sep-2019 Student_4 - Written for FPHS.
//
import java.util.Scanner;

public class Dio_S4 {
   public static void main(String[] args) {
      Scanner kb = new Scanner(System.in);
      final int RANGE, SPAN;
      int k, n, x, y, z;
      long sum;
   
      System.out.print("Enter range for k: ");
      RANGE = kb.nextInt();
   
      System.out.print("Enter span for x,y,z: ");
      SPAN = kb.nextInt();
      System.out.println();
   
      // ET start
      long et = System.nanoTime();
   
      // declare arrays
      boolean[] found = new boolean[RANGE+1];
      int[] a = new int[RANGE+1];
      int[] b = new int[RANGE+1];
      int[] c = new int[RANGE+1];
   
      // populate the k-array
      for (x=-SPAN;x<=1;x++)
         for (y=x;y<=SPAN;y++)
            for (z=0;z<=SPAN;z++) {
               sum = (x * x * x) + (y * y * y) + (z * z * z);
               if ((sum >= 0) && (sum <= RANGE)) {
                  found[(int)sum] = true;
                  a[(int)sum] = x;
                  b[(int)sum] = y;
                  c[(int)sum] = z;
               }
            }
   
      // check for hits
      n = 0;
      for (k=0;k<=RANGE;k++) {
         System.out.print("k: " + k + ", ");
         if (found[k]) {
            System.out.println("x: " + a[k] + ", y: " + b[k] + ", z: " + c[k]);
            n++;
         }
         else
            System.out.println("Not found!");
      }
      System.out.println();
      System.out.println("n: " + n);
   
      // ET end
      et = System.nanoTime() - et;
      System.out.printf("\nElapsed time for algorithm: %.2E nsecs",(double)et);
      
   }
} // end class