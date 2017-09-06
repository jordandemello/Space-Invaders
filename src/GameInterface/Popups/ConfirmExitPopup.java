package GameInterface.Popups;

import GameInterface.App.SpaceShooter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jordan on 2017-08-14.
 */
public class ConfirmExitPopup extends JDialog{

    private SpaceShooter app;
    private String owner;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 150;

    public ConfirmExitPopup(SpaceShooter app, String str){
        super(app, "Confirm Exit", true);
        this.app = app;
        this.owner = str;
        addUIContent();

        setFocusable(true);
        setResizable(false);
        setSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addUIContent(){
        JPanel panelMain = new JPanel(new GridBagLayout());
        getContentPane().add(panelMain);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(20, 10, 20, 10);

        JLabel labelConfirmExit = new JLabel("Are you sure you want to exit?");
        labelConfirmExit.setHorizontalAlignment(SwingConstants.CENTER);
        panelMain.add(labelConfirmExit, constraints);

        constraints.gridy++;
        constraints.insets = new Insets(5, 30, 5, 30);
        JButton buttonReturn = new JButton("Return to Game");
        buttonReturn.setBackground(Color.GREEN);
        buttonReturn.setOpaque(true);
        buttonReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(owner.equals("Frame"))
                    app.startTimer();
                setVisible(false);
            }
        });
        panelMain.add(buttonReturn, constraints);

        constraints.gridy++;
        constraints.insets = new Insets(5, 30, 20, 30);
        JButton buttonQuit = new JButton("Quit");
        buttonQuit.setBackground(Color.RED);
        buttonQuit.setOpaque(true);
        buttonQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panelMain.add(buttonQuit, constraints);
    }
}

