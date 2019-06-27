import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Favorite extends JButton {
    public SingleTrack getSingleTrack() {
        return singleTrack;
    }

    public void setSingleTrack(SingleTrack singleTrack) {
        this.singleTrack = singleTrack;
    }

    private SingleTrack singleTrack;
    public Favorite(){
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                singleTrack.getSinger_Photo().doClick();
            }
        });
    }
}
