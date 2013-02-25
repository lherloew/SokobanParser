import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Parser {
   public static final String NEW_LINE = System.getProperty("line.separator");

   public static void openFile(String filename, LinkedList<SokoObject> list) {
      int xpos = -1, ypos = 0;

      try{
         // Open the file that is the first 
         // command line parameter
         FileInputStream fstream = new FileInputStream(filename);
         // Get the object of DataInputStream
         DataInputStream in = new DataInputStream(fstream);
         BufferedReader br = new BufferedReader(new InputStreamReader(in));

         int c;
         int i = 0;
         
         while((c = br.read()) != -1) {
            char character = (char) c;

            // now keep track of number of characters (basically y position)
            xpos++;
            // tell us which line we are on, basically x position
            if(Character.toString(character).equals(NEW_LINE)) {
               ypos++;
               xpos = -1; // reset y coordinate because of new line
               list.add(new SokoObject("null", i, xpos, ypos, false));
            }

            if(character == '@') {
               //System.out.println("player found at (x,y): " + xpos + ", " + ypos);
               list.add(new SokoObject("player", i, xpos, ypos, false));
            }
            if(character == '#') {
               //System.out.println("wall found at (x, y): " + xpos + ", " + ypos);
               list.add(new SokoObject("wall", i, xpos, ypos, false));
            }
            if(character == '$') {
               //System.out.println("box found at (x,y): " + xpos + ", " + ypos);
               list.add(new SokoObject("box", i, xpos, ypos, false));
            }
            if(character == '.') {
               //System.out.println("goal found at (x,y): " + xpos + ", " + ypos);
               list.add(new SokoObject("goal", i, xpos, ypos, true));
            }
            if(Character.isWhitespace(character) && !Character.toString(character).equals(NEW_LINE)) {
               //System.out.println("floor found at (x,y): " + xpos + ", " + ypos);
               list.add(new SokoObject("floor", i, xpos, ypos, false));
            }
            if(character == '*') {
               //System.out.println("box on top of goal found at (x,y): " + xpos + ", " + ypos);
               list.add(new SokoObject("box", i, xpos, ypos, true));
            }
            if(character == '+') {
               //System.out.println("player on top of goal found at (x,y): " + xpos + ", " + ypos);
               list.add(new SokoObject("player", i, xpos, ypos, true));
            }
            i++;
         } // end while
         
         //Close the input stream
         in.close();
      }
      catch (Exception e){//Catch exception if any
         System.err.println("Error: " + e.getMessage());
      }
      
   }
}
