package GameUnits;

import GameInterface.App.SpaceShooter;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Jordan on 2017-07-14.
 */
public class EnemyBasic extends Enemy{

    private static final int BASE_HEALTH = 3;
    private static final int BASE_SPEED = 1;
    private static final int BASE_WIDTH = 30;
    private static final int BASE_HEIGHT = 31;
    private static final String ENEMY_IMAGE = "src/Images/Enemy.png";
    private static final ImageIcon enemyIcon = new ImageIcon(ENEMY_IMAGE);
    private SpaceShooter app;

    public EnemyBasic(int xpos, int ypos, int level, SpaceShooter app) {
        super(xpos, ypos, BASE_HEIGHT - level, BASE_WIDTH - level, BASE_SPEED + level, level, BASE_HEALTH + (1 * (level - 1)));
        this.app = app;
       }

    @Override
    public void draw(Graphics graphics) {
        Color savedCol = graphics.getColor();
        Image enemyImage = enemyIcon.getImage();
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(enemyImage, xpos - (width / 2), ypos - (height / 2), app.getGameArea());
        graphics.setColor(savedCol);
    }
}