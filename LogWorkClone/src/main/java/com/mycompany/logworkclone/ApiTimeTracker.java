/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.logworkclone;

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
public class ApiTimeTracker extends Thread{
    private String email;
    private String project;
    private String task_title;
    private boolean response;

    public ApiTimeTracker(String email, String project, String task_title) {
        this.email = email;
        this.project = project;
        this.task_title = task_title;
    }
    
    @Override
    public void run(){
        try {

            URL url = new URL("http://127.0.0.1:8000/dextop_time_tracker?" + "email=" + this.email + "&project=" + this.project + "&task_title=" + this.task_title);
//          URL url = new URL("http://127.0.0.1:8000/dextop_time_tracker?" + "email=test@gmail.com&project=Bizzy%20-%20Web%20Module&task_title=dextop");

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

                //System.out.println(informationString);


                //JSON simple library Setup with Maven is used to convert strings to JSON
                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));

                //Get the first JSON object in the JSON array
                System.out.println(dataObject);
                if(dataObject.toString().equals("[]")){
                    this.response = false;
                }else{
                    this.response = true;
                }
                
                
//                this.projects = new String[dataObject.size()];
//                for(int i=0;i<=dataObject.size();i++){
//                    //System.out.println(dataObject.get(i));
//                    projects[i] = (String) dataObject.get(i);
//                }
//
//                System.out.println(Arrays.toString(projects));
                
                

            }


        } catch (IOException | RuntimeException | ParseException e) {
            System.out.println(e);
            this.response = false;
        }
        
        //return this.response;
    }
}
