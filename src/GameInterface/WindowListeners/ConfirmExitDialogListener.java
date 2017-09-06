package GameInterface.WindowListeners;

import GameInterface.App.SpaceShooter;
import GameInterface.Popups.ConfirmExitPopup;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Jordan on 2017-08-14.
 */
public class ConfirmExitDialogListener extends WindowAdapter {
    private SpaceShooter app;

    public ConfirmExitDialogListener(SpaceShooter app){
        super();
        this.app = app;
    }

    @Override
    public void windowClosing(WindowEvent e){
        new ConfirmExitPopup(app, "Dialog");
    }
}
