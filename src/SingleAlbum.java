import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class SingleAlbum extends JPanel {
    private Songs albumSongs;

    private String singerName ;
    private String albumName ;
    private JButton album_Photo;
    private JLabel singer_Name;
    private JLabel album_Name;

    //private PlaySingleTrack playSingleTrack;
    private SetCurrentSongsAlbum currentSongsAlbum;



    public void setCurrentSongsAlbum(SetCurrentSongsAlbum currentSongsAlbum) {
        this.currentSongsAlbum = currentSongsAlbum;
    }


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



    public void addToAlbum (SingleTrack singleTrack){
        albumSongs.addToSongs(singleTrack);
        this.setVisible(true);
    }
    public void removeFromAlbum (SingleTrack singleTrack){
        albumSongs.removeFromAlbum(singleTrack);
    }


    public SingleAlbum (){

        this.setOpaque(false);
        this.setBackground(new Color(0xEEEEEE));
        this.setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
        albumSongs = new Songs(0);
        album_Photo = new JButton();
        singer_Name = new JLabel("" , JLabel.CENTER);
        album_Name = new JLabel("",JLabel.CENTER);
        album_Photo.setOpaque(false);
        album_Photo.setBackground(new Color(0xEEEEEE));
        album_Photo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                for (int i = 0; i < albumSongs.getTracks().size(); i++) {
//                    albumSongs.getTracks().get(i).setVisible(true);
//                }
//                albumSongs.setVisible(true);
                ArrayList<SingleTrack> temp = new ArrayList<>();
                for (int i = 0; i < albumSongs.getMusicCounter(); i++) {
                    temp.add(albumSongs.getTracks().get(i));
                }
                currentSongsAlbum.setCurrent(temp);
                albumSongs.getTracks().get(0).getSinger_Photo().doClick();
            }
        });
        Box box = Box.createVerticalBox();
        box.add(album_Photo);
        box.add(album_Name);
        box.add(singer_Name);
        this.add(box);
    }
    public void setOptions(String albumName  , String singerName , Image songIcon ){
        this.singerName = singerName;
        this.albumName = albumName;
        singer_Name.setText(singerName);
        album_Name.setText(albumName);
        try {
            Image image = songIcon.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            album_Photo.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.setVisible(true);
    }
}
