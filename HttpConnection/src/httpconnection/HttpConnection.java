/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpconnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import sun.net.www.http.HttpClient;

/**
 *
 * @author User
 */
public class HttpConnection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        URL url = new URL("http://127.0.0.1:8000/dextop_login?");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        new loginUi().setVisible(true);
    }
    
}
