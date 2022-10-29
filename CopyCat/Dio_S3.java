// Program Dio_S1.java
//
// Solves the Diophantine equation: k = x^3 + y^3 + z^3 (original totally unoptimized version).
//
// R0: 08-Oct-2020 Written by Student_1.
//
import java.util.Scanner;

public class Dio_S1
{
   public static void main(String[] args)
   {
      final Scanner KB = new Scanner(System.in);
      final int range, span;
      boolean found;
      int n, x, y, z;
      
      System.out.print("Enter range for k: ");
      range = KB.nextInt();
      System.out.print("Enter span for x, y, z: ");
      span = KB.nextInt();
      System.out.println();
      
      // ET start - insert at start of algorithm of interest
      long et = System.nanoTime();
      
      n = 0;
      for (int k=0;k<=range;k++)
      {
         found = false;
         for (x=-span;x<=span;x++)
         {
            for (y=-span;y<=span;y++)
            {
               for (z=-span;z<=span;z++)
                  if ((k == Math.pow (x,3) + Math.pow(y,3) + Math.pow(z,3)) & !found)
                  {
                     System.out.println("k: " + k + ", x: " + x + ", y: " + y + ", z: " + z);
                     found = true;
                     n++;
                  }
            }
         }
         if (!found) System.out.println("k: " + k + " Not found!");
      }
      
      // ET end - insert at end of algorithm of interest
      et = System.nanoTime() - et;

      System.out.println();
      System.out.println("n: " + n);
      System.out.printf("\nElapsed time for algorithm: %.2E nsecs",(double)et);
   } // end main
} // end class