import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;

/**
 * Created by st201138010 on 9/14/2014.
 */
public class Main implements NativeKeyListener {

    protected static GUI gui;

    public static void main(String args[]) {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            JOptionPane error = new JOptionPane("Fatal Error, Contact The Developer");
            System.exit(1);
        }
        GlobalScreen.getInstance().addNativeKeyListener(new Main());
        gui = new GUI();
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        if(e.getKeyCode() == NativeKeyEvent.VC_F12)
            if(gui.getState() == JFrame.ICONIFIED)
                gui.setState(JFrame.NORMAL);
            else
                gui.setState(JFrame.ICONIFIED);
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
    }

}
