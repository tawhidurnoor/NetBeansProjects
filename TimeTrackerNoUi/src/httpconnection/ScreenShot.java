/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package httpconnection;

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
import java.net.http.HttpResponse;

/**
 *
 * @author User
 */
public class ScreenShot extends Thread {

    private String macAddress;

    public ScreenShot(String macAddress) {
        this.macAddress = macAddress;
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
                String path = "E:/captured/" + file_name;

                // Used to get ScreenSize and capture image
                Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                BufferedImage Image = r.createScreenCapture(capture);
                ImageIO.write(Image, "jpg", new File(path));
                //System.out.println("Screenshot saved");
                //System.out.println(Screenshot.stopper);

                //upload the file to server
                File fileA = new File("E:/captured/" + file_name);

                MimeMultipartData mimeMultipartData = MimeMultipartData.newBuilder()
                        .withCharset(StandardCharsets.UTF_8)
                        .addFile("file", fileA.toPath(), Files.probeContentType(fileA.toPath()))
                        .build();

                HttpRequest request = HttpRequest.newBuilder()
                        .header("Content-Type", mimeMultipartData.getContentType())
                        .POST(mimeMultipartData.getBodyPublisher())
                        .uri(URI.create("https://timetracker.codecloudtech.com/dextop_no_ui_upload?macAddress=" + this.macAddress))
                        .version(HttpClient.Version.HTTP_1_1)
                        .build();

                HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
                HttpResponse response = httpClient.send(request, BodyHandlers.ofString());
                System.out.println(response);
                

            } catch (AWTException | IOException  ex) {
                System.out.println(ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ScreenShot.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.sleep(1*60*1000); //minutes  *seconds in a minute * milliseconds in a second
            } catch (InterruptedException ex) {
                Logger.getLogger(ScreenShot.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
