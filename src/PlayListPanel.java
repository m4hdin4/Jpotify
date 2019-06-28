import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayListPanel extends JPanel {
    private ArrayList<PlayListTrack> playListTracks;

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
            playListTracks.get(i).setVisible(false);
        }
        this.setLayout(new GridLayout(30 , 1));
    }
}
