import javax.swing.*;
import java.awt.*;

public class SongProfile extends JPanel implements SetPlayingSongProfile2 {
    private String singerName;
    private String trackName;
    private JLabel songSingerName;
    private JLabel songName;

    public JLabel getSongSingerName() {
        return songSingerName;
    }

    public JLabel getSongAlbumName() {
        return songName;
    }

    public SongProfile(String singerName , String albumName){
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(180 , 5));
        songSingerName = new JLabel("Singer  ");
        songSingerName.setOpaque(false);
        songName = new JLabel("Album  ");
        songSingerName.setFont(new Font("BOLD" , Font.BOLD , 15));
        songName.setOpaque(false);
        this.add(songSingerName , BorderLayout.NORTH);
        this.add(songName , BorderLayout.CENTER);
    }

    @Override
    public void setPlayingSongProfile2(String singerName, String trackName) {
        this.singerName = singerName;
        this.trackName = trackName;
        songSingerName.setText("<html>"+singerName+ "</html>");
        songName.setText("<html>"+trackName+ "</html>");
    }
}
