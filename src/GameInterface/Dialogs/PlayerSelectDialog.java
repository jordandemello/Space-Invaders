package GameInterface.Dialogs;

import GameInterface.App.SpaceShooter;
import GameInterface.Popups.ConfirmExitPopup;
import GameInterface.WindowListeners.ConfirmExitDialogListener;
import GameUnits.PlayerUnit;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jordan on 2017-07-25.
 */
public class PlayerSelectDialog extends JDialog {

    private SpaceShooter app;
    private final static int WIDTH = 250;
    private final static int HEIGHT = 250;
    private JList listPlayerSelect;
    private static String[] colours = {"Red", "Blue", "Green", "Purple"};
    private ImageIcon playerIcon;
    private JLabel labelPlayerImage;
    private JPanel panelMain;

    public PlayerSelectDialog(SpaceShooter app){
        super(app, "Select a Player.", true);
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
        panelMain = new JPanel(new GridBagLayout());
        getContentPane().add(panelMain, BorderLayout.CENTER);
        panelMain.setBackground(new Color(255, 255, 180));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(10, 20, 10, 20);
        constraints.fill = GridBagConstraints.BOTH;

        JLabel labelPlayerSelect = new JLabel("Select a Player");
        labelPlayerSelect.setHorizontalAlignment(SwingConstants.CENTER);
        labelPlayerSelect.setBackground(new Color(220, 220, 220));
        labelPlayerSelect.setOpaque(true);
        panelMain.add(labelPlayerSelect, constraints);

        constraints.gridx++;
        listPlayerSelect = new JList(colours);
        listPlayerSelect.setVisibleRowCount(4);
        listPlayerSelect.setSelectedIndex(0);
        listPlayerSelect.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPlayerSelect.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                repaintPlayer(listPlayerSelect.getSelectedIndex());
            }
        });
        panelMain.add(new JScrollPane(listPlayerSelect), constraints);

        JPanel panelNorth = new JPanel(new GridBagLayout());
        getContentPane().add(panelNorth, BorderLayout.NORTH);
        panelNorth.setBackground(new Color(255, 255, 180));
        constraints.gridx = 0;
        constraints.gridy = 0;
        playerIcon = new ImageIcon("src/Images/Player" + colours[0] + ".png");
        labelPlayerImage = new JLabel(playerIcon);
        panelNorth.add(labelPlayerImage, constraints);

        JPanel panelSouth = new JPanel();
        getContentPane().add(panelSouth, BorderLayout.SOUTH);
        panelSouth.setBackground(new Color(255, 255, 180));

        JButton buttonStart = new JButton("Okay");
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                app.getSpaceGame().setPlayerColour(colours[listPlayerSelect.getSelectedIndex()]);
                app.getGameArea().repaint();
                app.startTimer();
            }
        });
        buttonStart.setBackground(Color.GREEN);
        buttonStart.setOpaque(true);
        getRootPane().setDefaultButton(buttonStart);
        buttonStart.requestFocusInWindow();
        panelSouth.add(buttonStart);

        JButton buttonCancel = new JButton("Quit");
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConfirmExitPopup(app, "Dialog");
            }
        });
        buttonCancel.setBackground(Color.RED);
        buttonCancel.setOpaque(true);
        panelSouth.add(buttonCancel);
    }

    private void repaintPlayer(int listIndex){
        String colour = colours[listIndex];
        playerIcon = new ImageIcon("src/Images/Player" + colour + ".png");
        labelPlayerImage.setIcon(playerIcon);
        panelMain.repaint();
    }
}
