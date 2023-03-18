import java.awt.*;
import java.util.Random;

public class Asteroid extends Polygon{
    public boolean enabled = true;
    private int width;
    private int height;
    private double speed = 1;
    private int[] xpoints;
    private int[] ypoints;
    public int asteroidSize;
    
    
    public Asteroid(Point[] shape, Point position, double rotation, int w, int h) {
        super(shape, position, rotation);
        width = w;
        height = h;
        Random random = new Random();
        speed = random.nextDouble(.2,3);
    }

    public void paint(Graphics brush) {
        brush.setColor(Color.white);
        Point[] asteroidShape=getPoints();
        xpoints = new int[asteroidShape.length];
        ypoints = new int[asteroidShape.length];
        for(int i = 0; i<asteroidShape.length; i++) {
            xpoints[i] = (int)asteroidShape[i].getX();
            ypoints[i] = (int)asteroidShape[i].getY();
        }

        brush.fillPolygon(xpoints,ypoints,asteroidShape.length);
    }

    public void move() {
        position.setX(position.getX()+ speed * Math.cos(Math.toRadians(rotation)));
        position.setY(position.getY()+ speed * Math.sin(Math.toRadians(rotation)));

        //Set ship to other side of boundary
        if(position.getY()>height){
            position.setY(position.getY()-height);
        }
        if(position.getY()<0){
            position.setY(position.getY()+height);
        }
        if(position.getX()>width){
            position.setX(position.getX()-width);
        }
        if(position.getX()<0){
            position.setX(position.getX()+width);
        }
    }
}
