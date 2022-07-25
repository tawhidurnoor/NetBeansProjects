/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package timetracker;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import javax.imageio.ImageIO;

//for date
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.logging.Level;
import java.util.logging.Logger;

//for uploading files to server
import java.io.File;
import java.math.BigInteger;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.Random;
import models.TimeTracker;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

/**
 *
 * @author User
 */
public class ScreenShot extends Thread {

    private String email;
    private String timeTrackerId;
    private String status;
    private int screenshotDuration;

    public ScreenShot(String email, String project) {
        this.email = email;
        this.timeTrackerId = project;

        System.out.println("https://www.taskmonitor.xyz/api/dextop_screenshot_duration?" + "email=" + this.email + "&time_tracker_id=" + this.timeTrackerId);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .uri(URI.create("https://www.taskmonitor.xyz/api/dextop_screenshot_duration?" + "email=" + this.email + "&time_tracker_id=" + this.timeTrackerId))
                .build();
        String screenshotDurationString = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        this.screenshotDuration = Integer.parseInt(screenshotDurationString);

    }

    public static final long serialVersionUID = 1L;

    @Override
    public void run() {
        while (true) {

            try {
                //Thread.sleep(120);
                Robot r = new Robot();

                // It saves screenshot to desired path
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
                String timeStamp = sdf3.format(timestamp);
                String file_name = timeStamp + "_ScreenShot.jpg";
                //String path = "E:/Tawhidur Nood Badhan/Time_Tracker_Solution--Web-Module/public/captured/"+file_name;
                String directory = "C:/Users/" + System.getProperty("user.name") + "/Documents/TimeTrackerCaptured";
                Files.createDirectories(Paths.get(directory));
                String path = directory + "/" + file_name;

                // Used to get ScreenSize and capture image
                Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                BufferedImage Image = r.createScreenCapture(capture);
                ImageIO.write(Image, "jpg", new File(path));
                //System.out.println("Screenshot saved");
                //System.out.println(Screenshot.stopper);

                //upload the file to server
                File fileA = new File(directory + "/" + file_name);

                MimeMultipartData mimeMultipartData = MimeMultipartData.newBuilder()
                        .withCharset(StandardCharsets.UTF_8)
                        .addFile("file", fileA.toPath(), Files.probeContentType(fileA.toPath()))
                        .build();

                //getting average key pressed per minute
                KeyLogger keyLogger = KeyLogger.getInstance();
                keyLogger.setTotalKeyPressed(0);
                try {
                    GlobalScreen.registerNativeHook();
                } catch (NativeHookException ex) {
//                    Logger.getLogger(KeyLogger.class.getName()).log(Level.SEVERE, null, ex);
                      Logger.getLogger(keyLogger.getClass().getName()).log(Level.SEVERE, null, ex);
                }
                
                GlobalScreen.addNativeKeyListener(keyLogger);

//                KeyLogger keyLogger = new KeyLogger();
                int averageKeyPressed = keyLogger.getTotalKeyPressed() / this.screenshotDuration;

                if (averageKeyPressed <= 20) {
                    this.status = "Low";
                } else if (averageKeyPressed >= 21 && averageKeyPressed <= 30) {
                    this.status = "Okay";
                } else {
                    this.status = "Excellent";
                }

                HttpRequest request = HttpRequest.newBuilder()
                        .header("Content-Type", mimeMultipartData.getContentType())
                        .POST(mimeMultipartData.getBodyPublisher())
                        .uri(URI.create("https://www.taskmonitor.xyz/api/dextop_test_upload?email=" + this.email + "&timeTrackerId=" + this.timeTrackerId + "&activity=" + this.status))
                        .version(HttpClient.Version.HTTP_1_1)
                        .build();

                HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
                HttpResponse response = httpClient.send(request, BodyHandlers.ofString());
                System.out.println(response);

                //deleting file
                fileA.delete();
                keyLogger.setTotalKeyPressed(0);

            } catch (AWTException | IOException ex) {
                System.out.println(ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ScreenShot.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Random random = new Random();
                int randomScreenshotDuration = random.nextInt(this.screenshotDuration);
                
                Thread.sleep(randomScreenshotDuration * 60 * 1000); //minutes  *seconds in a minute * milliseconds in a second
            } catch (InterruptedException ex) {
                Logger.getLogger(ScreenShot.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
