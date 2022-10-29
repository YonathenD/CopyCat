// Pendulum_S4.java
// 
// Simulates a pendulum in SMH.
//
// R0 14-Feb-2021 - Written by Student_4 for FPHS.
//
import java.io.*;
import java.util.*;
public class Pendulum_S4 {
   public static void main(String[] args) {
   
      final int POINTS = 100000;
      double[][] points = new double[POINTS][3];
      int numPoints = 0;
   
      // declare and assign simulation controls      
      final double DELTA_T = 0.01; // seconds
      boolean details;
   
      // display welcome banner
      System.out.println();
      System.out.println("<*><*><*> Program Pendulum <*><*><*>");   
      System.out.println();
   
      // declare and assign keyboard scanner   
      Scanner kb = new Scanner(System.in);
   
      // FPHS
      final double atmDensity = 1.162; // kg/m^3
      final double GC = 9.800; // m/s^2 (FPHS)
   
      // declare pendulum parameters
      double cd;
      double diameterCm;
      double lengthCm;
      double massGm;
   
      System.out.print("Enter Pendulum Diameter (cm): ");
      diameterCm = kb.nextDouble();
      System.out.print("Enter Pendulum Mass (g): ");
      massGm = kb.nextDouble();
      System.out.print("Enter Pendulum Length (cm): ");
      lengthCm = kb.nextDouble();
      System.out.print("Enter Drag Coefficient: ");
      cd = kb.nextDouble();
   
      System.out.println();
      System.out.print("Display details (y/n)? ");
      details = yesNo();
      System.out.println();
      
      // declare and init event logging
      double yMax = 0;
      
      // declare other variables
      double angle = Math.toRadians(-90); // radians
   
      // declare and calc launch params
      double RADIUS = (diameterCm / 100) / 2; // m
      double MASS = massGm / 1000; // kg
      double area = Math.PI * Math.pow(RADIUS, 2); // m^2
      double drag;
      double a = 0; // angular accel
      double v = 0; // linear vel
      double omega = 0; // angular vel
      double length = lengthCm / 100; // meters
   
      // start timer ...
      System.out.println("3-2-1 ... Drop!");
      System.out.println();
      double time = 0; // seconds
   
      // display header
      if (details) {      
         System.out.println("  Time      Accel       Vel      Angle");
         System.out.println("   (s)     (m/s^2)     (m/s)     (deg)");
         System.out.println(" ------    -------    -------    -----");
      }
   
      // loop until done 
      for (time=0;time<100;time+=DELTA_T) {
         // display details
         if (details) {
            System.out.printf("%7.4f   %7.1f    %7.1f   %7.1f\n", time, a, omega, Math.toDegrees(angle));
         }
         
         points[numPoints][0] = time;
         points[numPoints][1] = time;
         points[numPoints][2] = angle;
         numPoints++;
      
         // linear velocity
         v = omega * length;
      
         // drag
         drag = 0.5 * cd * atmDensity * area * Math.pow(v,2);
      
         // accel
         a = -(GC / length * Math.sin(angle)) - (drag / length / MASS);
                     
         // velocity
         omega += a * DELTA_T;
         
         // position
         angle += omega * DELTA_T; 
      } // end for loop
   
      if (details) {
         System.out.printf("%7.4f   %7.1f    %7.1f   %7.1f\n", time, a, omega, Math.toDegrees(angle));
      }
      points[numPoints][0] = time;
      points[numPoints][1] = omega;
      points[numPoints][2] = angle;
      numPoints++;
      
      // write data file
      writePoints("Pendulum_points.txt",points,numPoints);
      
      // display projectile parameters
      if (details) {
         System.out.println();
      }
   
      // display projectile parameters
      System.out.println();
      System.out.println("Pendulum Parameters ...");
      System.out.printf("Diameter:   %4.1f cm\n", diameterCm);
      System.out.printf("Net Mass:   %4.1f g\n", massGm);
      System.out.printf("Length:     %4.1f cm\n", lengthCm);
      System.out.printf("Drag Coeff: %4.2f\n", cd);
         
      // display summary
      System.out.println();
      System.out.println("Path Summary ...");
   
      // display additional info
      System.out.println();
      System.out.println("Additional Info ...");
      System.out.printf("DELTA_T: %6.4f sec\n", DELTA_T);
      System.out.printf("Air Den: %6.4f kg/m^3\n", atmDensity);
      System.out.printf("Gravity: %6.4f m/s^2\n", GC);
   } // end main

// ############################################################################
// yesNo() - Obtains a yes/no response from the user, returns a boolean.
//
// 17-Dec-2020 Written by Al Friebe for FPHS.
// ############################################################################

   public static boolean yesNo()
   {
      Scanner kb = new Scanner(System.in);
      String str;
      for (;;)
      {
         str = kb.next();
         if (str.toLowerCase().charAt(0) == 'y')
            return true;
         if (str.toLowerCase().charAt(0) == 'n')
            return false;
         System.out.print("Please answer 'y' or 'n': ");
      }
   } // end yesNo

// ############################################################################
// writePoints() - Writes an arrays of double to a space-delimited text file.
//
// 17-Dec-2020 Written by Al Friebe for FPHS.
// ############################################################################

   public static void writePoints(String outFileName, double[][] points, int numPoints)
   {
      File file = new File(outFileName);
   
      // open output file for writing
      System.out.println("Writing output file: " + outFileName);
      PrintWriter output = null;
      try
      {
         output = new PrintWriter(file);
      }
      catch (FileNotFoundException ex)
      {
         System.out.println("File write error, cannot create: " + outFileName);
         System.exit(1); // quit the program
      }
      
      // write the values to the file
      for (int i=0;i<numPoints;i++)
      {
         for (int j=0;j<points[0].length;j++)
            output.print(String.valueOf(points[i][j]) + " ");
         output.print(String.valueOf("\n"));
      }
      output.close();
   } // end writePoints
} // end class