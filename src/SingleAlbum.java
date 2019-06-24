import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SingleAlbum extends JPanel {
    private Songs albumSongs;

    private String singerName ;
    private String albumName ;
    private JButton album_Photo;
    private JLabel singer_Name;

    public Songs getAlbumSongs() {
        return albumSongs;
    }

    public void setAlbumSongs(Songs albumSongs) {
        this.albumSongs = albumSongs;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public JButton getAlbum_Photo() {
        return album_Photo;
    }

    public void setAlbum_Photo(JButton album_Photo) {
        this.album_Photo = album_Photo;
    }

    public JLabel getSinger_Name() {
        return singer_Name;
    }

    public void setSinger_Name(JLabel singer_Name) {
        this.singer_Name = singer_Name;
    }

    public JLabel getAlbum_Name() {
        return album_Name;
    }

    public void setAlbum_Name(JLabel album_Name) {
        this.album_Name = album_Name;
    }

    public PlaySingleTrack getPlaySingleTrack() {
        return playSingleTrack;
    }

    private JLabel album_Name;

    public void setPlaySingleTrack(PlaySingleTrack playSingleTrack) {
        this.playSingleTrack = playSingleTrack;
    }

    private PlaySingleTrack playSingleTrack;

    public SingleAlbum (){

        this.setOpaque(false);
        this.setBackground(new Color(0xEEEEEE));
        this.setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
        album_Photo = new JButton();
        singer_Name = new JLabel("" , JLabel.CENTER);
        album_Name = new JLabel("",JLabel.CENTER);
        album_Photo.setOpaque(false);
        album_Photo.setBackground(new Color(0xEEEEEE));
        album_Photo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //playSingleTrack.play(singleTrack);
            }
        });
        Box box = Box.createVerticalBox();
        box.add(album_Photo);
        box.add(album_Name);
        box.add(singer_Name);
        this.add(box);
    }
}
