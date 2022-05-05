/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testguiproject;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;

//for date
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Screenshot {

    public static int stopper = 1;
    public static int starter = 0;
    public static final long serialVersionUID = 1L;

    public static void takeSs(int stopper) {
        Screenshot.stopper = stopper;
        while (true) {

            try {
                Thread.sleep(120);
                Robot r = new Robot();

                // It saves screenshot to desired path
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
                String timeStamp = sdf3.format(timestamp);
                String file_name = timeStamp + "_ScreenShot.jpg";
                //			String path = "C:/Users/User/Desktop/Noor/";
                String path = "C:/Users/User/Desktop/Noor/" + file_name;

                // Used to get ScreenSize and capture image
                Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                BufferedImage Image = r.createScreenCapture(capture);
                ImageIO.write(Image, "jpg", new File(path));
                System.out.println("Screenshot saved");
                //System.out.println(Screenshot.stopper);
            } catch (AWTException | IOException | InterruptedException ex) {
                System.out.println(ex);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Screenshot.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    
    public void stopSs(){
        Screenshot.starter = 0;
    }

}
