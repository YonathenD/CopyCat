/************************

  Name: Yonathen Desisa
  Date: 2/25/21
  Period: 3
  Lab: CopyCat
  
  Purpose:
    This program will detect if the two programs
  given have excessively collaborated or not.
  
*************************/

import java.util.Scanner;
import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
public class CC_YWD
{
   public static void main(String[] args) throws Exception
   {
   
      Scanner input = new Scanner(System.in);
   
      System.out.println("\nx+x+x Copy Cat x+x+x\n=====\n");
      
      /*Prompt for user input*/
      System.out.print("Potential Culprit #1: ");
      String file1 = input.next();
      System.out.print("Potential Culprit #2: ");
      String file2 = input.next();
   
      System.out.println();
      
      /*Read and store the two files of source code into an arraylist, line by line*/
      ArrayList<String> fileArr1 = new ArrayList<>(Arrays.asList(new String(Files.readAllBytes(Paths.get(file1))).replaceAll(" ", "").replaceAll("_", "").toLowerCase().trim().split("\\r?\\n\\s*")));
      ArrayList<String> fileArr2 = new ArrayList<>(Arrays.asList(new String(Files.readAllBytes(Paths.get(file2))).replaceAll(" ", "").replaceAll("_", "").toLowerCase().trim().split("\\r?\\n\\s*")));
      
      /*Create arraylist of only the variables with their equations
      and conditionals of each file's source code*/
      ArrayList<String> fileVarCon1 = varCon(fileArr1);
      ArrayList<String> fileVarCon2 = varCon(fileArr2);
   
      int minFile = Math.min(fileVarCon1.size(), fileVarCon2.size());
      int maxFile = Math.max(fileVarCon1.size(), fileVarCon2.size());
      
      /*Makes sure it doesn't matter the order in which the 
      files are typed*/
      if (fileVarCon1.size() == maxFile) {
         ArrayList<String> tempArr = fileVarCon1;
         fileVarCon1 = fileVarCon2;
         fileVarCon2 = tempArr;
      }
   
      int exactMatches = 0;
      
      /*Counts the number of exact matches between the two files*/
      for (int i = 0; i < maxFile; i++) {
         if (fileVarCon1.contains(fileVarCon2.get(i))) {
            exactMatches++;
         }
      }
      
      /*Display results and verdict*/
      System.out.println("Matches of Varibles/Equations and Conditionals");
      System.out.println("==========");
      System.out.println("Number of Exact Matches: " + exactMatches);
      System.out.printf("Exact Matches Percentage: %.2f%%", (100.0*exactMatches*2)/(minFile+maxFile));
   
      System.out.print("\n\nFinal Verdict ... ");
   
      if ((100.0*exactMatches*2)/(minFile+maxFile) > 35) {
         System.out.println("Guilty!!!");
      }
      else {
         System.out.println("Innocent!!!");
      }
   
   }
   
   /*Gets the variables, equations, and conditionals of a file*/
   public static ArrayList<String> varCon (ArrayList<String> fileArr) {
   
      ArrayList<String> tempArr = new ArrayList<>();
   
      for (int i = 0; i < fileArr.size(); i++) {
      
         if (fileArr.get(i).matches(".*(^for|^if|^do|^while).*")) {
            tempArr.add(fileArr.get(i));
         }
         else if (fileArr.get(i).matches(".*(=|,)+.*;.*") && !fileArr.get(i).contains("system.out")) {
            tempArr.add(fileArr.get(i));
         }
      }
   
      return tempArr;
   
   }
}