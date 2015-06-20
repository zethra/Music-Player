package MusicPlayerNode;

import jaco.mp3.player.MP3Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class Server implements Runnable{

    private boolean isStopped = false;
    private MP3Player player = null;
    private int port = 1234;
    private ServerSocket serverSocket = null;
    private BufferedReader input = null;
    private PrintWriter output = null;
    private String raw = null, response = null;
    private JSONObject command = null;
    private JSONObject data = null;
    private JSONArray songList = null;
    private Integer currentSong = 0;

    public Server() {

    }

    @Override
    public void run() {
        openServerSocket();
        while (!isStopped()) {
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
                input  = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
                output = new PrintWriter(clientSocket.getOutputStream(), true);
                raw = input.readLine();
                System.out.printf("Received: %s\n", raw);
                Object rawJSON = JSONValue.parse(raw);
                command = (JSONObject) rawJSON;
                if(executeCommand((String) command.get("command"))) {
                    stop();
                    player = null;
                    data = (JSONObject) command.get("data");
                    currentSong = Integer.parseInt((String) data.get("song"));
                    songList = (JSONArray) data.get("songs");
                    player = new MP3Player();
                    for(int i = 0; i < songList.size(); i++) {
                        URL song = new URL((String) songList.get(i));
                        player.addToPlayList(song);
                    }
                    play();
                }
                response = "Hello";
                output.println(response);
                System.out.printf("Sent: %s\n", response);
            } catch (IOException e) {
                if(isStopped) {
                    System.out.println("Server Stopped.") ;
                    return;
                }
            }
        }
    }

    private boolean executeCommand(String command) {
        switch (command) {
            case "play":
                play();
                break;
            case "pause":
                pause();
                break;
            case "stop":
                stop();
                break;
            case "playSongs":
                return true;
        }
        return false;
    }

    private void play() {
        if(player != null) {
            player.play();
        }
    }

    private void pause() {
        if(player != null) {
            player.pause();
        }
    }

    private void stop() {
        if(player != null) {
            player.stop();
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.port);
            //this.serverSocket.setSoTimeout(10000);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 8080", e);
        }
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }


    public synchronized void stopThread() {
        isStopped = true;
        stop();
    }

}
