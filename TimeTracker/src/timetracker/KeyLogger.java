/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package timetracker;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 *
 * @author HP
 */
public final class KeyLogger implements NativeKeyListener {
    private static KeyLogger instance;
    private int totalKeyPressed;
    
    private KeyLogger(){}
    
    public static KeyLogger getInstance(){
        if (instance == null) {
            instance = new KeyLogger();
        }
        return instance;
    }
    
//    public static void main(String args[]) {
//
//        try {
//            GlobalScreen.registerNativeHook();
//        } catch (NativeHookException ex) {
//            Logger.getLogger(KeyLogger.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        GlobalScreen.addNativeKeyListener(new KeyLogger());
//
//    }
    
    public int getTotalKeyPressed() {
        return totalKeyPressed;
    }

    public void setTotalKeyPressed(int totalKeyPressed) {
        this.totalKeyPressed = totalKeyPressed;
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {
//        System.out.println("Pressed" + NativeKeyEvent.getKeyText(nke.getKeyCode()));
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
//        System.out.println("Released" + NativeKeyEvent.getKeyText(nke.getKeyCode()));
        this.totalKeyPressed++;
        System.out.println("Total key pressed: " + this.totalKeyPressed);
    }
}
