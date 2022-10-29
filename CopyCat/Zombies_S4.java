// Zombies_S4.java
//
// Simulates the Zombie Apocalypse!
//
// B = Body (dead, useful for attracting zombies)
// H = Human (the user)
// Z = Zombie!
//
// R0: 14-Mar-2020 - Written by Student_4 for FPHS.
// R1: 15-Mar-2020 - Added dead body decoy.
//
import java.util.*;
public class Zombies_S4 {
   public static void main(String[] args) {
      Random rnd = new Random();
      Scanner kb = new Scanner(System.in);
      String temp;
      boolean body;
      char hMove;
      double db, dh; // zombie-to-body and zombie-to-human distances
      int bx, by, hx, hy, zx, zy; // body, human and zombie coordinates
      int dbx, dby, dhx, dhy; // body-to-zombie and human-to-zombie coordinate differences
      
      // display header
      System.out.println();
      System.out.println("/\\/\\/\\ Program Zombies /\\/\\/\\");
      System.out.println();
   
   //       System.out.print("Enter room height: ");
   //       final int HEIGHT = kb.nextInt();
   //       System.out.print("Enter room width: ");
   //       final int WIDTH = kb.nextInt();
   
      // declare playing field
      final int HEIGHT = 10;
      final int WIDTH = 10;
      char[][] room = new char[HEIGHT][WIDTH];
   
      // init room array
      for (int y=0;y<HEIGHT;y++)
         for (int x=0;x<WIDTH;x++)
            room[y][x] = ' ';
   
      // init human
      hx = rnd.nextInt(WIDTH);
      hy = rnd.nextInt(HEIGHT);
      room[hy][hx] = 'H';
   
      // init zombie
      do {
         zx = rnd.nextInt(WIDTH);
         zy = rnd.nextInt(HEIGHT);
      } while (room[zy][zx] != ' ');
      room[zy][zx] = 'Z';
   
      // init body
      body = true;
      do {
         bx = rnd.nextInt(WIDTH);
         by = rnd.nextInt(HEIGHT);
      } while (room[by][bx] != ' ');
      room[by][bx] = 'B';
   
      // start the game
      System.out.println("Yikes! A zombie! Run for your life!");
      System.out.println();
   
      // game loop
      for (;;) {
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
      
         // check for win
         if ((hx == WIDTH) && (hy == HEIGHT-1))
         {
            System.out.println();
            System.out.println("Congrats! You survived!");
            break;
         }
      
         // check for loss
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
            if (dbx > dby) // move in x direction
            {
               if (zx > bx)
                  zx--;
               else
                  zx++;
            }
            else // move in y direction
            {
               if (zy > by)
                  zy--;
               else
                  zy++;
            }
            // check if zombie encountered the body
            if ((zx == bx) && (zy == by))
               body = false;
         }
         else // move towards human
         {
            if (dhx > dhy) // move in x direction
            {
               if (zx > hx)
                  zx--;
               else
                  zx++;
            }
            else // move in y direction
            {
               if (zy > hy)
                  zy--;
               else
                  zy++;
            }
         }         
         room[zy][zx] = 'Z';
      
         // check for loss
         if ((zx == hx) && (zy == hy))
         {
            System.out.println();
            System.out.println("You've been bitten by a zombie ... you lose!");
            break;
         }
      } // end game loop
   } // end main

   static void displayRoom(char[][] room)
   {
      // top wall
      System.out.print("+-");
      for (int x=0;x<room[0].length;x++)
         System.out.print("--");
      System.out.println("+");
      // inside of room
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
