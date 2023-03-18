import java.awt.*;
import java.awt.event.*;

/**
 * Game is a abstract class that provides a painted canvas in its own window, updated 10 times per second.
 * USAGE: Extended by Asteroids, which is a concrete subclass that implements the paint() method.
 * NOTE: You don't need to understand the details here. DO NOT EDIT THIS CLASS!
 */
abstract class Game extends Canvas {
   protected boolean on;
   protected int width, height;
   protected Image buffer;
  
  /**
   * The constructor for Game initializes the window
   * @param name is the name that will be displayed in the window title bar
   * @param width is the width of the window in pixels
   * @param height is the height of the window in pixels
   */
   public Game(String name, int width, int height) {
      this.width = width;
      this.height = height;
      on = true;
     
      // A Frame is a 'window' object.
      Frame frame = new Frame(name);
      frame.add(this);
      frame.setSize(width, height);
      frame.setVisible(true);
      frame.setResizable(false);
      // Enable the 'close window' button at the right end of the title bar
      frame.addWindowListener(
         new WindowAdapter() { 
            public void windowClosing(WindowEvent e) {
               System.exit(0);
            } 
         });
    
      buffer = createImage(width, height);
   }
  
  /**
   * paint will be called every tenth of a second that the game is on
   * to redraw the window's image buffer
   */
   abstract public void paint(Graphics brush);
  
  /**
   * update paints to a buffer then to the screen, then waits a tenth of
   * a second before repeating itself, assuming the game is on. This is done
   * to avoid a choppy painting experience if repainted in pieces.
   */
   public void update(Graphics brush) {
      paint(buffer.getGraphics());
      brush.drawImage(buffer, 0, 0, this);
      if (on) {
         sleep(10);
         repaint();
      }
   }
  
  /**
   * sleep is a simple helper function used in update
   */
   private void sleep(int time) {
      try {
         Thread.sleep(time);
      } 
      catch(Exception exc){
      
      };
   }
}
