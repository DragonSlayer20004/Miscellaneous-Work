public abstract class Shape {
    public Point[] shape;   // An array of points.
    public Point position;   // The offset mentioned above.
    public double rotation;  // Zero degrees is due east.
    private double findArea() {
        double sum = 0;
        for (int i = 0, j = 1; i < shape.length; i++, j = (j+1) % shape.length) {
            sum += shape[i].getX() * shape[j].getY() - shape[j].getX() * shape[i].getY();
        }
        return Math.abs(sum / 2);
    }
    private Point findCenter() {
        Point sum = new Point(0,0);
        for (int i = 0, j = 1; i < shape.length; i++, j= (j+1) % shape.length) {
            sum.setX(sum.getX() + (shape[i].getX() + shape[j].getX())
                    * (shape[i].getX() * shape[j].getY() - shape[j].getX() * shape[i].getY()));
            sum.setY(sum.getY() + (shape[i].getY() + shape[j].getY())
                    * (shape[i].getX() * shape[j].getY() - shape[j].getX() * shape[i].getY()));
        }
        double area = findArea();
        return new Point(Math.abs(sum.getX() / (6 * area)), Math.abs(sum.getY() / (6 * area)));
    }
    public Point[] getPoints() {
        int i = 0;
        Point center = findCenter();
        Point[] points = new Point[shape.length];
        for (Point p : shape) {
            double x = ((p.getX()-center.getX()) * Math.cos(Math.toRadians(rotation)))
                    - ((p.getY()-center.getY()) * Math.sin(Math.toRadians(rotation)))
                    + center.getX()/2 + position.getX();
            double y = ((p.getX()-center.getX()) * Math.sin(Math.toRadians(rotation)))
                    + ((p.getY()-center.getY()) * Math.cos(Math.toRadians(rotation)))
                    + center.getY()/2 + position.getY();
            points[i++] = new Point(x,y);
        }
        return points;
    }
    /**
     * contains implements some magical math (i.e. the ray-casting algorithm)
     */
    public boolean contains(Point point) {
        Point[] points = getPoints();
        double crossingNumber = 0;
        for (int i = 0, j = 1; i < shape.length; i++, j=(j+1)%shape.length) {
            if ((((points[i].getX() < point.getX()) && (point.getX() <= points[j].getX())) ||
                    ((points[j].getX() < point.getX()) && (point.getX() <= points[i].getX()))) &&
                    (point.getY() > points[i].getY() + (points[j].getY()-points[i].getY())/
                            (points[j].getX() - points[i].getX()) * (point.getX() - points[i].getX()))) {
                crossingNumber++;
            }
        }
        return crossingNumber % 2 == 1;
    }
    boolean intersects(Shape poly){
        //Get Points of foreign shape and test whether they intersect
        Point[] polyPoints = poly.getPoints();
        //return true if foreign point is within current shape
        for(int i = 0; i<polyPoints.length; i++) {
            if (contains(polyPoints[i])) {
                return true;
            }
        }
        return false;
    }
}
