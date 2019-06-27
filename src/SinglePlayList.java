import javax.swing.*;
import java.util.ArrayList;

public class SinglePlayList extends JButton {
    private ArrayList<SingleTrack> singleTracks;

    public ArrayList<SingleTrack> getSingleTracks() {
        return singleTracks;
    }

    public void setSingleTracks(ArrayList<SingleTrack> singleTracks) {
        this.singleTracks = singleTracks;
    }

    public SinglePlayList(){
        singleTracks = new ArrayList<>();
    }
}
