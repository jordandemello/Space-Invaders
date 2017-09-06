package GameInterface.Dialogs;

import GameInterface.App.SpaceShooter;
import GameInterface.Popups.NotEnoughGoldPopup;
import GameInterface.WindowListeners.ConfirmExitDialogListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jordan on 2017-07-25.
 */
public class ShopDialog extends JDialog {

    private SpaceShooter app;
    private final static int WIDTH = 600;
    private final static int HEIGHT = 400;
    private JLabel labelProjectileCurrentLevel, labelPlayerUnitCurrentLevel, labelCurrency;
    private JButton buttonProjectile, buttonPlayerUnit;
    private ShopDialog shop = this;


    public ShopDialog(SpaceShooter app){
        super(app, "Space Shop", true);
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

        JPanel panelShopMain = new JPanel(new GridBagLayout());
        getContentPane().add(panelShopMain, BorderLayout.CENTER);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 2;
        constraints.weighty = 2;
        constraints.insets = new Insets(3, 3, 3, 3);
        constraints.fill = GridBagConstraints.BOTH;

        JLabel labelProjectileDescription = new JLabel("Increase the speed and size of your projectiles.");
        labelProjectileDescription.setHorizontalAlignment(SwingConstants.CENTER);
        labelProjectileDescription.setBackground(new Color(204, 255, 255));
        labelProjectileDescription.setOpaque(true);
        panelShopMain.add(labelProjectileDescription, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        JLabel labelPlayerUnitDescription = new JLabel("Increase the maximum number of missiles by " + app.getSpaceGame().MAX_MISSILES_MULTIPLIER + ".");
        labelPlayerUnitDescription.setHorizontalAlignment(SwingConstants.CENTER);
        labelPlayerUnitDescription.setBackground(new Color(255, 204, 255));
        labelPlayerUnitDescription.setOpaque(true);
        panelShopMain.add(labelPlayerUnitDescription, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        labelProjectileCurrentLevel = new JLabel();
        labelProjectileCurrentLevel.setText("Current Level: " + app.getSpaceGame().getProjectileLevel());
        labelProjectileCurrentLevel.setHorizontalAlignment(SwingConstants.CENTER);
        labelProjectileCurrentLevel.setBackground(new Color(204, 255, 255));
        labelProjectileCurrentLevel.setOpaque(true);
        panelShopMain.add(labelProjectileCurrentLevel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        labelPlayerUnitCurrentLevel = new JLabel();
        labelPlayerUnitCurrentLevel.setText("Current Level: " + app.getSpaceGame().getPlayerUnitLevel());
        labelPlayerUnitCurrentLevel.setHorizontalAlignment(SwingConstants.CENTER);
        labelPlayerUnitCurrentLevel.setBackground(new Color(255, 204, 255));
        labelPlayerUnitCurrentLevel.setOpaque(true);
        panelShopMain.add(labelPlayerUnitCurrentLevel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        buttonProjectile = new JButton();
        buttonProjectile.setText("Purchase for " + app.getSpaceGame().getProjectileCost() + " gold.");
        buttonProjectile.setHorizontalAlignment(SwingConstants.CENTER);
        buttonProjectile.setBackground(new Color(102, 255, 255));
        buttonProjectile.setOpaque(true);
        buttonProjectile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!app.getSpaceGame().tryLevelUpProjectile()){
                    new NotEnoughGoldPopup(shop);
                }
                shop.updateText();
            }
        });
        panelShopMain.add(buttonProjectile, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        buttonPlayerUnit = new JButton();
        buttonPlayerUnit.setText("Purchase for " + app.getSpaceGame().getPlayerUnitCost() + " gold.");
        buttonPlayerUnit.setHorizontalAlignment(SwingConstants.CENTER);
        buttonPlayerUnit.setBackground(new Color(255, 102, 255));
        buttonPlayerUnit.setOpaque(true);
        buttonPlayerUnit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!app.getSpaceGame().tryLevelUpPlayerUnit()){
                    new NotEnoughGoldPopup(shop);
                }
                shop.updateText();
            }
        });
        panelShopMain.add(buttonPlayerUnit, constraints);

        JLabel labelWelcomeToShop = new JLabel("Welcome to the Space Shop");
        labelWelcomeToShop.setPreferredSize(new Dimension(0, 60));
        labelWelcomeToShop.setHorizontalAlignment(SwingConstants.CENTER);
        labelWelcomeToShop.setBackground(Color.YELLOW);
        labelWelcomeToShop.setOpaque(true);
        getContentPane().add(labelWelcomeToShop, BorderLayout.NORTH);

        JPanel panelSouth = new JPanel(new FlowLayout());
        getContentPane().add(panelSouth, BorderLayout.SOUTH);

        JLabel buffer1 = new JLabel();
        buffer1.setPreferredSize(new Dimension(130, 40));
        panelSouth. add(buffer1);

        JLabel buffer2 = new JLabel();
        buffer2.setPreferredSize(new Dimension(50, 40));
        panelSouth. add(buffer2);

        JButton buttonNextLevel = new JButton("Next Level");
        buttonNextLevel.setPreferredSize(new Dimension(200, 40));
        buttonNextLevel.setBackground(Color.GREEN);
        buttonNextLevel.setOpaque(true);
        buttonNextLevel.setHorizontalAlignment(SwingConstants.CENTER);
        buttonNextLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                app.getSpaceGame().nextLevel();
            }
        });
        getRootPane().setDefaultButton(buttonNextLevel);
        buttonNextLevel.requestFocusInWindow();
        panelSouth.add(buttonNextLevel);

        JLabel buffer3 = new JLabel();
        buffer3.setPreferredSize(new Dimension(50, 40));
        panelSouth. add(buffer3);

        labelCurrency = new JLabel("Current gold: " + app.getSpaceGame().getCurrency() + " g");
        labelCurrency.setHorizontalAlignment(SwingConstants.CENTER);
        labelCurrency.setPreferredSize(new Dimension(130, 40));
        labelCurrency.setBackground(new Color(255, 255, 102));
        labelCurrency.setOpaque(true);
        panelSouth.add(labelCurrency);
    }

    private void updateText(){
        labelPlayerUnitCurrentLevel.setText("Current Level: " + app.getSpaceGame().getPlayerUnitLevel());
        labelProjectileCurrentLevel.setText("Current Level: " + app.getSpaceGame().getProjectileLevel());
        buttonProjectile.setText("Purchase for " + app.getSpaceGame().getProjectileCost() + " gold.");
        buttonPlayerUnit.setText("Purchase for " + app.getSpaceGame().getPlayerUnitCost() + " gold.");
        labelCurrency.setText("Current gold: " + app.getSpaceGame().getCurrency() + " g");
    }
}
