import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.mpatric.mp3agic.* ;


public class Songs extends JPanel implements ProfilePhotoLinker1,CounterHandler {

    public int getMusicCounter() {
        return musicCounter;
    }

    private int musicCounter;

    public ArrayList<SingleTrack> getTracks() {
        return tracks;
    }

    private ArrayList<SingleTrack> tracks;

    private UpdateSongsFrame updateSongsFrame;

    public void setUpdateSongsFrame(UpdateSongsFrame updateSongsFrame) {
        this.updateSongsFrame = updateSongsFrame;
    }

    public void setMatchSongsAndAlbums(MatchSongsAndAlbums matchSongsAndAlbums) {
        this.matchSongsAndAlbums = matchSongsAndAlbums;
    }

    private MatchSongsAndAlbums matchSongsAndAlbums;

    public void setAlbumRemoving(RemoveFromAlbum albumRemoving) {
        this.albumRemoving = albumRemoving;
    }

    private RemoveFromAlbum albumRemoving;



    public Songs (int MAXIMUM){
        super();
        this.setLayout(new WrapLayout(WrapLayout.LEFT));
        tracks = new ArrayList<>();
        for (int i = 0; i < MAXIMUM; i++) {
            tracks.add(new SingleTrack());
            tracks.get(i).setVisible(false);
            tracks.get(i).setCount(this);
            this.add(tracks.get(i));
        }
    }

    @Override
    public void linker(File f) throws InvalidDataException, IOException, UnsupportedTagException {
//        SingleTrack trash = new SingleTrack();
//        trash.setCount(this);
        Mp3File mp3file = new Mp3File(f);
        String songArtist;
        String songName;
        String albumName;
        Image image;
        boolean flag = true;
        for (int i = 0; i < musicCounter; i++) {
            if (tracks.get(i).getSingleTrack().equals(f)) {
                flag = false;
                break;
            }
        }
        if (!flag)
            return;
        if (mp3file.hasId3v1Tag() && mp3file.getId3v1Tag().getArtist() != null && !mp3file.getId3v1Tag().getArtist().equals(""))
            songArtist = mp3file.getId3v1Tag().getArtist();
        else
            songArtist = "UNKNOWN";
        if (mp3file.hasId3v1Tag() && mp3file.getId3v1Tag().getTitle() != null && !mp3file.getId3v1Tag().getTitle().equals(""))
            songName = mp3file.getId3v1Tag().getTitle();
        else
            songName = "UNKNOWN";
        if (mp3file.hasId3v1Tag() && mp3file.getId3v1Tag().getAlbum() != null && !mp3file.getId3v1Tag().getAlbum().equals("")){
            albumName = mp3file.getId3v1Tag().getAlbum();
        }

        else
            albumName = "UNKNOWN";
        if (mp3file.getId3v2Tag().getAlbumImage()!=null ){
            image = ImageIO.read(new ByteArrayInputStream(mp3file.getId3v2Tag().getAlbumImage()));
        }
        else{
            image = ImageIO.read(getClass().getResource("/singer.png"));
        }
        tracks.get(musicCounter).setOptions(songName , albumName  , songArtist , image , f );
        tracks.get(musicCounter).setVisible(true);
        updateSongsFrame.update();
        if (mp3file.hasId3v1Tag() && mp3file.getId3v1Tag().getAlbum() != null && !mp3file.getId3v1Tag().getAlbum().equals("")){
            matchSongsAndAlbums.match(albumName , songArtist , image , tracks.get(musicCounter));
        }

        musicCounter++;
    }


    @Override
    public void handle(SingleTrack singleTrack) throws InvalidDataException, IOException, UnsupportedTagException {
        tracks.remove(singleTrack);
        Mp3File mp3file = new Mp3File(singleTrack.getSingleTrack());
        if (mp3file.hasId3v1Tag() && mp3file.getId3v1Tag().getAlbum() != null && !mp3file.getId3v1Tag().getAlbum().equals("")){
            albumRemoving.removeFromAlbum(mp3file.getId3v1Tag().getAlbum() , singleTrack);
        }
        musicCounter--;
    }
    public void addToSongs (SingleTrack singleTrack){
        tracks.add(musicCounter , singleTrack);
        tracks.get(musicCounter).setVisible(true);
        musicCounter++;
    }
    public void removeFromAlbum(SingleTrack singleTrack){
        tracks.get(tracks.indexOf(singleTrack)).setVisible(false);
        tracks.remove(singleTrack);
        musicCounter--;
    }
}
