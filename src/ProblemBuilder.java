import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author lasse
 *
 */
public class ProblemBuilder {
   public static void buildProblemFile(LinkedList<SokoObject> list) {
      // find players, boxes, goals and floors and keep track of them in separate lists.
      LinkedList<SokoObject> playerList = new LinkedList<SokoObject>();
      LinkedList<SokoObject> boxList = new LinkedList<SokoObject>();
      LinkedList<SokoObject> goalList = new LinkedList<SokoObject>();
      LinkedList<SokoObject> floorList = new LinkedList<SokoObject>();
      TrackObjects.tracking(list, playerList, boxList, goalList, floorList);

      // Start constructing pddl problem file
      String problemName = "test";
      System.out.print("(:define (problem " + problemName + "\n");
      System.out.print("  (:domain sokoban)\n");

      System.out.print("  (:objects ");
      // DIRECTION objects
      System.out.print("\n    up - DIRECTION \n    down - DIRECTION \n    left - DIRECTION \n    right - DIRECTION " + "\n");

      // POSITION objects. This build our representation of the level.
      for(int i=0;i<list.size();i++) {
         if(list.get(i).getType() == "null") {

         } else {
            System.out.print("    pos-"+list.get(i).getXpos() + "-" + list.get(i).getYpos() + " - POSITION\n");
         }

      }
      // player objects
      for(int i=0;i<playerList.size();i++) {
         System.out.print("    " + playerList.get(i).getType() + playerList.get(i).getId() + " - PLAYER\n");
      }
      // box objects
      for(int i=0;i<boxList.size();i++) {
         System.out.print("    " + boxList.get(i).getType() + boxList.get(i).getId() + " - BOX\n");
      }
      System.out.print(" )\n"); // end objects

      System.out.print(" (:init \n");
      // player objects
      for(int i=0;i<playerList.size();i++) {
         System.out.print("   (AT " + playerList.get(i).getType() + playerList.get(i).getId() + " pos-" + playerList.get(i).getXpos() + "-" + playerList.get(i).getYpos() + ")\n");
      }
      // box objects
      for(int i=0;i<boxList.size();i++) {
         System.out.print("   (AT " + boxList.get(i).getType() + boxList.get(i).getId() + " pos-" + boxList.get(i).getXpos() + "-" + boxList.get(i).getYpos() + ")\n");
      }
      // goal objects 
      for(int i=0;i<goalList.size();i++) {
         System.out.print("   (IS-GOAL pos-" + goalList.get(i).getXpos() + "-" + goalList.get(i).getYpos() + ")\n");
      }
      // non-goal positions (all positions except goals)
      for(int i=0;i<list.size();i++) {
         if(list.get(i).getType() == "goal" || list.get(i).getType() == "null") {
            //don't print
         } else {
            System.out.print("   (NON-GOAL pos-" + list.get(i).getXpos() + "-" + list.get(i).getYpos() + ")\n");
         }      
      }   
      // floor objects
      for(int i=0;i<floorList.size();i++) {
         System.out.print("   (CLEAR pos-" + floorList.get(i).getXpos() + "-" + floorList.get(i).getYpos() + ")\n");
      }

      /**
       * MOVE CHECKER:
       * Check every position for possible moves. Currently only right and left :(
       */
      for(int i=0;i<list.size();i++) {
         int nextItem = i + 1;
         int prevItem = i - 1;

         /* if this position is a wall or the next are a wall, player, box we can't move */
         if(list.get(i).getType() == "wall" || list.get(nextItem).getType() == "wall" || list.get(nextItem).getType() == "player" || list.get(nextItem).getType() == "box" || list.get(nextItem).getType() == "null") {
            // right position is not available to move to
         } else {
            // right position can be moved to
            System.out.print("   (MOVE-TO pos-" + list.get(i).getXpos() + "-" + list.get(i).getYpos() + " pos-" + list.get(nextItem).getXpos() + "-" + list.get(nextItem).getYpos() + " right)\n" );
         }

         if(list.get(i).getType() == "wall" || list.get(prevItem).getType() == "wall" || list.get(prevItem).getType() == "player" || list.get(prevItem).getType() == "box" || list.get(prevItem).getType() == "null") {
            // left position is not available
         } else {
            // left position can be moved to
            System.out.print("   (MOVE-TO pos-" + list.get(i).getXpos() + "-" + list.get(i).getYpos() + " pos-" + list.get(prevItem).getXpos() + "-" + list.get(prevItem).getYpos() + " left)\n" );
         }


      }

      System.out.print(" )\n"); // end init


      // for goal building we need to figure out how many goals, if any, there are, so:
      int goals = 0;
      for(int i=0;i<goalList.size();i++) {
         goals++;
      }

      System.out.print(" (:goal");
      for(int i=0;i<goalList.size();i++) {
         // if we have more than one goal, we need to "AND"
         if(goals >= 1) {
            System.out.print(" (and \n");
         }
         for(int j=0;j<boxList.size();j++) {
            System.out.print("   (AT-GOAL " + boxList.get(j).getType() + boxList.get(j).getId() + ") ");
         }
      }

      if(goals >= 1) {
         System.out.print(" )"); // end "AND"
      }
      System.out.print(" )\n"); // end goal
      
      System.out.print(")"); // end problem

      //System.out.println("Huston, we have a problem!");


   }
}