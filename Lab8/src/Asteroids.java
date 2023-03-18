import java.awt.*;
import java.util.Random;

/**
 * Asteroids is a subclass of Game in which all the important work is all in the paint method.
 * NOTE: This class is the metaphorical "main method" of your program; it is your control center.
 */
class Asteroids extends Game {
   private int width = 800;
   private int height = 600;
   private boolean playerAllowedToFire = true;
   private final int PLAYERSTANDARDATTACKSPEED = 15;
   private final int PLAYERBURSTATTACKSPEED = 5;
   private final int PLAYERTRIPLEATTACKSPEED = 25;
   private int playerAttackSpeedTimer;
   private int currentActiveBulletCount = 0;
   Ship playerShip;
   AmmoCounter ammoCounter;
   Asteroid[] asteroidEnemies;
   Bullet[] playerBullets;
   Bullet[] enemyBullets;
   Star[] starCount;
   public Asteroids() {
      super("Asteroids!",800,600);
      this.setFocusable(true);
      this.requestFocus();

      //Create Ship
      createShip();

      //create Asteroids
      asteroidEnemies = new Asteroid[10];
      for(int i = 0; i < asteroidEnemies.length; i++){
         asteroidEnemies[i] = createAsteroid();
      }

      //create Stars
      starCount = new Star[1000];
      for(int i = 0; i < starCount.length; i++){
         starCount[i] = createStar();
      }

      //create Bullets
      playerBullets = new Bullet[10];
      for(int i = 0; i < playerBullets.length; i++){
         playerBullets[i] = createBullet();
      }
      //create ammoCounter
      ammoCounter = createAmmoCounter();
   }

   private void createShip() {
      Point[] shipBuild = new Point[11];
      shipBuild[0] = new Point(20,0);
      shipBuild[1] = new Point(40,5);
      shipBuild[2] = new Point(20,10);
      shipBuild[3] = new Point(20,20);
      shipBuild[4] = new Point(40,25);
      shipBuild[5] = new Point(20,30);
      shipBuild[6] = new Point(10,25);
      shipBuild[7] = new Point(0,20);
      shipBuild[8] = new Point(0,10);
      shipBuild[9] = new Point(10,5);
      shipBuild[10] = new Point(20,0);

      Point originPoint = new Point(width/2, height/2);

      playerShip = new Ship(shipBuild, originPoint, 90, width, height );
      this.addKeyListener(playerShip.playerInput);
   }

   public void paint(Graphics brush) {
      brush.setColor(Color.black);
      brush.fillRect(0,0,width,height);

      //Star Movement
      for(int i = 0; i < starCount.length; i++) {
         //move every individual star
         starCount[i].paint(brush);
         starCount[i].move();
      }

      //Player Movement
      playerShip.paint(brush);
      playerShip.move();

      //Bullet Movement
      for(int i = 0; i < playerBullets.length; i++) {
         //move every individual bullet
         if(playerBullets[i].enabled){
            playerBullets[i].paint(brush);
            playerBullets[i].move();
         }
      }


      //Player Shooting Bullet
      //Determine whether player is allowed to attack based on attack speed
      if(playerAttackSpeedTimer<=0){
         playerAllowedToFire=true;
      }else {
         playerAttackSpeedTimer--;
      }
      //reset bullet count to 0
      currentActiveBulletCount = 0;
      for(int i = 0; i < playerBullets.length; i++) {
         //Ammo Counter For Player
         if(playerBullets[i].enabled) {
            currentActiveBulletCount++;
         }
      }
      if(currentActiveBulletCount>=10){
         playerAllowedToFire = false;
      }

      if(playerShip.firingBullet && playerAllowedToFire){

         //disable player fire
         playerAllowedToFire = false;
         for(int i = 0; i < playerBullets.length; i++) {
            playerAttackSpeedTimer= PLAYERSTANDARDATTACKSPEED;
            //locate forward firing position
            Point firingSpot = playerShip.position;
            //search for disabled bullet to fire
            if(!playerBullets[i].enabled){
               playerBullets[i].bullettimer = 0;
               //put bullet in front of ship
               playerBullets[i].position = new Point(firingSpot.getX(), firingSpot.getY());
               playerBullets[i].rotation = playerShip.rotation+180;
               playerBullets[i].enabled=true;
               break;
            }
         }
      }
//Asteroid Movement
      for(int i = 0; i < asteroidEnemies.length; i++){
         //move every individual asteroid
         if(asteroidEnemies[i].enabled){
            asteroidEnemies[i].paint(brush);
            asteroidEnemies[i].move();

            //Tests to see whether an asteroid point is within the ship
            if(playerShip.intersects(asteroidEnemies[i])&&!playerShip.immortality) {
               System.out.println("YOU DIED NOOB");
               on = false;
               //Display Defeat Screen
               brush.setColor(Color.red);
               brush.setFont(new Font("TimesRoman", Font.PLAIN, 40));
               brush.drawString("Game Over", width/2-100, height/2);
               break;
            }
            //Tests to see whether a bullet is withing the asteroid
            for(int j = 0; j < playerBullets.length; j++) {
               if (playerBullets[j].enabled){
                  if (asteroidEnemies[i].intersects(playerBullets[j])) {
                     System.out.println("Kaboom");
                     //Disable asteroid
                     asteroidEnemies[i].enabled = false;
                     playerBullets[j].enabled = false;
                  }
               }
            }
         }
      }
      //Ammo Counter
      ammoCounter.paint(brush, 10-currentActiveBulletCount);

   }
  
   public static void main (String[] args) {
      Asteroids a = new Asteroids();
      a.repaint();
   }

   public Asteroid createAsteroid() {
      Random random = new Random();
      int direction = random.nextInt(0,360);
      int corners = random.nextInt(4,7);
      int pointChange = 0;
      Point[] asteroidBuild = new Point[corners];

      //generate points
      for (int i = 0; i<corners; i++){
         if(i<corners/2) {
            asteroidBuild[i] = new Point(random.nextInt(-50 + pointChange, -40 + pointChange), random.nextInt(0 + pointChange, 20 + pointChange / 2));
            pointChange += 10;
         } else {
            asteroidBuild[i] = new Point(random.nextInt(-50 + pointChange, -40 + pointChange), random.nextInt(-20 - pointChange / 2, 0 - pointChange));
            pointChange -= 10;
         }
      }

      Point originPoint = new Point(random.nextInt(0,width), random.nextInt(0,height));
      return new Asteroid(asteroidBuild, originPoint, direction, width, height );
   }
   public Star createStar() {
      Random random = new Random();
      Point originPoint = new Point(random.nextInt(0,width), random.nextInt(0,height));
      return new Star(originPoint, 10, width, height);
   }

   public Bullet createBullet() {
      Random random = new Random();
      Point originPoint = new Point(random.nextInt(0,width), random.nextInt(0,height));
      return new Bullet(originPoint, 10, width, height);
   }
   public AmmoCounter createAmmoCounter() {
      Point[] ammoBuild = new Point[4];
      Point originPoint = new Point(width-20, 0);

      ammoBuild[0] = new Point(width - 20, 0);
      ammoBuild[1] = new Point(width - 20, 60);
      ammoBuild[2] = new Point(width, 60);
      ammoBuild[3] = new Point(width, 0);
      return new AmmoCounter(ammoBuild, originPoint, 0);
   }
}