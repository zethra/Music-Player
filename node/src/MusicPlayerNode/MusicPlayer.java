package MusicPlayerNode;

import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayer;

import java.util.List;

public class MusicPlayer {

    private final AudioMediaPlayerComponent playerComponent;
    private final MediaPlayer player;

    private List<String> playlist = null;
    private int position;

    public MusicPlayer() {
        new NativeDiscovery().discover();
        playerComponent = new AudioMediaPlayerComponent();
        player = playerComponent.getMediaPlayer();
    }

    public void setPlaylist(List<String> playlist) {
        this.playlist = playlist;
    }

    public List<String> getPlaylist() {
        return playlist;
    }

    public boolean playlistIsSet() {
        if(playlist instanceof List)
            return true;
        return false;
    }

    public void setPlaylistPosition(int position) {
        this.position = position;
    }

    public void play() {
        if(!player.isPlaying() && player.isPlayable()) {
            player.play();
        }
    }

    public void play(int position) {
        if(player.isPlayable()) {
            this.position = position;
            player.playMedia(playlist.get(this.position));
        }
    }

    public void play(String song) {
        //if(player.isPlayable())
            player.playMedia(song);

    }

    public void load(int position) {
        this.position = position;
        player.prepareMedia(playlist.get(this.position));
    }

    public void load(String song) {
        player.prepareMedia(song);
    }

    public void pause() {
        player.pause();
    }
}
