/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpconnection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import javax.swing.JOptionPane;

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
//        Process p = Runtime.getRuntime().exec("getmac /fo csv /nh");
//        java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
//        String line;
//        line = in.readLine();
//        String[] result = line.split(",");
//        String macAddress = result[0].replace('"', ' ').trim();
//        
//        System.out.println(macAddress);

    
        //another
        InetAddress ip;
        try {

            ip = InetAddress.getLocalHost();
//            System.out.println("Current IP address : " + ip.getHostAddress());

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            System.out.print("Current MAC address : ");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            String macAddress = sb.toString();
            new ScreenShot(macAddress).start();
            System.out.println(macAddress);
//            JOptionPane.showMessageDialog(null, macAddress);

        } catch (UnknownHostException | SocketException e) {
        }
        

//        new ScreenShot(macAddress).start();
    }

}
