/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package httpconnection;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author User
 */
public class ApiTimeTrackerStop {
    private String timeTrackerId;

    public ApiTimeTrackerStop(String timeTrackerId) {
        this.timeTrackerId = timeTrackerId;
    }
    
    public void stopTracking(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .uri(URI.create("https://timetracker.codecloudtech.com/dextop_time_tracker_stop?" + "timeTrackerId=" + this.timeTrackerId))
                .build();

    }
}
