package GameInterface.Dialogs;

import GameInterface.App.SpaceShooter;
import GameInterface.Popups.ConfirmExitPopup;
import GameInterface.WindowListeners.ConfirmExitDialogListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jordan on 2017-07-24.
 */
public class EndGameDialog extends JDialog {

    private SpaceShooter app;
    private final static int WIDTH = 300;
    private final static int HEIGHT = 200;

    public EndGameDialog(SpaceShooter app){
        super(app, "Game Over", true);
        this.app = app;

        addUIContent();

        addWindowListener(new ConfirmExitDialogListener(app));
        setFocusable(true);
        setResizable(false);
        setSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void addUIContent(){
        JPanel panelMain = new JPanel(new GridBagLayout());
        getContentPane().add(panelMain, BorderLayout.CENTER);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(10, 20, 10, 20);

        JLabel labelGameOver = new JLabel("Game Over.");
        labelGameOver.setHorizontalAlignment(SwingConstants.CENTER);
        panelMain.add(labelGameOver, constraints);

        constraints.gridy++;
        JButton buttonReplay = new JButton("Replay");
        buttonReplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                app.getSpaceGame().restartGame();
                app.stopTimer();
                new PlayerSelectDialog(app);
            }
        });
        buttonReplay.setBackground(Color.GREEN);
        buttonReplay.setOpaque(true);
        getRootPane().setDefaultButton(buttonReplay);
        buttonReplay.requestFocusInWindow();
        panelMain.add(buttonReplay, constraints);

        constraints.gridy++;
        JButton buttonCancel = new JButton("Close");
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonCancel.setBackground(Color.RED);
        buttonCancel.setOpaque(true);
        panelMain.add(buttonCancel, constraints);
    }

}
