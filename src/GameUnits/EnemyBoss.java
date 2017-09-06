package GameUnits;

import GameInterface.App.SpaceShooter;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Jordan on 2017-07-14.
 */
public class EnemyBoss extends Enemy{

    private static final int BASE_HEALTH = 50;
    private static final int BASE_SPEED = 1;
    private static final int BASE_WIDTH = 250;
    private static final int BASE_HEIGHT = 148;
    private static final String ENEMY_IMAGE = "src/Images/EnemyBoss.png";
    private static final ImageIcon enemyBossIcon = new ImageIcon(ENEMY_IMAGE);
    private SpaceShooter app;


    public EnemyBoss(int xpos, int ypos, int level, SpaceShooter app) {
        super(xpos, ypos, BASE_HEIGHT - level, BASE_WIDTH - level, BASE_SPEED, (20 + 10 * level), BASE_HEALTH + (20 * level));
        this.app = app;
    }

    @Override
    public void draw(Graphics graphics) {
        Color savedCol = graphics.getColor();
        Image enemyImage = enemyBossIcon.getImage();
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(enemyImage, xpos - (width / 2), ypos - (height / 2), app.getGameArea());
        graphics.setColor(savedCol);
    }
}