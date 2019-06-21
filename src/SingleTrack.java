import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class SingleTrack extends JPanel {
    private String photoAddres ;
    private String singerName ;
    private String trackName ;
    private String albumName ;

    public SingleTrack (String photoAddres , String singerName , String trackName , String albumName){
        this.photoAddres =photoAddres;
        this.singerName = singerName;
        this.trackName = trackName;
        this.albumName = albumName;


        this.setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
        JLabel singer_Photo = new JLabel();
        JLabel singer_Name = new JLabel(singerName , JLabel.CENTER);
        JLabel track_Name = new JLabel(trackName , JLabel.CENTER);
        JLabel album_Name = new JLabel(albumName,JLabel.CENTER);
        try {
            Image img2 = ImageIO.read(getClass().getResource(photoAddres));
            Image image = img2.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            singer_Photo.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        Box box = Box.createVerticalBox();
        box.add(singer_Photo);
        box.add(track_Name);
        box.add(album_Name);
        box.add(singer_Name);
        this.add(box);
    }
}
