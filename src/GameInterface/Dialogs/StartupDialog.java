package GameInterface.Dialogs;

import GameInterface.App.SpaceShooter;
import GameInterface.Popups.ConfirmExitPopup;
import GameInterface.WindowListeners.ConfirmExitDialogListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jordan on 2017-07-20.
 */
public class StartupDialog extends JDialog {

    private SpaceShooter app;
    private final static int WIDTH = 500;
    private final static int HEIGHT = 250;

    public StartupDialog(SpaceShooter app){
        super(app, "Instructions", true);
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
        JPanel panelMain = new JPanel(new GridLayout(5,2));
        getContentPane().add(panelMain);

        JLabel labelLeftArrow = new JLabel("LEFT ARROW KEY");
        labelLeftArrow.setHorizontalAlignment(SwingConstants.CENTER);
        labelLeftArrow.setBackground(new Color(220, 220, 220));
        labelLeftArrow.setOpaque(true);
        panelMain.add(labelLeftArrow);

        JLabel labelLeftArrowDescription = new JLabel("Move player unit to the left.");
        labelLeftArrowDescription.setHorizontalAlignment(SwingConstants.CENTER);
        labelLeftArrowDescription.setBackground(new Color(220, 220, 220));
        labelLeftArrowDescription.setOpaque(true);
        panelMain.add(labelLeftArrowDescription);

        JLabel labelRightArrow = new JLabel("RIGHT ARROW KEY");
        labelRightArrow.setHorizontalAlignment(SwingConstants.CENTER);
        labelRightArrow.setBackground(new Color(200, 200, 200));
        labelRightArrow.setOpaque(true);
        panelMain.add(labelRightArrow);

        JLabel labelRightArrowDescription = new JLabel("Move player unit to the right.");
        labelRightArrowDescription.setHorizontalAlignment(SwingConstants.CENTER);
        labelRightArrowDescription.setBackground(new Color(200, 200, 200));
        labelRightArrowDescription.setOpaque(true);
        panelMain.add(labelRightArrowDescription);

        JLabel labelSpaceBar = new JLabel("SPACE BAR");
        labelSpaceBar.setHorizontalAlignment(SwingConstants.CENTER);
        labelSpaceBar.setBackground(new Color(220, 220, 220));
        labelSpaceBar.setOpaque(true);
        panelMain.add(labelSpaceBar);

        JLabel labelSpaceBarDescription = new JLabel("Fire projectile from current player location.");
        labelSpaceBarDescription.setHorizontalAlignment(SwingConstants.CENTER);
        labelSpaceBarDescription.setBackground(new Color(220, 220, 220));
        labelSpaceBarDescription.setOpaque(true);
        panelMain.add(labelSpaceBarDescription);

        JLabel labelEscapeKey = new JLabel("ESCAPE KEY");
        labelEscapeKey.setHorizontalAlignment(SwingConstants.CENTER);
        labelEscapeKey.setBackground(new Color(200, 200, 200));
        labelEscapeKey.setOpaque(true);
        panelMain.add(labelEscapeKey);

        JLabel labelEscapeKeyDescription = new JLabel("Pause the game.");
        labelEscapeKeyDescription.setHorizontalAlignment(SwingConstants.CENTER);
        labelEscapeKeyDescription.setBackground(new Color(200, 200, 200));
        labelEscapeKeyDescription.setOpaque(true);
        panelMain.add(labelEscapeKeyDescription);

        JButton buttonStart = new JButton("Okay");
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new PlayerSelectDialog(app);
            }
        });
        buttonStart.setBackground(Color.GREEN);
        buttonStart.setOpaque(true);
        getRootPane().setDefaultButton(buttonStart);
        buttonStart.requestFocusInWindow();
        panelMain.add(buttonStart);

        JButton buttonCancel = new JButton("Quit");
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConfirmExitPopup(app, "Dialog");
            }
        });
        buttonCancel.setBackground(Color.RED);
        buttonCancel.setOpaque(true);
        panelMain.add(buttonCancel);
    }

}

