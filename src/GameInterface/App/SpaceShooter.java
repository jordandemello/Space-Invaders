package GameInterface.App;

import GameInterface.Dialogs.ShopDialog;
import GameInterface.Dialogs.StartupDialog;
import GameInterface.Panels.GameArea;
import GameInterface.Panels.GameUnitArea;
import GameInterface.Panels.ScoreArea;
import GameInterface.WindowListeners.ConfirmExitFrameListener;
import GameUnits.SpaceGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observer;

/**
 * Created by Jordan on 2017-07-14.
 */
public class SpaceShooter extends JFrame {

    private SpaceGame spaceGame;
    private GameArea gameArea;
    private ScoreArea scoreArea;
    private GameUnitArea gameUnitArea;
    private Timer timer;
    private final int TIMER_INTERVAL = 50;
    public static final int SCORE_AREA_HEIGHT = 90;
    public static final int GAME_AREA_HEIGHT = 500;
    public static final int GAME_UNIT_AREA_HEIGHT = 90;
    public static final int SCREEN_WIDTH = 900;

    private SpaceShooter() {
        super("Space Shooter");
        spaceGame = new SpaceGame(this);
        gameArea = new GameArea(spaceGame);
        scoreArea = new ScoreArea(spaceGame);
        gameUnitArea = new GameUnitArea(spaceGame);
        Observer[] observers = new Observer[] {scoreArea, gameArea, gameUnitArea};
        spaceGame.loadObservers(observers);

        add(gameArea, BorderLayout.CENTER);
        add(scoreArea, BorderLayout.NORTH);
        add(gameUnitArea, BorderLayout.SOUTH);
        addKeyListener(new GameKeyHandler());
        addTimer();

        addWindowListener(new ConfirmExitFrameListener(this));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        new StartupDialog(this);

    }

    public void addTimer() {
        timer = new Timer(TIMER_INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                spaceGame.updateGame();
                spaceGame.checkLevelOver();}});
        timer.setInitialDelay(1500);

    }

    private class GameKeyHandler extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent keyEvent){
            spaceGame.keyPressed(keyEvent);
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            spaceGame.keyReleased(keyEvent);
        }
    }

    public void startTimer(){
        timer.start();
    }

    public void stopTimer(){
        timer.stop();
    }

    public SpaceGame getSpaceGame(){
        return spaceGame;
    }

    public GameArea getGameArea(){
        return gameArea;
    }

    public static void main(String[] args){
        new SpaceShooter();
    }
}