import com.mpatric.mp3agic.Mp3File;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SingleTrack extends JPanel {
    Mp3File singleTrack ;

    private String photoAddress ;
    private String singerName ;
    private String trackName ;
    private String albumName ;
    private JButton singer_Photo;
    private JLabel singer_Name;
    private JLabel track_Name;
    private JLabel album_Name;

    public SingleTrack (){

        this.setOpaque(false);
        this.setBackground(new Color(0xEEEEEE));
        this.setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
        singer_Photo = new JButton();
        singer_Name = new JLabel("" , JLabel.CENTER);
        track_Name = new JLabel("" , JLabel.CENTER);
        album_Name = new JLabel("",JLabel.CENTER);
        singer_Photo.setOpaque(false);
        singer_Photo.setBackground(new Color(0xEEEEEE));
        Box box = Box.createVerticalBox();
        box.add(singer_Photo);
        box.add(track_Name);
        box.add(album_Name);
        box.add(singer_Name);
        this.add(box);
    }

    public Mp3File getSingleTrack() {
        return singleTrack;
    }

    public void setOptions(String photoAddress , String singerName , String trackName , String albumName , Mp3File mp3File){
        this.singleTrack = mp3File;
        this.photoAddress =photoAddress;
        this.singerName = singerName;
        this.trackName = trackName;
        this.albumName = albumName;
        singer_Name.setText(singerName);
        album_Name.setText(albumName);
        track_Name.setText(trackName);
//        try {
//            Image img2 = ImageIO.read(getClass().getResource(this.photoAddress));
//            Image image = img2.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
//            singer_Photo.setIcon(new ImageIcon(image));
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
    }
}
