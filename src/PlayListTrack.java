import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayListTrack extends JButton {
    private SingleTrack singleTrack;

    public SingleTrack getSingleTrack() {
        return singleTrack;
    }

    public void setSingleTrack(SingleTrack singleTrack) {
        this.singleTrack = singleTrack;
    }

    public PlayListTrack(){
        singleTrack = new SingleTrack();
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                singleTrack.getSinger_Photo().doClick();
            }
        });
    }
}
