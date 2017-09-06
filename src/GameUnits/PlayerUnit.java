package GameUnits;

import GameInterface.App.SpaceShooter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Jordan on 2017-07-13.
 */
public class PlayerUnit extends GameItem {

    private static final int BASE_SPEED = 10;
    private static final int HEIGHT = 68;
    private static final int WIDTH = 70;
    private static final int SCREEN_HEIGHT = 5;
    private SpaceShooter app;
    private static final int SPEED_INCREASE_MULTIPLIER = 3; // speed increase on level up
    private int dx;

    public static final String PLAYER_GREEN = "src/Images/PlayerGreen.png";
    public static final String PLAYER_RED = "src/Images/PlayerRed.png";
    public static final String PLAYER_BLUE = "src/Images/PlayerBlue.png";
    public static final String PLAYER_PURPLE = "src/Images/PlayerPurple.png";
    private Image playerGreen;
    private Image playerBlue;
    private Image playerRed;
    private Image playerPurple;

    private ImageIcon playerIcon;
    private Image playerImage;


    public PlayerUnit(int xpos, SpaceShooter app) {
        super(xpos, (SpaceShooter.GAME_AREA_HEIGHT - HEIGHT - SCREEN_HEIGHT), HEIGHT, WIDTH, BASE_SPEED, 1);
        this.app = app;
        loadImages();
        playerImage = playerRed;
        this.height = playerIcon.getIconHeight();
        this.width = playerIcon.getIconWidth();
        this.ypos = SpaceShooter.GAME_AREA_HEIGHT - height - SCREEN_HEIGHT;
    }

    private void loadImages(){
        playerIcon = new ImageIcon(PLAYER_GREEN);
        playerGreen = playerIcon.getImage();
        playerIcon = new ImageIcon(PLAYER_BLUE);
        playerBlue = playerIcon.getImage();
        playerIcon = new ImageIcon(PLAYER_RED);
        playerRed = playerIcon.getImage();
        playerIcon = new ImageIcon(PLAYER_PURPLE);
        playerPurple = playerIcon.getImage();
    }

    public void setColour(String colour){
        if (colour.equals("Red"))
            playerImage = playerRed;
        else if (colour.equals("Blue"))
            playerImage = playerBlue;
        else if (colour.equals("Green"))
            playerImage = playerGreen;
        else
            playerImage = playerPurple;
    }

    public void levelUp(){
        level++;
        updateStats();
    }

    public void updateStats() {
        speed = BASE_SPEED + SPEED_INCREASE_MULTIPLIER * level;
    }

    @Override
    public void draw(Graphics graphics) {
        Color savedCol = graphics.getColor();
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(playerImage, (xpos - (width / 2)), ypos, app.getGameArea());
        graphics.setColor(savedCol);
    }

    @Override
    public void move() {
        xpos += dx;
        keepInBounds();
    }

    public void moveLeft(){
        dx = speed * -1;
    }

    public void moveRight() {
        dx = speed;
    }

    public void stopMovement(){
        dx = 0;
    }

    public int getLevel(){
        return level;
    }
}
