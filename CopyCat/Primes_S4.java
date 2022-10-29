// Primes_S4 - finds primes
// 
import java.util.*;
public class Primes_S4{
   public static void main(String args[]){
      System.out.println("<><><> Program Primes <><><>");
      Scanner scan = new Scanner(System.in);
      System.out.print("\nInput n: ");
      int nummer = scan.nextInt();
      long sum = 0;
      int x = 10;
      for (int i=0;i<x;i++)
      {
         long et = System.nanoTime();
         Prime(nummer);
         et = System.nanoTime() - et;
         sum += et;
         System.out.println("Found "+nummer+" primes, last prime was: "+Prime(nummer));
         System.out.printf("Elapsed time for algorithm: %.4e nsecs\n", (double) et);
      }
      System.out.printf("Average ET: %.4e nsecs\n", (double) sum / x);
      System.out.printf("Primes per second: %.4e\n", nummer / ((double)sum / 1000000000));
   }

   public static int Prime(int nummer){
      if (nummer < 2)
         return 2;
      int Anzahl = 1;
      int Grenze = (int)(nummer*(Math.log(nummer) + Math.log(Math.log(nummer)))) + 3;
      int Wurzel = (int)Math.sqrt(Grenze) + 1;
      Grenze = (Grenze-1)/2;
      Wurzel = Wurzel/2 - 1;
      boolean[] booleschesArray = new boolean[Grenze];
      for(int i = 0; i < Wurzel; i+=1){
         if (!booleschesArray[i]){
            Anzahl+=1;
            for(int j = 2*i*(i+3)+3, p = 2*i+3; j < Grenze; j += p){
               booleschesArray[j] = true;
            }
         }
      }
      int p;
      for(p = Wurzel; Anzahl < nummer; p+=1){
         if (!booleschesArray[p]){
            Anzahl+=1;
         }
      }
      return 2*p+1;
   }
}