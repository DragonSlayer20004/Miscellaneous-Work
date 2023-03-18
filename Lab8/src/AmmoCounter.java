import java.awt.*;

public class AmmoCounter extends Polygon{
    /**
     * Add a specification of the constructor here...
     *
     * @param shape
     * @param position
     * @param rotation
     */
    private int[] xpoints;
    private int[] ypoints;

    public AmmoCounter(Point[] shape, Point position, double rotation) {
        super(shape, position, rotation);
    }
    public void paint(Graphics brush, int ammoCount) {
        brush.setColor(Color.blue);
        Point[] ammoCounterShape=getPoints();
        xpoints = new int[ammoCounterShape.length];
        ypoints = new int[ammoCounterShape.length];
        for(int i = 0; i<ammoCounterShape.length; i++) {
            xpoints[i] = (int)ammoCounterShape[i].getX();
            if(i == 1 || i ==2) {
                ypoints[i] = (int) ammoCounterShape[i].getY() * ammoCount;
            }else {
                ypoints[i] = (int) ammoCounterShape[i].getY();
            }
        }

        brush.fillPolygon(xpoints,ypoints,ammoCounterShape.length);
        //brush.drawLine(100,200,300,400);
    }
    public void ammoChange(int ammoCount){
        ypoints[1] = ammoCount*10;
        ypoints[2] = ammoCount*10;
    }
}
