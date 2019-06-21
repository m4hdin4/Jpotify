import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import com.mpatric.mp3agic.* ;



public class CenterPanel extends JPanel implements PhotoAndMusicLinker {

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
        if (mp3file.hasId3v1Tag()) {
            ID3v1 id3v1Tag = mp3file.getId3v1Tag();
            addTrack[musicCounter].setOptions("play.png" , id3v1Tag.getArtist()+" " , id3v1Tag.getTrack()+" " , id3v1Tag.getAlbum()+ " " , mp3file);
            addTrack[musicCounter].setVisible(true);
            //addNewTrack(addTrack);
        }
        else{
            addTrack[musicCounter].setOptions("play.png" , " " , " " , " " , mp3file);
            addTrack[musicCounter].setVisible(true);
        }
        musicCounter++;
    }

}
