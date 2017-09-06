package GameUnits;

import GameInterface.App.SpaceShooter;

import java.awt.*;

/**
 * Created by Jordan on 2017-07-13.
 */
public abstract class GameItem {

    protected int xpos;
    protected int ypos;
    protected int height;
    protected int width;
    protected int speed;
    protected int level;


    public GameItem(int xpos, int ypos, int height, int width, int speed, int level){
        this.xpos = xpos;
        this.ypos = ypos;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.level = level;
    }

    public void keepInBounds(){
        if(this.xpos > SpaceShooter.SCREEN_WIDTH - 10)
            this.xpos = SpaceShooter.SCREEN_WIDTH - 10;
        if(this.xpos < 10)
            this.xpos = 10;
    }

    public abstract void move();

    public abstract void draw(Graphics graphics);
}
