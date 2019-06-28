import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayListTrack extends JButton {
    private SingleTrack singleTrack;
    private RemoveFromPlaylists removeFromPlaylists;

    public void setRemoveFromPlaylists(RemoveFromPlaylists removeFromPlaylists) {
        this.removeFromPlaylists = removeFromPlaylists;
    }

    public SingleTrack getSingleTrack() {
        return singleTrack;
    }

    public void setSingleTrack(SingleTrack singleTrack) {
        this.singleTrack = singleTrack;
    }

    public PlayListTrack(){
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItem1 = new JMenuItem("remove from playList");
        jPopupMenu.add(jMenuItem1);
        jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnThis().setVisible(false);
                removeFromPlaylists.removeFromPlaylists(returnThis());
            }
        });
        singleTrack = new SingleTrack();
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                singleTrack.getSinger_Photo().doClick();
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
    }
    public PlayListTrack returnThis(){
        return this;
    }
}
