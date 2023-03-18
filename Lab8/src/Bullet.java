import java.awt.*;

public class Bullet extends Circle{
    public boolean enabled = false;
    private int width;
    private int height;
    private int ypoint;
    private int xpoint;
    int bullettimer = 0;
    private final int TIMEROFF =100;
    private final double SPEED = 7.5;
    public Bullet(Point position, double rotation, int w, int h){
        super(position, rotation);
        width = w;
        height = h;

        this.shape = new Point[4];
        this.shape[0] = new Point(0,0);
        this.shape[1] = new Point(-5,5);
        this.shape[2] = new Point(0,10);
        this.shape[3] = new Point(5,5);
    }
    public void paint(Graphics brush) {
        brush.setColor(Color.red);

        //Position Point
        ypoint = (int)position.getY();
        xpoint = (int)position.getX();

        brush.fillOval(xpoint,ypoint,5,10);
        //brush.drawLine(100,200,300,400);
    }

    public void move() {
        //Move star according to rotation
        position.setX(position.getX()+ SPEED * Math.cos(Math.toRadians(rotation)));
        position.setY(position.getY()+ SPEED * Math.sin(Math.toRadians(rotation)));

        //Set star to other side of boundary
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
        //Turn bullet off after a percent of time
        bullettimer++;
        if(bullettimer>TIMEROFF){
            enabled = false;
        }
    }
}
