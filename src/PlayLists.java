import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayLists extends JPanel implements AddTrackToPlaylist , DeletePlaylist {
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
        this.setBackground(new Color(0x636363));
        playLists = new ArrayList<>();
    }

    @Override
    public void addTrackToPlaylist(String playlistName , SingleTrack singleTrack) {
        boolean flag = true;
        for (int i = 0; i < playLists.size(); i++) {
            if (playLists.get(i).getPlayListName().equals(playlistName)){
                playLists.get(i).getSingleTracks().add(singleTrack);
                flag = false;
                break;
            }
        }
        if (flag){
            JOptionPane.showMessageDialog(singleTrack, "There's no playLists with this name!!!please add it first");
        }
    }

    @Override
    public void deletePlaylist(SinglePlayList singlePlayList) {
        this.remove(singlePlayList);
        playLists.remove(singlePlayList);
        revalidate();
    }
}
