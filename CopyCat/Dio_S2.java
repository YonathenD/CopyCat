// Program Dio_S2.java
//
// Solves the Diophantine equation: k = x^3 + y^3 + z^3
//
// R0: 07-Sep-2019 Student_2 - Written for FPHS.
// R1: 06-Oct-2019 Student_2 - Added "range" and "span" parameters to support time complexity studies.
// R2: 21-Sep-2020 Student_2 - Updated ET display.
// R3: 09-Oct-2020 Student_2 - Added "mod 9" to k-loop (why not?).
// R4: 12-Oct-2020 Student_2 - Experimenting with x,y,z spans (faster, but are they legit?).
//
import java.util.Scanner;

public class Dio_S2
{
   public static void main(String[] args)
   {
      final Scanner KB = new Scanner(System.in);
      final int NEG_SPAN, SPAN, RANGE;
      boolean found;
      int n, x, y, z;
      
      System.out.print("Enter range for k: ");
      RANGE = KB.nextInt();
      System.out.print("Enter span for x, y, z: ");
      SPAN = KB.nextInt();
      NEG_SPAN = -SPAN;
      System.out.println();
      
      long et = System.nanoTime();
      n = 0;
      for (int k=0;k<=RANGE;k++)
      {
         if (( k % 9 == 4) || (k % 9 == 5))
         {
            System.out.println("k: " + k + " Not found!");
            continue;
         }            
         label1:
         {
            found = false;
            for (x=NEG_SPAN;x<=1;x++)
            {
               for (y=x;y<=SPAN;y++)
               {
                  for (z=0;z<=SPAN;z++)
                  {
                     if (k == (x * x * x) + (y * y * y) + (z * z * z))
                     {
                        System.out.println("k: " + k + ", x: " + x + ", y: " + y + ", z: " + z);
                        found = true;
                        n++;
//                        break label1;
                        x = SPAN+1;
                        y = SPAN+1;
                        z = SPAN+1;
                     }
                  }
               }
            } 
            if (!found) System.out.println("k: " + k + " Not found!");
         }
      }
      et = System.nanoTime() - et;
      
      System.out.println();
      System.out.println("n: " + n);
      System.out.printf("\nElapsed time for algorithm: %.2E nsecs",(double)et);
   } // end main
} // end class