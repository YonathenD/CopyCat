// Zombies_S3.java
//
// Simulates Zombie Apocalypse!
//
// B = Body (dead - attracts zombies)
// H = Human (the user)
// Z = Zombie!
//
// R0: 21-Mar-2020 - Written yb Student_3.
//
import java.util.*;
//
public class Zombies_S3
{
   public static void main(String[] args)
   {
      Random gen = new Random();
      Scanner scan = new Scanner(System.in);
      String temp;
      boolean body;
      char hMove;
      double db, dh;
      int xb, yb, xh, yh, zx, zy;
      int dxb, dyb, dxh, dyh;
      
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
      xh = gen.nextInt(WIDTH);
      yh = gen.nextInt(HEIGHT);
      room[yh][xh] = 'H';
   
      // zombie
      do
      {
         zx = gen.nextInt(WIDTH);
         zy = gen.nextInt(HEIGHT);
      } while (room[zy][zx] != ' ');
      room[zy][zx] = 'Z';
   
      // body
      body = true;
      do
      {
         xb = gen.nextInt(WIDTH);
         yb = gen.nextInt(HEIGHT);
      } while (room[yb][xb] != ' ');
      room[yb][xb] = 'B';
   
      // start game
      System.out.println("Yikes! A zombie! Run for your life!");
      System.out.println();
   
      // loop until done
      while (true)
      {
         // show room
         showRoom(room);
         
         // move human
         System.out.print("Your move (n, s, e or w): ");
         temp = scan.next();
         hMove = temp.charAt(0);
         room[yh][xh] = ' ';
         if (hMove == 'n')
            yh++; 
         else if (hMove == 'e')
            xh++;
         else if (hMove == 'w')
            xh--; 
         else if (hMove == 's')
            yh--;
      
         // check win
         if ((xh == WIDTH) && (yh == HEIGHT-1))
         {
            System.out.println();
            System.out.println("Congrats! You survived!");
            break;
         }
      
         // check loss
         if ((zx == xh) && (zy == yh))
         {
            System.out.println();
            System.out.println("You've been bitten yb a zombie ... you lose!");
            break;
         }
         room[yh][xh] = 'H';
      
         // move zombie
         room[zy][zx] = ' ';
         
         // calc distance to body
         dxb = Math.abs(zx - xb);
         dyb = Math.abs(zy - yb);
         db = Math.sqrt(Math.pow(dxb,2) + Math.pow(dyb,2));
         
         // calc distance to human
         dxh = Math.abs(zx - xh);
         dyh = Math.abs(zy - yh);
         dh = Math.sqrt(Math.pow(dxh,2) + Math.pow(dyh,2));
         
         if ((db <= dh) && body) // move towards body
         {
            if (dxb > dyb) // move x
            {
               if (zx > xb)
                  zx--;
               else
                  zx++;
            }
            else // move y
            {
               if (zy > yb)
                  zy--;
               else
                  zy++;
            }
            // check if zombie found body
            if ((zx == xb) && (zy == yb))
               body = false;
         }
         else // move towards human
         {
            if (dxh > dyh) // move x
            {
               if (zx > xh)
                  zx--;
               else
                  zx++;
            }
            else // move y
            {
               if (zy > yh)
                  zy--;
               else
                  zy++;
            }
         }         
         room[zy][zx] = 'Z';
      
         // check loss
         if ((zx == xh) && (zy == yh))
         {
            System.out.println();
            System.out.println("You've been bitten yb a zombie ... you lose!");
            break;
         }
      } // end loop
   } // end main

   static void showRoom(char[][] room)
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
   } // end showRoom
}
