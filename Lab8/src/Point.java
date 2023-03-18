/**
 * Point is a simple (x,y) coordinate pair representing
 * a location in a graphics window
 */
class Point{
   private double x, y;
   
   public Point (double x, double y) { 
      this.x = x; 
      this.y = y; 
   }
   
   public double getX() {
      return x;
   }
   
   public double getY() {
      return y;
   }
   
   public void setX(double x) {
      this.x = x;
   }
      
   public void setY(double y) {
      this.y = y;
   }

   @Override
   public String toString() {
      return super.toString();
   }
}
