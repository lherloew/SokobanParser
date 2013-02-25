import java.util.LinkedList;


public class TrackObjects {

   public static void tracking(LinkedList<SokoObject> list, LinkedList<SokoObject> playerList, LinkedList<SokoObject> boxList, LinkedList<SokoObject> goalList, LinkedList<SokoObject> floorList) {
      for(int i=0;i<list.size();i++) {
         // find players
         if(list.get(i).getType().equals("player")) {
            //System.out.print(" player" + list.get(i).getId());
            playerList.add(list.get(i));
         }

         // find boxes and keep track of them
         if(list.get(i).getType().equals("box")) {
            //System.out.print(" box" + list.get(i).getId());
            boxList.add(list.get(i));
         }

         // find goals and keep track of them
         if(list.get(i).getType().equals("goal")) {
            //System.out.print(" goal" + list.get(i).getId());
            goalList.add(list.get(i));
         }

         // find unoccupied floor
         if(list.get(i).getType().equals("floor")) {
            //System.out.print(" floor" + list.get(i).getId());
            floorList.add(list.get(i));
         }

      }
   }
}
