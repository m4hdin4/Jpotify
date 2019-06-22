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

    private SingleTrack[] tracks;
    
    

    public CenterPanel (){
        this.setLayout(new WrapLayout(WrapLayout.LEFT));
        tracks = new SingleTrack[MAXMusicCounter];
        for (int i = 0; i < 1000; i++) {
            tracks[i] = new SingleTrack();
            tracks[i].setVisible(false);
            this.add(tracks[i]);
        }
    }

    @Override
    public void linker(File f) throws InvalidDataException, IOException, UnsupportedTagException {
        
        Mp3File mp3file = new Mp3File(f);
        String songArtist;
        String songName;
        String albumName;
        Image image;
        boolean flag = true;
        for (int i = 0; i < musicCounter; i++) {
            if (tracks[i].getSingleTrack().equals(f)) {
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
        tracks[musicCounter].setOptions(songArtist , songName , albumName , image , f);
        tracks[musicCounter].setVisible(true);

        musicCounter++;
    }
    public void counterPlus(){
        musicCounter++;
    }
    public void counterMinus(){
        musicCounter--;
    }

}
