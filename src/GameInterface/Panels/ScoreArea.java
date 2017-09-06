package GameInterface.Panels;

import GameInterface.App.SpaceShooter;
import GameUnits.SpaceGame;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jordan on 2017-07-17.
 */
public class ScoreArea extends JPanel implements Observer {
    private SpaceGame game;
    private JLabel labelKillCount, labelMissilesRemaining, labelInvadersOnScreen, labelInvadersRemaining, labelCurrency;
    public final static String UPDATE_SCORE_AREA = "Score Area";


    public ScoreArea(SpaceGame game){
        this.game = game;
        setLayout(new BorderLayout());

        addUIContent();

        setPreferredSize(new Dimension(SpaceShooter.SCREEN_WIDTH, SpaceShooter.SCORE_AREA_HEIGHT));
        setVisible(true);

    }

    private void addUIContent(){
        JPanel panelMain = new JPanel(new GridBagLayout());
        panelMain.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 100), 4));
        panelMain.setBackground(new Color(255, 255, 180));
        panelMain.setOpaque(true);
        add(panelMain, BorderLayout.CENTER);

        labelKillCount = new JLabel("Kill Count: " + game.getKillCount());
        labelKillCount.setHorizontalAlignment(SwingConstants.CENTER);
        labelKillCount.setPreferredSize(new Dimension(100, 60));
        labelKillCount.setBackground(new Color(255, 180, 162));
        labelKillCount.setOpaque(true);
        panelMain.add(labelKillCount);

        JLabel buffer1 = new JLabel();
        buffer1.setPreferredSize(new Dimension(40, 0));
        panelMain.add(buffer1);

        labelMissilesRemaining = new JLabel("Missiles Remaining: " + ((game.MAX_MISSILES_MULTIPLIER * game.getPlayerUnitLevel()) - game.getNumberOfMissiles()));
        labelMissilesRemaining.setHorizontalAlignment(SwingConstants.CENTER);
        labelMissilesRemaining.setPreferredSize(new Dimension(150, 60));
        labelMissilesRemaining.setBackground(new Color(255, 180, 162));
        labelMissilesRemaining.setOpaque(true);
        panelMain.add(labelMissilesRemaining);

        JLabel buffer2 = new JLabel();
        buffer2.setPreferredSize(new Dimension(40, 0));
        panelMain.add(buffer2);

        labelInvadersOnScreen = new JLabel("Invaders on Screen: " + game.getInvadersOnScreen());
        labelInvadersOnScreen.setHorizontalAlignment(SwingConstants.CENTER);
        labelInvadersOnScreen.setPreferredSize(new Dimension(150, 60));
        labelInvadersOnScreen.setBackground(new Color(255, 180, 162));
        labelInvadersOnScreen.setOpaque(true);
        panelMain.add(labelInvadersOnScreen);

        JLabel buffer3 = new JLabel();
        buffer3.setPreferredSize(new Dimension(40, 0));
        panelMain.add(buffer3);

        labelInvadersRemaining = new JLabel("Invaders Remaining: " + game.getInvadersLeftInRound());
        labelInvadersRemaining.setHorizontalAlignment(SwingConstants.CENTER);
        labelInvadersRemaining.setPreferredSize(new Dimension(150, 60));
        labelInvadersRemaining.setBackground(new Color(255, 180, 162));
        labelInvadersRemaining.setOpaque(true);
        panelMain.add(labelInvadersRemaining);

        JLabel buffer4 = new JLabel();
        buffer4.setPreferredSize(new Dimension(40, 0));
        panelMain.add(buffer4);

        labelCurrency = new JLabel("Current gold: " + game.getCurrency() + "g");
        labelCurrency.setHorizontalAlignment(SwingConstants.CENTER);
        labelCurrency.setPreferredSize(new Dimension(110, 60));
        labelCurrency.setBackground(new Color(255, 180, 162));
        labelCurrency.setOpaque(true);
        panelMain.add(labelCurrency);
    }

    @Override
    public void update(Observable o, Object arg) {
        String message = (String) arg;
        if(message.equals(UPDATE_SCORE_AREA)){
            labelKillCount.setText("Kill Count: " + game.getKillCount());
            labelMissilesRemaining.setText("Missiles Remaining: " + ((game.MAX_MISSILES_MULTIPLIER * game.getPlayerUnitLevel()) - game.getNumberOfMissiles()));
            labelInvadersOnScreen.setText("Invaders on Screen: " + game.getInvadersOnScreen());
            labelInvadersRemaining.setText("Invaders Remaining: " + game.getInvadersLeftInRound());
            labelCurrency.setText("Current gold: " + game.getCurrency());
        }
    }
}
