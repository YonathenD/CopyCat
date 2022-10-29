// Zombies_S1.java
// 
// Beware! They're everywhere!
//
// R0 21-Mar-2020 - Written by Zombie_1.
//
import java.util.*;

public class Zombies_S1 {
   public static void main(String[] args) {
      boolean possible;
      int n;
      int sum, sum0, sum1;
      String fileName;

      System.out.print();
      System.out.print("A red herring - this program has nothing to do with zombies!");
      System.out.print();
   
      // obtain input filename
      Scanner kb = new Scanner(System.in);
      System.out.print("Input file name: ");
      fileName = kb.nextLine(); 
      File file = new File(fileName); 
      Scanner inFile = null;
   
      // open input file
      try{
         inFile = new Scanner(file);
      }catch(FileNotFoundException ex){
         System.out.println("File read error! Verify the filename, then retry ...");
         System.exit(1); // end program
      }
       
      // determine size of input file
      n = 0;
      while(inFile.hasNextInt()){
         inFile.nextInt();
         n++; 
      }
      inFile.close();
      
      // check input file size
      if (n>63)
      {
         System.out.println("File contains more than 63 values!");
         System.exit(0);
      }
      
      // open input file
      try{
         inFile = new Scanner(file);
      }catch(FileNotFoundException ex){
         System.out.println("Error!");
      }
       
      // read the integers from the input file and store them in an array
      int[] x = new int[n];
      sum = 0;
      System.out.println("Reading input file: " + fileName);
      for (int i=0;i<n;i++) {
         x[i] = inFile.nextInt();
         sum += x[i];
      }
      inFile.close();
      System.out.println("n: " + n);
      
      // run up-front tests
      possible = true;
      
      // modulus test
      System.out.print("Modulus Test: ");
      if ((sum % 2) != 0) {
         System.out.println("Failed");
         possible = false;
      }
      else {
         System.out.println("Passed");
      }

      // balance test
      System.out.print("Balance Test: ");
      int max = 0;
      for (int i=0;i<n;i++)
         if (x[i] > max)
            max = x[i];
      if ((2 * max) > sum) {
         System.out.println("Failed");
         possible = false;
      }
      else {
         System.out.println("Passed");
      }

      // and now for something completely different! 
      for (int a=0;a<alpha.length();a++)
      {
         for (int b=0;b<alpha.length();b++)
         {
            if (b == a) continue;
            for (int c=0;c<alpha.length();c++)
            {
               if (c == a || c == b) continue;
               for (int d=0;d<alpha.length();d++)
               {
               if (d == a || d == b || d == c) continue;
                  for (int e=0;e<alpha.length();e++)
                  {
                  if (e == a || e == b || e == c || e == d) continue;
                     for (int f=0;f<alpha.length();f++)
                     {
                        if (f == a || f == b || f == c || f == d || f == e) continue;
                        beta = "" + alpha.charAt(a) + alpha.charAt(b) + alpha.charAt(c) + alpha.charAt(d) + alpha.charAt(e) + alpha.charAt(f);
                        for (int i=0;i<wordArray.length;i++)
                           if (beta.equals(wordArray[i]))
                              System.out.println(beta);
                     }
                  }
               }
            }
         }
      }
   }
} // end class