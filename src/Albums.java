import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Albums extends JPanel implements MatchSongsAndAlbums , RemoveFromAlbum {
    public int getAlbumCounter() {
        return albumCounter;
    }

    public void setAlbumCounter(int albumCounter) {
        this.albumCounter = albumCounter;
    }

    private int albumCounter;

    public ArrayList<SingleAlbum> getAlbums() {
        return albums;
    }

    private ArrayList<SingleAlbum> albums;


    public Albums (){
        super();
        this.setLayout(new WrapLayout(WrapLayout.LEFT));
        albums = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            albums.add(new SingleAlbum());
            albums.get(i).setVisible(false);
            this.add(albums.get(i));
        }
    }


    @Override
    public void match(String singleAlbumName, String songArtist, Image image, SingleTrack singleTrack) {
        boolean flag2 = true;
        for (int i = 0; i < albumCounter; i++) {
            if (singleAlbumName.equals(albums.get(i).getAlbumName())){
                albums.get(i).addToAlbum(singleTrack);
                flag2 = false;
                break;
            }
        }
        if (flag2){
//            albums.add(new SingleAlbum());
//            albums.get(albumCounter).setVisible(false);
//            this.add(albums.get(albumCounter));
            albums.get(albumCounter).setOptions(singleAlbumName , songArtist , image);
            albums.get(albumCounter).addToAlbum(singleTrack);
            albumCounter++;
        }
    }

    @Override
    public void removeFromAlbum(String albumName, SingleTrack singleTrack) {
        for (int i = 0; i < albumCounter; i++) {
            if (albumName.equals(albums.get(i).getAlbumName())){
                albums.get(i).removeFromAlbum(singleTrack);
                if (albums.get(i).getAlbumSongs().getTracks().size() == 0) {
                    albums.get(i).setVisible(false);
                    albums.remove(i);
                }

                break;
            }
        }
    }
}
