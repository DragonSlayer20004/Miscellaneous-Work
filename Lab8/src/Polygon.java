/**
 * Polygon is a sequence of points in space defined by the points
 * themselves, an offset, and a rotation. The offset is the
 * distance between the origin and the center of the shape.
 * The rotation is measured in degrees, 0-360.
 *
 * You are intended to instantiate this class with a set of points that
 * forever defines its shape, and then modify it by repositioning and
 * rotating that shape. In defining the shape, the relative positions
 * of the points you provide are used, in other words: {(0,1),(1,1),(1,0)}
 * is the same shape as {(9,10),(10,10),(10,9)}.
 * NOTE: You don't need to worry about the "magic math" details.
 */
class Polygon extends Shape{
   /**
    * Add a specification of the constructor here...
    */
   public Polygon (Point[] shape, Point position, double rotation) {
      this.shape = shape;
      this.position = position;
      this.rotation = rotation;
      
      // First, we find the shape's top-most left-most boundary, its origin.
      Point origin =  new Point(shape[0].getX(), shape[0].getY());
      for (Point p : shape) {
         if (p.getX() < origin.getX()) {
            origin.setX(p.getX());
         }
         if (p.getY() < origin.getY()) {
            origin.setY(p.getY());
         }
      }
      
      // Then, we orient all of its points relative to the real origin.
      for (Point p : shape) {
         p.setX(p.getX() - origin.getX());
         p.setY(p.getY() - origin.getY());
      }
   }
}
