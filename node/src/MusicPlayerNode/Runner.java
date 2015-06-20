package MusicPlayerNode;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Server server = new Server();
        new Thread(server).start();
        scanner.nextLine();
        server.stopThread();
        System.exit(0);
    }
}
