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
public class GameUnitArea extends JPanel implements Observer{

    private SpaceGame game;
    private JLabel labelGameLevel, labelEnemyLevel, labelProjectileLevel, labelPlayerUnitLevel;
    public final static String UPDATE_GAME_UNIT_AREA = "Game Unit Area";


    public GameUnitArea(SpaceGame game){
        this.game = game;
        setLayout(new BorderLayout());

        addUIContent();

        setPreferredSize(new Dimension(SpaceShooter.SCREEN_WIDTH, SpaceShooter.GAME_UNIT_AREA_HEIGHT));
        setVisible(true);
    }

    private void addUIContent(){
        JPanel panelMain = new JPanel(new GridBagLayout());
        panelMain.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 100), 4));
        panelMain.setBackground(new Color(255, 255, 180));
        panelMain.setOpaque(true);
        add(panelMain, BorderLayout.CENTER);

        labelGameLevel = new JLabel("Game Level: " + game.getGameLevel());
        labelGameLevel.setHorizontalAlignment(SwingConstants.CENTER);
        labelGameLevel.setPreferredSize(new Dimension(110, 60));
        labelGameLevel.setBackground(new Color(255, 180, 162));
        labelGameLevel.setOpaque(true);
        panelMain.add(labelGameLevel);

        JLabel buffer1 = new JLabel();
        buffer1.setPreferredSize(new Dimension(80, 0));
        panelMain.add(buffer1);

        labelEnemyLevel = new JLabel("Enemy Level: " + game.getEnemyLevel());
        labelEnemyLevel.setHorizontalAlignment(SwingConstants.CENTER);
        labelEnemyLevel.setPreferredSize(new Dimension(110, 60));
        labelEnemyLevel.setBackground(new Color(255, 180, 162));
        labelEnemyLevel.setOpaque(true);
        panelMain.add(labelEnemyLevel);

        JLabel buffer2 = new JLabel();
        buffer2.setPreferredSize(new Dimension(80, 0));
        panelMain.add(buffer2);

        labelProjectileLevel = new JLabel("Projectile Level: " + game.getProjectileLevel());
        labelProjectileLevel.setHorizontalAlignment(SwingConstants.CENTER);
        labelProjectileLevel.setPreferredSize(new Dimension(130, 60));
        labelProjectileLevel.setBackground(new Color(255, 180, 162));
        labelProjectileLevel.setOpaque(true);
        panelMain.add(labelProjectileLevel);

        JLabel buffer3 = new JLabel();
        buffer3.setPreferredSize(new Dimension(80, 0));
        panelMain.add(buffer3);

        labelPlayerUnitLevel = new JLabel("Player Unit Level: " + game.getPlayerUnitLevel());
        labelPlayerUnitLevel.setHorizontalAlignment(SwingConstants.CENTER);
        labelPlayerUnitLevel.setPreferredSize(new Dimension(140, 60));
        labelPlayerUnitLevel.setBackground(new Color(255, 180, 162));
        labelPlayerUnitLevel.setOpaque(true);
        panelMain.add(labelPlayerUnitLevel);
    }

    @Override
    public void update(Observable o, Object arg) {
        String message = (String) arg;
        if (message.equals(UPDATE_GAME_UNIT_AREA)) {
            labelGameLevel.setText("Game Level: " + game.getGameLevel());
            labelEnemyLevel.setText("Enemy Level: " + game.getEnemyLevel());
            labelProjectileLevel.setText("Projectile Level: " + game.getProjectileLevel());
            labelPlayerUnitLevel.setText("Player Unit Level: " + game.getPlayerUnitLevel());
        }
    }
}
