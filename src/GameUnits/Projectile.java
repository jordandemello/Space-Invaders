package GameUnits;

import java.awt.*;

/**
 * Created by Jordan on 2017-07-13.
 */
public class Projectile extends GameItem{

    private static final int BASE_WIDTH = 6;
    private static final int BASE_HEIGHT = 10;
    private static final int BASE_SPEED = 10;
    private static final int BASE_DAMAGE = 3;
    private static final Color PROJECTILE_COLOUR = new Color(0, 128, 250);

    private int damage;


    public Projectile(int xpos, int ypos, int level) {
        super(xpos, ypos, (BASE_HEIGHT + (1 * (level - 1))), (BASE_WIDTH + (4 * (level - 1))), (BASE_SPEED + (3 * (level - 1))), level);
        this.damage = (BASE_DAMAGE + (2 * (level - 1)));
    }

    @Override
    public void draw(Graphics graphics) {
        Color savedCol = graphics.getColor();
        graphics.setColor(PROJECTILE_COLOUR);
        graphics.fillRect(xpos - (width / 2), ypos - (height / 2), width, height);
        graphics.setColor(savedCol);
    }

    @Override
    public void move(){
        ypos = ypos - speed;
    }

    public boolean isOutOfBounds(){
        return (ypos < 0);
    }

    public boolean isColliding(Enemy enemy){
        Point projectileTL = new Point(this.xpos - (this.width / 2), this.ypos -(this.height / 2));
        Dimension projectileDim = new Dimension(this.width, this.height);
        Rectangle projectileRect = new Rectangle(projectileTL, projectileDim);

        Point enemyTL = new Point(enemy.xpos - (enemy.width / 2), enemy.ypos -(enemy.height / 2));
        Dimension enemyDim = new Dimension(enemy.width, enemy.height);
        Rectangle enemyRect = new Rectangle(enemyTL, enemyDim);

        return projectileRect.intersects(enemyRect);
    }

    public int getDamage(){
        return damage;
    }
}
