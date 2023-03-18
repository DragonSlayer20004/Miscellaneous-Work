import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Ship extends Polygon{

    private int width;
    private int height;

    final private double acceleration = 5;
    private double speed;
    private Point pull = new Point(0, 0);

    private int[] xpoints;
    private int[] ypoints;
    private char keyPressed;
    private boolean pressingForwardKey;
    private boolean pressingBackKey;
    private boolean pressingLeftKey;
    private boolean pressingRightKey;

    public boolean firingBullet;

    //Powerups
    public boolean tripleFire = false;
    public boolean burstFire = false;
    public boolean immortality = false;

    public Ship(Point[] shape, Point position, double rotation, int w, int h) {
        super(shape, position, rotation);
        width = w;
        height = h;
    }

    public void paint(Graphics brush) {
        brush.setColor(Color.white);
        Point[] shipShape=getPoints();
        xpoints = new int[shipShape.length];
        ypoints = new int[shipShape.length];
        for(int i = 0; i<shipShape.length; i++) {
            xpoints[i] = (int)shipShape[i].getX();
            ypoints[i] = (int)shipShape[i].getY();
        }

        brush.fillPolygon(xpoints,ypoints,shipShape.length);
        //brush.drawLine(100,200,300,400);
    }

    public void move() {
        //Slow down acceleration
        if(pull.getY()>0){
            pull.setY(pull.getY()-.05);
        }
        if(pull.getY()<0){
            pull.setY(pull.getY()+.05);
        }
        if(pull.getX()>0){
            pull.setX(pull.getX()-.05);
        }
        if(pull.getX()<0){
            pull.setX(pull.getX()+.05);
        }

        //Accelerate Ship
        if(pressingForwardKey) {
            accelerate(-.2);
        }
        if(pressingBackKey) {
            accelerate(+.2);
        }
        //Turn Ship
        if(pressingRightKey) {
            rotation+=1;
        }
        if(pressingLeftKey) {
            rotation-=1;
        }
        //Move Ship Position
        position.setY(position.getY()+ pull.getY());
        position.setX(position.getX()+ pull.getX());

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

    public void accelerate (double acceleration) {
        int limit = 4;
        pull.setX(pull.getX() + (acceleration * Math.cos(Math.toRadians(rotation))));
        pull.setY(pull.getY() + (acceleration * Math.sin(Math.toRadians(rotation))));

        if(pull.getY()>limit) {
            pull.setY(limit);
        }
        if(pull.getX()>limit) {
            pull.setX(limit);
        }
        if(pull.getY()<-limit) {
            pull.setY(-limit);
        }
        if(pull.getX()<-limit) {
            pull.setX(-limit);
        }
    }

    KeyListener playerInput = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            //Make the ship instantiate a bullet
            if(e.getKeyCode() == 32){
                firingBullet = true;
            }
            //Enable Triple Fire Cheat
            if(e.getKeyCode() == 69){
                if(tripleFire){
                    System.out.println("Triple Fire Disabled");
                    tripleFire = false;
                }else {
                    System.out.println("Triple Fire enabled");
                    tripleFire = true;
                }
            }
            //Enable Burst Fire Cheat
            if(e.getKeyCode() == 82){
                if(burstFire){
                    System.out.println("Burst Fire Disabled");
                    burstFire = false;
                }else {
                    System.out.println("Burst Fire enabled");
                    burstFire = true;
                }
            }
            //Enable Immortality Cheat
            if(e.getKeyCode() == 73){
                if(immortality){
                    System.out.println("immortality Disabled");
                    immortality = false;
                }else {
                    System.out.println("immortality enabled");
                    immortality = true;
                }
            }
            //Up Key
            if(e.getKeyCode()==87|| e.getKeyCode() == 38){
                pressingForwardKey = true;
            }
            //Down Key
            if(e.getKeyCode()==83|| e.getKeyCode() == 40){
                pressingBackKey = true;
            }
            //Left Key
            if(e.getKeyCode()==65|| e.getKeyCode() == 37){
                pressingLeftKey = true;
            }
            //Right Key
            if(e.getKeyCode()==68|| e.getKeyCode() == 39){
                pressingRightKey = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //Make the ship instantiate a bullet
            if(e.getKeyCode() == 32){
                firingBullet = false;
            }
            if(e.getKeyCode()==87|| e.getKeyCode() == 38){
                pressingForwardKey = false;
            }
            //Down Key
            if(e.getKeyCode()==83|| e.getKeyCode() == 40){
                pressingBackKey = false;
            }
            //Left Key
            if(e.getKeyCode()==65|| e.getKeyCode() == 37){
                pressingLeftKey = false;
            }
            //Right Key
            if(e.getKeyCode()==68|| e.getKeyCode() == 39){
                pressingRightKey = false;
            }
        }
    };
}
