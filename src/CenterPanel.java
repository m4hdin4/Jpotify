import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import com.mpatric.mp3agic.* ;


public class CenterPanel extends JPanel implements ProfilePhotoLinker1 {

    private int musicCounter;
    private int MAXMusicCounter = 1000;

    private SingleTrack[] addTrack;

    public CenterPanel (){
        this.setLayout(new WrapLayout(WrapLayout.LEFT));
        addTrack = new SingleTrack[MAXMusicCounter];
        for (int i = 0; i < 1000; i++) {
            addTrack[i] = new SingleTrack();
            addTrack[i].setVisible(false);
            this.add(addTrack[i]);
        }
    }

    @Override
    public void linker(File f) throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File mp3file = new Mp3File(f);
        String songArtist;
        String songName;
        String albumName;
        Image image;
        if (mp3file.hasId3v1Tag() && mp3file.getId3v1Tag().getArtist() != null && !mp3file.getId3v1Tag().getArtist().equals(""))
            songArtist = mp3file.getId3v1Tag().getArtist();
        else
            songArtist = "UNKNOWN";
        if (mp3file.hasId3v1Tag() && mp3file.getId3v1Tag().getTrack() != null && !mp3file.getId3v1Tag().getTrack().equals(""))
            songName = mp3file.getId3v1Tag().getTrack();
        else
            songName = "UNKNOWN";
        if (mp3file.hasId3v1Tag() && mp3file.getId3v1Tag().getAlbum() != null && !mp3file.getId3v1Tag().getAlbum().equals(""))
            albumName = mp3file.getId3v1Tag().getAlbum();
        else
            albumName = "UNKNOWN";
        if (mp3file.getId3v2Tag().getAlbumImage()!=null ){
            image = ImageIO.read(new ByteArrayInputStream(mp3file.getId3v2Tag().getAlbumImage()));
        }
        else{
            image = ImageIO.read(getClass().getResource("/singer.png"));
        }
        addTrack[musicCounter].setOptions(songArtist , songName , albumName , image , f);
        addTrack[musicCounter].setVisible(true);

        musicCounter++;

    }

}
