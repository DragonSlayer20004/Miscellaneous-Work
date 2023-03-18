import java.awt.*;
import java.security.InvalidParameterException;
import java.util.Random;

public class Star extends Circle{
    Color color;
    private int width;
    private int height;
    int ypoint;
    int xpoint;
    final double SPEED = .05;
    public Star(Point position, double rotation, int w, int h){
        super(position, rotation);
        Random random = new Random();
        width = w;
        height = h;

        int colorChoice = random.nextInt(0,4);
        switch (colorChoice){
            case 0:
                color = Color.blue;
                break;
            case 1:
                color = Color.orange;
                break;
            case 2:
                color = Color.green;
                break;
            case 3:
                color = Color.yellow;
                break;
            case 4:
                color = Color.pink;
                break;
            default:
                throw new InvalidParameterException(color.toString());
        }
    }
    public void paint(Graphics brush) {
        brush.setColor(color);

        //Position Point
        ypoint = (int)position.getY();
        xpoint = (int)position.getX();

        brush.fillOval(xpoint,ypoint,2,2);
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
    }
}
