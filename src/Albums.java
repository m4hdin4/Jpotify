import javax.swing.*;
import java.util.ArrayList;

public class Albums extends JPanel {
    private int albumCounter;
    private int MAXAlbumCounter = 1000;

    public ArrayList<SingleAlbum> getAlbums() {
        return albums;
    }

    private ArrayList<SingleAlbum> albums;


    public Albums (){
        super();
        this.setLayout(new WrapLayout(WrapLayout.LEFT));
        this.setVisible(false);
        albums = new ArrayList<>();
        for (int i = 0; i < MAXAlbumCounter; i++) {
            albums.add(new SingleAlbum());
            albums.get(i).setVisible(false);
            this.add(albums.get(i));
        }
    }

}
