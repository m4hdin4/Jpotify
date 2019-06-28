import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Favorite extends JButton {
    public SingleTrack getSingleTrack() {
        return singleTrack;
    }

    public void setSingleTrack(SingleTrack singleTrack) {
        this.singleTrack = singleTrack;
    }

    public void setDeleteFromFavorites(DeleteFromFavorites deleteFromFavorites) {
        this.deleteFromFavorites = deleteFromFavorites;
    }
    private SingleTrack singleTrack;
    private DeleteFromFavorites deleteFromFavorites;
    private RemoveFromFavoritesSave removeFromFavoritesSave;

    public void setRemoveFromFavoritesSave(RemoveFromFavoritesSave removeFromFavoritesSave) {
        this.removeFromFavoritesSave = removeFromFavoritesSave;
    }

    public Favorite(){
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItem1 = new JMenuItem("remove from favorites");
        jPopupMenu.add(jMenuItem1);
        jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                singleTrack.setLike(false);
                returnThis().setVisible(false);
                deleteFromFavorites.DeleteFromFavorites(returnThis());
                removeFromFavoritesSave.removeFromFavoritesSave(singleTrack.getSingleTrack());
            }
        });
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
    public Favorite returnThis(){
        return this;
    }
}
