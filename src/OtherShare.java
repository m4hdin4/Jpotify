import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OtherShare extends JButton {
    public SingleTrack getSingleTrack() {
        return singleTrack;
    }

    public void setSingleTrack(SingleTrack singleTrack) {
        this.singleTrack = singleTrack;
    }

    private SingleTrack singleTrack;

    public void setPlaySharedSongs(PlaySharedSongs playSharedSongs) {
        this.playSharedSongs = playSharedSongs;
    }

    private PlaySharedSongs playSharedSongs;
    public OtherShare(){
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSharedSongs.playSharedSongs(singleTrack.getSingleTrack());
            }
        });
    }
}
