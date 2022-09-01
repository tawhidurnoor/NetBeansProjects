/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package timetracker;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 *
 * @author HP
 */
public class MouseLogger extends MouseAdapter{
    
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
    public void mousePressed(MouseEvent e) {
        this.MouseClick++;
        System.out.println("Mouse Click Count: " + this.MouseClick);
    }

}
