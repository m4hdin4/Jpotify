import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SharedPlaylistPanelOtherUsers extends JPanel {

    public ArrayList<OtherShare> getOtherShared() {
        return otherShared;
    }

    private ArrayList<OtherShare> otherShared;

    private int sharedCounter;
    public SharedPlaylistPanelOtherUsers()  {
        otherShared = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            otherShared.add(new OtherShare());
            otherShared.get(i).setVisible(false);
        }
        this.setLayout(new GridLayout(30 , 1));
    }
    public void setFiles(ArrayList<File> fileArrayList) throws IOException, InvalidDataException, UnsupportedTagException {
        for (int i = 0; i < fileArrayList.size(); i++) {
            SingleTrack temp = new SingleTrack();
            Mp3File mp3file = new Mp3File(fileArrayList.get(i));
            String songArtist;
            String songName;
            String albumName;
            Image image;
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
            temp.setOptions(songName , albumName  , songArtist , image , fileArrayList.get(i) );
            otherShared.get(i).setSingleTrack(temp);
            otherShared.get(i).setText(songName);
            otherShared.get(i).setVisible(true);
            otherShared.get(i).setVisible(true);
        }
    }
}
