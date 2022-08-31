/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package timetracker;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author HP
 */
public class MouseLogger implements MouseListener{
    
    private static MouseLogger instance;
    private int MouseClick;
    
    private MouseLogger(){}
    
    public static MouseLogger getInstance(){
        if (instance == null) {
            instance = new MouseLogger();
        }
        return instance;
    }

    public int getMouseClick() {
        return MouseClick;
    }

    public void setMouseClick(int MouseClick) {
        this.MouseClick = MouseClick;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.MouseClick++;
        System.out.println("Total mouse clicked " + this.MouseClick);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
