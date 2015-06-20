package MusicPlayerNode;

import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayer;

public class MusicPlayer {

    public static void main(String[] args) throws InterruptedException {
        new NativeDiscovery().discover();
        AudioMediaPlayerComponent playerComponent;
        playerComponent = new AudioMediaPlayerComponent();
        MediaPlayer player = playerComponent.getMediaPlayer();
        //player.playMedia("C:\\Users\\Zethra\\Music\\music\\x.mp3");
        //player.playMedia("http://zethratech.com/musicFiles/Google%20Play%20Music/Ella%20Henderson%20-%20Ghost.mp3");
        player.prepareMedia("http://zethratech.com/musicFiles/Google%20Play%20Music/Ella%20Henderson%20-%20Ghost.mp3");

        player.play();
        player.setPosition(0.5f);

        Thread.sleep(5000);
    }
}
