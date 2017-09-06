package GameInterface.Dialogs;

import GameInterface.App.SpaceShooter;
import GameInterface.WindowListeners.ConfirmExitDialogListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jordan on 2017-07-25.
 */
public class NextLevelDialog extends JDialog {

    private SpaceShooter app;
    private final static int WIDTH = 300;
    private final static int HEIGHT = 200;

    public NextLevelDialog(SpaceShooter app){
        super(app, "Level Completed.", true);
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
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(10, 20, 10, 20);

        JLabel labelLevelCompleted = new JLabel("Level Completed.");
        labelLevelCompleted.setHorizontalAlignment(SwingConstants.CENTER);
        panelMain.add(labelLevelCompleted, constraints);

        constraints.gridy++;
        JButton buttonNextLevel = new JButton("Next Level");
        buttonNextLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.getSpaceGame().nextLevel();
                setVisible(false);
            }
        });
        buttonNextLevel.setBackground(Color.GREEN);
        buttonNextLevel.setOpaque(true);
        panelMain.add(buttonNextLevel, constraints);

        constraints.gridy++;
        JButton buttonOpenShop = new JButton("Open Shop");
        buttonOpenShop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ShopDialog(app);
            }
        });
        buttonOpenShop.setBackground(Color.YELLOW);
        buttonOpenShop.setOpaque(true);
        getRootPane().setDefaultButton(buttonOpenShop);
        buttonOpenShop.requestFocusInWindow();
        panelMain.add(buttonOpenShop, constraints);
    }
}
