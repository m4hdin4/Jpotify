import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SinglePlayList extends JButton {
    private ArrayList<SingleTrack> singleTracks;
    private String playListName;

    private ChangeCenterPanel6 centerPanel6;

    public void setCenterPanel6(ChangeCenterPanel6 centerPanel6) {
        this.centerPanel6 = centerPanel6;
    }

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
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayListPanel temp = new PlayListPanel();
                for (int i = 0; i < singleTracks.size(); i++) {
                    temp.getPlayListTracks().get(i).setSingleTrack(singleTracks.get(i));
                    temp.getPlayListTracks().get(i).setText(singleTracks.get(i).getTrackName());
                    temp.getPlayListTracks().get(i).setVisible(true);
                }
                centerPanel6.change6(temp , singleTracks);
            }
        });

        singleTracks = new ArrayList<>();
    }
}
