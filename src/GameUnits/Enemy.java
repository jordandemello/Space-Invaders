package GameUnits;

import java.util.Random;

/**
 * Created by Jordan on 2017-08-18.
 */
public abstract class Enemy extends GameItem{
    private int health;
    private int wiggle;


    public Enemy(int xpos, int ypos, int height, int width, int speed, int level, int health) {
        super(xpos, ypos, height, width, speed, level);
        this.health = health;
        wiggle = level;
    }

    @Override
    public void move() {
        Random random = new Random();
        int var = random.nextInt(wiggle + 1);
        xpos = xpos + (2*var) - wiggle; // can move up to wiggle pixels l/r

        ypos = ypos + speed;
        keepInBounds();
    }

    public int getHealth(){
        return health;
    }

    public void damage(int damageTaken){
        health -= damageTaken;
    }
}
