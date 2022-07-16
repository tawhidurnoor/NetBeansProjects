/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package timetracker;

import models.TimeTracker;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ApiTimeTracker extends Thread {

    private String email;
    private String project;
    private String task_title;
    private boolean response;

    public ApiTimeTracker(String email, String project, String task_title) {
        this.email = email;
        this.project = project;
        this.task_title = task_title;
    }

    
    public TimeTracker track() {
        System.out.println("https://www.taskmonitor.xyz/api/dextop_time_tracker?" + "email=" + this.email + "&project=" + this.project + "&task_title=" + this.task_title);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                .version(Version.HTTP_1_1)
                .uri(URI.create("https://www.taskmonitor.xyz/api/dextop_time_tracker?" + "email=" + this.email + "&project=" + this.project + "&task_title=" + this.task_title))
                .build();
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//                .thenAccept(System.out::println)
//                .join();
        String timeTrackerIdString = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        BigInteger timeTrackerId = new BigInteger(timeTrackerIdString);
        
         TimeTracker timeTracker = new TimeTracker(timeTrackerId);
         return timeTracker;
    }

}
