// Primes_S2.java
//
// Purpose: Finds the first "n" prime numbers, dividing by odds up through the square root of the candidate.
//
// R0: 02-Jan-2020 Written by Student_2.
//
import java.util.Scanner;
public class Primes_S2 {
   public static void main(String[] args) {

      // display welcome banner
      System.out.println("<><><> Program Primes <><><>");
      System.out.println();
      
      // prompt for number of primes
      Scanner kb = new Scanner(System.in);
      System.out.printf("Input n: ");
      int n = kb.nextInt();

      // ET routine - insert at start of algorithm of interest
      long et = System.nanoTime();

      // declare vars
      boolean prime;
      int nPrimes;
      int x;

      // calc primes
      nPrimes = 1;
      x = 1;
      do {
         x += 2;
         prime = true;
         int root = (int) Math.sqrt(x);
         for (int i=3;i <= root;i++)
            if ((x % i) == 0) {
               prime = false;
               break;
         }
         if (prime) {
            nPrimes++;
//            System.out.println(nPrimes + ": " + x + " is prime!");
         }
      } while (nPrimes < n);

      // ET routine - insert at end of algorithm of interest
      et = System.nanoTime() - et; // nanoseconds

      // display results
      System.out.println("Found " + nPrimes + " primes, last prime was: " + x);
      System.out.printf("Elapsed time for algorithm: %.4e nsecs\n", (double) et);
  }
}