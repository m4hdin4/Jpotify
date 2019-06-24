import com.mpatric.mp3agic.Mp3File;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class SingleTrack extends JPanel {
    public void setSingleTrack(File singleTrack) {
        this.singleTrack = singleTrack;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public JButton getSinger_Photo() {
        return singer_Photo;
    }

    public void setSinger_Photo(JButton singer_Photo) {
        this.singer_Photo = singer_Photo;
    }

    public JLabel getSinger_Name() {
        return singer_Name;
    }

    public void setSinger_Name(JLabel singer_Name) {
        this.singer_Name = singer_Name;
    }

    public JLabel getTrack_Name() {
        return track_Name;
    }

    public void setTrack_Name(JLabel track_Name) {
        this.track_Name = track_Name;
    }

    public JLabel getAlbum_Name() {
        return album_Name;
    }

    public void setAlbum_Name(JLabel album_Name) {
        this.album_Name = album_Name;
    }

    private File singleTrack ;

    private String singerName ;
    private String trackName ;
    private String albumName ;
    private JButton singer_Photo;
    private JLabel singer_Name;
    private JLabel track_Name;
    private JLabel album_Name;

    public void setRemoveMusicLinker(RemoveMusicLinker removeMusicLinker) {
        this.removeMusicLinker = removeMusicLinker;
    }

    private RemoveMusicLinker removeMusicLinker;


    public void setPlaySingleTrack(PlaySingleTrack playSingleTrack) {
        this.playSingleTrack = playSingleTrack;
    }

    private PlaySingleTrack playSingleTrack;

    public void setCount(CounterHandler count) {
        this.count = count;
    }

    private CounterHandler count;

    public SingleTrack (){
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItem1 = new JMenuItem("Add to playlist");
        JMenuItem jMenuItem2 = new JMenuItem("Delete");
        //JMenuItem jMenuItem3 = new JMenuItem("falk");
        jPopupMenu.add(jMenuItem1);
        jPopupMenu.add(jMenuItem2);
        jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jMenuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                count.handle(returnThis());
                removeMusicLinker.remove(singleTrack);
            }
        });
        //jPopupMenu.add(jMenuItem3);
        //jPopupMenu.show();


        this.setOpaque(false);
        this.setBackground(new Color(0xEEEEEE));
        this.setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
        singer_Photo = new JButton();
        singer_Name = new JLabel("" , JLabel.CENTER);
        track_Name = new JLabel("" , JLabel.CENTER);
        album_Name = new JLabel("",JLabel.CENTER);
        singer_Photo.setOpaque(false);
        singer_Photo.setBackground(new Color(0xEEEEEE));
        singer_Photo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSingleTrack.play(singleTrack);
            }
        });

        singer_Photo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON3){
                    jPopupMenu.show(e.getComponent() , e.getX() , e.getY());
                }
            }
        });
        Box box = Box.createVerticalBox();
        box.add(singer_Photo);
        box.add(track_Name);
        box.add(album_Name);
        box.add(singer_Name);
        this.add(box);
    }

    public File getSingleTrack() {
        return singleTrack;
    }
    public SingleTrack returnThis(){
        return this;
    }

    public void setOptions(String singerName , String trackName , String albumName , Image songIcon , File file ){
        this.singleTrack = file;
        this.singerName = singerName;
        this.trackName = trackName;
        this.albumName = albumName;
        singer_Name.setText(singerName);
        album_Name.setText(albumName);
        track_Name.setText(trackName);
        try {
            Image image = songIcon.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            singer_Photo.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
