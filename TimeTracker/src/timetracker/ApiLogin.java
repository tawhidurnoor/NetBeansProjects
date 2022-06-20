/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package timetracker;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author User
 */
public class ApiLogin {
    private String email;
    private String password;
    private boolean response;

    public ApiLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @SuppressWarnings("empty-statement")
    public boolean login() throws ParseException{
        try {

            URL url = new URL("http://127.0.0.1:8000/api/dextop_login?" + "email=" + this.email + "&password=" + this.password);
            System.out.println(url);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
                //this.response = false;
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner;
                scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                System.out.println(informationString);
                
                JSONParser parse = new JSONParser(); 
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
                System.out.println(dataObject.toString());
                if(dataObject.toString().equals("[]")){
                    this.response = false;
                }else{
                    //System.out.println(dataObject);
                    this.response = true;
                }
                
            }


        } catch (IOException | RuntimeException e) {
            System.out.println(e);
            this.response = false;
        }
        
        return this.response;
    }
    
}
