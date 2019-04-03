import java.io.*;
import java.util.*;

class Timer
{  Scanner keyboard = new Scanner(System.in);
   PrintWriter screen = new PrintWriter(System.out,true);

   void pause(long millisecs)
   {  long current = Calendar.getInstance().getTimeInMillis();
      while(Calendar.getInstance().getTimeInMillis()-current<millisecs);
   }

   void Timer1() throws IOException
   {  screen.println("Enter time");
      int time = Integer.parseInt(keyboard.nextLine());
      while(time>0)
      {  screen.println(time);
         pause(1000);
         time--;
      }
   }
}


