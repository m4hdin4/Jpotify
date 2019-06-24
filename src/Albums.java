import javax.swing.*;

public class Albums extends JPanel {
    private int albumCounter;
    private int MAXAlbumCounter = 1000;

    public SingleAlbum[] getAlbums() {
        return albums;
    }

    private SingleAlbum[] albums;


    public Albums (){
        super();
        this.setLayout(new WrapLayout(WrapLayout.LEFT));
        this.setVisible(false);
        albums = new SingleAlbum[MAXAlbumCounter];
        for (int i = 0; i < MAXAlbumCounter; i++) {
            albums[i] = new SingleAlbum();
            albums[i].setVisible(false);
            this.add(albums[i]);
        }
    }

}
