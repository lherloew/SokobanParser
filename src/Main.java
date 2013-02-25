import java.util.LinkedList;


public class Main {

   /**
    * @param args
    */
   public static void main(String[] args) {
      // lets have an list of objects for convenience.
      LinkedList<SokoObject> list = new LinkedList<SokoObject>();
      Parser.openFile("/Users/lasse/Dropbox/DTU/AI/mandatory1/assignment1-sokoban-levels/l1-test.sok", list);
      ProblemBuilder.buildProblemFile(list);

   }

}
