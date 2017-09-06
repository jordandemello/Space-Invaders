package GameInterface.Popups;

import GameInterface.Dialogs.ShopDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

/**
 * Created by Jordan on 2017-08-13.
 */
public class NotEnoughGoldPopup extends JDialog{

    private static final int WIDTH = 180;
    private static final int HEIGHT = 130;

    public NotEnoughGoldPopup(ShopDialog shopDialog){
        super(shopDialog, "Sorry!", true);

        addUIContent();

        setResizable(false);
        setSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addUIContent(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        getContentPane().add(panel);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(12, 10, 12, 10);

        JLabel labelMessage = new JLabel("Not enough gold.");
        labelMessage.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(labelMessage, constraints);

        constraints.gridy++;
        JButton buttonExit = new JButton("Accept");
        buttonExit.setAlignmentX(CENTER_ALIGNMENT);
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        panel.add(buttonExit, constraints);
    }
}
