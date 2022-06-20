/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackerkeylogger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;


/**
 *
 * @author User
 */
public class TImeTrackerKeyLogger implements NativeKeyListener {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            Logger.getLogger(TImeTrackerKeyLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GlobalScreen.addNativeKeyListener(new TImeTrackerKeyLogger());
        
  }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {
        System.out.println("Pressed" + NativeKeyEvent.getKeyText(nke.getKeyCode()));
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
        System.out.println("Released" + NativeKeyEvent.getKeyText(nke.getKeyCode()));
    }

}