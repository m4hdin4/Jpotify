import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayListPanel extends JPanel implements RemoveFromPlaylists {
    private ArrayList<PlayListTrack> playListTracks;

    public void setRemoveFromPlaylists2(RemoveFromPlaylists2 removeFromPlaylists2) {
        this.removeFromPlaylists2 = removeFromPlaylists2;
    }

    private RemoveFromPlaylists2 removeFromPlaylists2;

    public ArrayList<PlayListTrack> getPlayListTracks() {
        return playListTracks;
    }

    public void setPlayListTracks(ArrayList<PlayListTrack> playListTracks) {
        this.playListTracks = playListTracks;
    }

    public PlayListPanel(){
        playListTracks = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            playListTracks.add(new PlayListTrack());
            this.add(playListTracks.get(i));
            playListTracks.get(i).setRemoveFromPlaylists(this);
            playListTracks.get(i).setVisible(false);
        }
        this.setLayout(new GridLayout(30 , 1));
    }

    @Override
    public void removeFromPlaylists(PlayListTrack playListTrack) {
        playListTracks.remove(playListTrack);
        this.remove(playListTrack);
        removeFromPlaylists2.removeFromPlaylists2(playListTrack.getSingleTrack());
        revalidate();
    }
}
