package MusicPlayerNode;

public class TestPlayer {

    public static void main(String[] args) throws InterruptedException {
        MusicPlayer player = new MusicPlayer();
        player.play("http://zethratech.com/musicFiles/Google%20Play%20Music/Imagine%20Dragons%20-%20I%e2%80%99m%20So%20Sorry.mp3");
        Thread.sleep(5000);
    }

}
