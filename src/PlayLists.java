import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayLists extends JPanel {
    private ArrayList<SinglePlayList> playLists;

    public ArrayList<SinglePlayList> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(ArrayList<SinglePlayList> playLists) {
        this.playLists = playLists;
    }

    public PlayLists(){
        this.setLayout(new WrapLayout());
        this.setSize(1 , 20);
        playLists = new ArrayList<>();
    }
}
