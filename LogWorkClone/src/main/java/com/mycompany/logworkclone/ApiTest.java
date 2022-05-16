/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.logworkclone;

import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import org.json.simple.parser.ParseException;

/**
 *
 * @author User
 */
public class ApiTest {
    private String email;
//    private String password;
//
    public ApiTest(String email) {
        this.email = email;
    }
    
    public String[] projects;
    
    
    
    @SuppressWarnings("empty-statement")
    public String[] getProjectList(){
        
        try {
            //Public API:
            //https://www.metaweather.com/api/location/search/?query=<CITY>
            //https://www.metaweather.com/api/location/44418/
            
//            String url_link = "http://127.0.0.1:8000/dextop_login?" + "email=" + this.email + "&password=" + this.password;

             String url_link = "http://127.0.0.1:8000/dextop_projects";

            URL url = new URL("http://127.0.0.1:8000/dextop_projects" + "?email=" + this.email);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                //System.out.println(informationString);


                //JSON simple library Setup with Maven is used to convert strings to JSON
                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));

                //Get the first JSON object in the JSON array
//                System.out.println(dataObject.get(0));

//                String projects_string = dataObject.toString();
                //String[] project_string_array = (String[]) dataObject.toArray();
                
                
                 //String[] projects;
                 this.projects = new String[dataObject.size()];
                for(int i=0;i<=dataObject.size();i++){
                    //System.out.println(dataObject.get(i));
                    projects[i] = (String) dataObject.get(i);
                }

//                JSONObject countryData = (JSONObject) dataObject.get(0);
//
//                System.out.println(countryData.get("woeid"));
                System.out.println(Arrays.toString(projects));
                
                

            }


        } catch (Exception e) {
        }
        
        return this.projects;
        
    }
    
}
