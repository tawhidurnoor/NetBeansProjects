/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpconnection;

import java.io.IOException;

/**
 *
 * @author User
 */
public class TimeTrackerNoUi {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        //get mac address
        Process p = Runtime.getRuntime().exec("getmac /fo csv /nh");
        java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
        String line;
        line = in.readLine();
        String[] result = line.split(",");
        String macAddress = result[0].replace('"', ' ').trim();
        
        System.out.println(macAddress);
        
        new ScreenShot(macAddress).start();
    }

}
