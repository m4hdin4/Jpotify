import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SinglePlayList extends JButton implements RemoveFromPlaylists2 {
    private ArrayList<SingleTrack> singleTracks;
    private String playListName;

    private DeletePlaylist deletePlaylist;
    private ChangeCenterPanel6 centerPanel6;
    private DeletePlaylistSave deletePlaylistSave;
    private DeleteTrackFromPlaylistSave deleteTrackFromPlaylistSave;

    public void setDeleteTrackFromPlaylistSave(DeleteTrackFromPlaylistSave deleteTrackFromPlaylistSave) {
        this.deleteTrackFromPlaylistSave = deleteTrackFromPlaylistSave;
    }

    public void setDeletePlaylistSave(DeletePlaylistSave deletePlaylistSave) {
        this.deletePlaylistSave = deletePlaylistSave;
    }

    public void setDeletePlaylist(DeletePlaylist deletePlaylist) {
        this.deletePlaylist = deletePlaylist;
    }

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
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItem1 = new JMenuItem("delete playlist");
        jPopupMenu.add(jMenuItem1);
        jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnThis().setVisible(false);
                deletePlaylistSave.deletePlaylistSave(playListName);
                deletePlaylist.deletePlaylist(returnThis());
            }
        });
        this.playListName =playListName;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayListPanel temp = new PlayListPanel();
                temp.setRemoveFromPlaylists2(returnThis());
                for (int i = 0; i < singleTracks.size(); i++) {
                    temp.getPlayListTracks().get(i).setSingleTrack(singleTracks.get(i));
                    temp.getPlayListTracks().get(i).setText(singleTracks.get(i).getTrackName());
                    temp.getPlayListTracks().get(i).setVisible(true);
                }
                centerPanel6.change6(temp , singleTracks);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON3){
                    jPopupMenu.show(e.getComponent() , e.getX() , e.getY());
                }
            }
        });
        singleTracks = new ArrayList<>();
    }

    @Override
    public void removeFromPlaylists2(SingleTrack singleTrack) {
        singleTracks.remove(singleTrack);
        deleteTrackFromPlaylistSave.deleteTrackFromPlaylistSave(playListName , singleTrack.getSingleTrack());
    }
    public SinglePlayList returnThis(){
        return this;
    }
}
