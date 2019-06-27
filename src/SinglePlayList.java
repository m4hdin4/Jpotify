import javax.swing.*;
import java.util.ArrayList;

public class SinglePlayList extends JButton {
    private ArrayList<SingleTrack> singleTracks;
    private String playListName;

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }


    public ArrayList<SingleTrack> getSingleTracks() {
        return singleTracks;
    }

    public void setSingleTracks(ArrayList<SingleTrack> singleTracks) {
        this.singleTracks = singleTracks;
    }

    public SinglePlayList(String playListName){
        super(playListName);
        this.playListName =playListName;
        singleTracks = new ArrayList<>();
    }
}
