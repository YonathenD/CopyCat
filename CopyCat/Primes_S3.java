// Primes_S3.java
//
// Purpose: Finds the first "n" prime numbers, dividing by primes up through the square root of the candidate.
//
// R0: 02-Jan-2020 Written by Student_3 for FPHS.
// R1: 15-Jan-2020 Changed arrangement of increment to "nPrimes".
//
import java.util.Scanner;
public class Primes_S3
{
   public static void main(String[] args)
   {
      // display welcome banner
      System.out.println("<><><> Program Primes <><><>");
      System.out.println();
   
      // prompt for number of primes
      Scanner kb = new Scanner(System.in);
      System.out.printf("Input n: ");
      int n = kb.nextInt();
   
      // ET routine
      long et = System.nanoTime();
   
      // declare vars
      boolean prime;
      int i;
      int nPrimes;
      int root;
      int x;
      int[] primes = new int[n];
      
      // calc the primes
      primes[0] = 2;
      nPrimes = 1;
      if (n >= 2)
      {
         x = 1;
         do
         {
            x += 2;
            prime = true;
            root = (int) Math.sqrt(x);
            for (i=0;primes[i]<=root;i++)
               if (x % primes[i] == 0)
               {
                  prime = false;
                  break;
               }
            if (prime)
            {
               primes[nPrimes] = x;
               nPrimes++;
            }
         } while (nPrimes < n);
      }
            
      // ET routine
      et = System.nanoTime() - et; // nanoseconds
      
      // display results
      System.out.println("Found " + nPrimes + " primes, last prime was: " + primes[nPrimes-1]);
      System.out.printf("Elapsed time for algorithm: %.4e nsecs\n", (double) et);
   }
}