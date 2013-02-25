
public class SokoObject {
   
   private String type = "";
   private int id;
   private int xpos;
   private int ypos;
   private boolean onGoal;
   
   public SokoObject(String type, int id, int xpos, int ypos, boolean isOnGoal) {
      this.type = type;
      this.id = id;
      this.xpos = xpos;
      this.ypos = ypos;
      if (isOnGoal) {
         onGoal = true;
      }
   }
   
   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getType() {
      return type;
   }
   public void setType(String type) {
      this.type = type;
   }
   public int getXpos() {
      return xpos;
   }
   public void setXpos(int xpos) {
      this.xpos = xpos;
   }
   public int getYpos() {
      return ypos;
   }
   public void setYpos(int ypos) {
      this.ypos = ypos;
   }
   
   public String toString() {
      return type;
   }

   public Boolean isOnGoal() {
      boolean onGoal = this.onGoal;
      if (onGoal ) {
         return true;
      } else {
         return false;
      }
   }

}
