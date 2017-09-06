package GameInterface.Panels;

import GameInterface.App.SpaceShooter;
import GameUnits.SpaceGame;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jordan on 2017-07-17.
 */
public class GameArea extends JPanel implements Observer{

    private SpaceGame game;
    private static final String BACKGROUND_IMAGE = "src/Images/spaceBackground.png";
    private static final ImageIcon backgroundIcon = new ImageIcon(BACKGROUND_IMAGE);
    public static final String UPDATE_GAME_AREA = "Game Area";
    private Image backgroundImage;


    public GameArea(SpaceGame game){
        this.game = game;
        backgroundImage = backgroundIcon.getImage();
        setPreferredSize(new Dimension(SpaceShooter.SCREEN_WIDTH, SpaceShooter.GAME_AREA_HEIGHT));
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        graphics.drawImage(backgroundImage, 0, 0, null);
        game.draw(graphics);
    }

    @Override
    public void update(Observable o, Object arg) {
        String message = (String) arg;
        if(message.equals(UPDATE_GAME_AREA))
            repaint();
    }
}
