import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost";
        String input;
        input = "{\"command\":\"playSongs\"," +
                    "\"data\":{" +
                        "\"song\":\"0\"," +
                        "\"songs\":[" +
                            "\"http://zethratech.com/musicFiles/Google%20Play%20Music/Imagine%20Dragons%20-%20Gold.mp3\"," +
                            "\"http://zethratech.com/musicFiles/Google%20Play%20Music/Imagine%20Dragons%20-%20I%e2%80%99m%20So%20Sorry.mp3\"," +
                            "\"http://zethratech.com/musicFiles/Google%20Play%20Music/Ella%20Henderson%20-%20Ghost.mp3\"" +
                        "]" +
                    "}" +
                "}";
        Socket s = new Socket(serverAddress, 1234);
        System.out.printf("Connected to: %s\n", s.getLocalAddress());
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        out.println(input);
        System.out.println("Sent: 100,918");
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer = in.readLine();
        System.out.printf("Received: %s\n", answer);
        System.exit(0);
    }

}
