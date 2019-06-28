import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

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
    private CounterHandler count;

    private SetPlayingSongProfile playingSongProfile;
    private SetPlayingSongProfile2 playingSongProfile2;
    private AddTrackToShared addTrackToShared;private AddTrackToFavorites addTrackToFavorites;

    public void setAddTrackToFavorites(AddTrackToFavorites addTrackToFavorites) {
        this.addTrackToFavorites = addTrackToFavorites;
    }

    public void setAddTrackToShared(AddTrackToShared addTrackToShared) {
        this.addTrackToShared = addTrackToShared;
    }
    private AddToFavoritesSave addToFavoritesSave;

    public void setAddToFavoritesSave(AddToFavoritesSave addToFavoritesSave) {
        this.addToFavoritesSave = addToFavoritesSave;
    }


    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
        if (like) {
            addToFavoritesSave.addToFavoritesSave(singleTrack);
            addTrackToFavorites.addTrackToFavorites(returnThis());
        }
    }

    private Boolean like;

    private RemoveMusicLinker removeMusicLinker;
    private PlaySingleTrack playSingleTrack;
    private AddTrackToPlaylist addTrackToPlaylist;

    public void setAddTrackToPlaylist(AddTrackToPlaylist addTrackToPlaylist) {
        this.addTrackToPlaylist = addTrackToPlaylist;
    }


    public void setPlayingSongProfile2(SetPlayingSongProfile2 playingSongProfile2) {
        this.playingSongProfile2 = playingSongProfile2;
    }
    public void setPlayingSongProfile(SetPlayingSongProfile playingSongProfile) {
        this.playingSongProfile = playingSongProfile;
    }

    public void setRemoveMusicLinker(RemoveMusicLinker removeMusicLinker) {
        this.removeMusicLinker = removeMusicLinker;
    }

    public void setPlaySingleTrack(PlaySingleTrack playSingleTrack) {
        this.playSingleTrack = playSingleTrack;
    }

    public void setCount(CounterHandler count) {
        this.count = count;
    }

    public SingleTrack (){
        like = false;
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem jMenuItem1 = new JMenuItem("Add to playlist");
        JMenuItem jMenuItem3 = new JMenuItem("Add to shared playList");
        JMenuItem jMenuItem2 = new JMenuItem("Delete");
        jPopupMenu.add(jMenuItem1);
        jPopupMenu.add(jMenuItem3);
        jPopupMenu.add(jMenuItem2);
        jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane newNameDialog = new JOptionPane();
                String playListName = newNameDialog.showInputDialog(returnThis(), "which one do you want to add ?");
                if (playListName != null && !playListName.equals("")) {
                    addTrackToPlaylist.addTrackToPlaylist(playListName , returnThis());
                }
            }
        });
        jMenuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    count.handle(returnThis());
                } catch (InvalidDataException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedTagException e1) {
                    e1.printStackTrace();
                }
                removeMusicLinker.remove(singleTrack);
            }
        });
        jMenuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addTrackToShared.addTrackToShared(returnThis());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
        singer_Photo.setContentAreaFilled(false);
        singer_Photo.setBorderPainted(false);
        singer_Photo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    playingSongProfile.setPlayingSongProfile(singleTrack);
                } catch (InvalidDataException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedTagException e1) {
                    e1.printStackTrace();
                }
                playingSongProfile2.setPlayingSongProfile2(singerName , trackName);
                playSingleTrack.play(returnThis());
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

    public void setOptions(String trackName , String albumName  , String singerName , Image songIcon , File file ){
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
