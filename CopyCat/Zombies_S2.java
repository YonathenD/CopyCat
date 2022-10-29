// Zombies_S2.java
//
// Simulates Zombie Apocalypse!
//
// B = Body (dead - attracts zombies)
// H = Human (the user)
// Z = Zombie!
//
// R0: 21-Mar-2020 - Written by Student_2 for FPHS.
//
import java.util.*;
public class Zombies_S2
{
   public static void main(String[] args)
   {
      Random rnd = new Random();
      Scanner kb = new Scanner(System.in);
      String temp;
      boolean body;
      char hMove;
      double db, dh;
      int bx, by, hx, hy, zx, zy;
      int dbx, dby, dhx, dhy;
      
      // display header
      System.out.println();
      System.out.println("/\\/\\/\\ Program Zombies /\\/\\/\\");
      System.out.println();
   
      // playing field
      final int HEIGHT = 10;
      final int WIDTH = 10;
      char[][] room = new char[HEIGHT][WIDTH];
   
      // room array
      for (int y=0;y<HEIGHT;y++)
         for (int x=0;x<WIDTH;x++)
            room[y][x] = ' ';
   
      // human
      hx = rnd.nextInt(WIDTH);
      hy = rnd.nextInt(HEIGHT);
      room[hy][hx] = 'H';
   
      // zombie
      do
      {
         zx = rnd.nextInt(WIDTH);
         zy = rnd.nextInt(HEIGHT);
      } while (room[zy][zx] != ' ');
      room[zy][zx] = 'Z';
   
      // body
      body = true;
      do
      {
         bx = rnd.nextInt(WIDTH);
         by = rnd.nextInt(HEIGHT);
      } while (room[by][bx] != ' ');
      room[by][bx] = 'B';
   
      // start game
      System.out.println("Yikes! A zombie! Run for your life!");
      System.out.println();
   
      // loop until done
      for (;;)
      {
         // display room
         displayRoom(room);
         
         // move human
         System.out.print("Your move (n, s, e or w): ");
         temp = kb.next();
         hMove = temp.charAt(0);
         room[hy][hx] = ' ';
         if (hMove == 'n')
            hy++; 
         else if (hMove == 'e')
            hx++;
         else if (hMove == 'w')
            hx--; 
         else if (hMove == 's')
            hy--;
      
         // check win
         if ((hx == WIDTH) && (hy == HEIGHT-1))
         {
            System.out.println();
            System.out.println("Congrats! You survived!");
            break;
         }
      
         // check loss
         if ((zx == hx) && (zy == hy))
         {
            System.out.println();
            System.out.println("You've been bitten by a zombie ... you lose!");
            break;
         }
         room[hy][hx] = 'H';
      
         // move zombie
         room[zy][zx] = ' ';
         
         // calc distance to body
         dbx = Math.abs(zx - bx);
         dby = Math.abs(zy - by);
         db = Math.sqrt(Math.pow(dbx,2) + Math.pow(dby,2));
         
         // calc distance to human
         dhx = Math.abs(zx - hx);
         dhy = Math.abs(zy - hy);
         dh = Math.sqrt(Math.pow(dhx,2) + Math.pow(dhy,2));
         
         if ((db <= dh) && body) // move towards body
         {
            if (dbx > dby) // move x
            {
               if (zx > bx)
                  zx--;
               else
                  zx++;
            }
            else // move y
            {
               if (zy > by)
                  zy--;
               else
                  zy++;
            }
            // check if zombie found body
            if ((zx == bx) && (zy == by))
               body = false;
         }
         else // move towards human
         {
            if (dhx > dhy) // move x
            {
               if (zx > hx)
                  zx--;
               else
                  zx++;
            }
            else // move y
            {
               if (zy > hy)
                  zy--;
               else
                  zy++;
            }
         }         
         room[zy][zx] = 'Z';
      
         // check loss
         if ((zx == hx) && (zy == hy))
         {
            System.out.println();
            System.out.println("You've been bitten by a zombie ... you lose!");
            break;
         }
      } // end loop
   } // end main

   static void displayRoom(char[][] room)
   {
      // top wall
      System.out.print("+-");
      for (int x=0;x<room[0].length;x++)
         System.out.print("--");
      System.out.println("+");
      
      // inside room
      for (int y=room.length-1;y>=0;y--)
      {
         System.out.print("| ");            
         for (int x=0;x<room[0].length;x++)
            System.out.print(room[y][x] + " ");
         if (y != room.length-1)
            System.out.println("|");
         else 
            System.out.println();   
      }
      
      // bottom wall
      System.out.print("+-");
      for (int x=0;x<room[0].length;x++)
         System.out.print("--");
      System.out.println("+");
   } // end displayRoom
}
