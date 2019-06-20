import javax.swing.*;
import java.awt.*;

public class SongProfile extends JPanel {
    private String singerName;
    private String albumName;
    private JLabel songSingerName;
    private JLabel songAlbumName;

    public JLabel getSongSingerName() {
        return songSingerName;
    }

    public JLabel getSongAlbumName() {
        return songAlbumName;
    }

    public SongProfile(String singerName , String albumName){
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        songSingerName = new JLabel("Singer:  "+ singerName);
        songSingerName.setOpaque(false);
        songAlbumName = new JLabel("Album:  " + albumName);
        songSingerName.setFont(new Font("BOLD" , Font.BOLD , 20));
        songAlbumName.setOpaque(false);
        this.add(songSingerName , BorderLayout.NORTH);
        this.add(songAlbumName , BorderLayout.CENTER);
    }
}
