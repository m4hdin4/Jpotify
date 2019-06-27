import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Share extends JButton {
    public SingleTrack getSingleTrack() {
        return singleTrack;
    }

    public void setSingleTrack(SingleTrack singleTrack) {
        this.singleTrack = singleTrack;
    }

    private SingleTrack singleTrack;
    public Share(){
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                singleTrack.getSinger_Photo().doClick();
            }
        });
    }
}
