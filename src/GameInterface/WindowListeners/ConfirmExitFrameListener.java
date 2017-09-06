package GameInterface.WindowListeners;

import GameInterface.App.SpaceShooter;
import GameInterface.Popups.ConfirmExitPopup;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Jordan on 2017-08-15.
 */
public class ConfirmExitFrameListener extends WindowAdapter{
    private SpaceShooter app;

    public ConfirmExitFrameListener(SpaceShooter app){
        super();
        this.app = app;
    }

    @Override
    public void windowClosing(WindowEvent e){
        app.stopTimer();
        new ConfirmExitPopup(app, "Frame");
    }

}
