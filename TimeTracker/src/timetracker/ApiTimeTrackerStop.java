/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package timetracker;

import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import models.TimeTracker;

/**
 *
 * @author User
 */
public class ApiTimeTrackerStop {

    private String timeTrackerId;

    public ApiTimeTrackerStop(String timeTrackerId) {
        this.timeTrackerId = timeTrackerId;
    }

    public void stopTracking() throws IOException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .uri(URI.create("http://127.0.0.1:8000/api/dextop_time_tracker_stop?" + "timeTrackerId=" + this.timeTrackerId))
                .build();
        
        String timeTrackerIdString = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        System.out.println(timeTrackerIdString);
        
    }
}
