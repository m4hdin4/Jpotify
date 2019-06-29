
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

/**
 * class ControlPanel is the west JPanel on JpotifyFrame
 * it contains songs and playlists and albums and singer photo
 */
public class ControlPanel extends JPanel implements UpdateSongsFrame , SetPlayingSongProfile , PlaylistLoadAddingPlaylist {

    private final int heightDefault = 25;
    private final int widthDefault = 100;
    private final int imageSizeSmall = 30;
    private final int imageSizeBig = 90;

    private JButton addToSongs;
    private JButton allSongs;
    private JButton albums;
    private JButton playListIcon;
    private JButton home;
    private JLabel singer;
    private JButton addPlay;
    private JButton favorite;
    private JButton shared;
    private PlayLists playlist;

    public PlayLists getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlayLists playlist) {
        this.playlist = playlist;
    }


    private ProfilePhotoLinker1 musicLinker;
    private SaveMusicLinker saveMusic;
    private AddNewPlaylistSave addNewPlaylistSave;
    private SetDeletedPlaylistSave setDeletedPlaylistSave;

    public void setSetDeletedPlaylistSave(SetDeletedPlaylistSave setDeletedPlaylistSave) {
        this.setDeletedPlaylistSave = setDeletedPlaylistSave;
    }

    public void setAddNewPlaylistSave(AddNewPlaylistSave addNewPlaylistSave) {
        this.addNewPlaylistSave = addNewPlaylistSave;
    }


    public void setPlayListsLinker(PlayListsLinker playListsLinker) {
        this.playListsLinker = playListsLinker;
    }

    private PlayListsLinker playListsLinker;

    public void setCenterPanel1(ChangeCenterPanel1 centerPanel1) {
        this.centerPanel1 = centerPanel1;
    }

    private ChangeCenterPanel1 centerPanel1;
    private ChangeCenterPanel2 centerPanel2;
    private ChangeCenterPanel3 centerPanel3;
    private ChangeCenterPanel4 centerPanel4;
    private ChangeCenterPanel5 centerPanel5;


    public void setCenterPanel5(ChangeCenterPanel5 centerPanel5) {
        this.centerPanel5 = centerPanel5;
    }

    public void setCenterPanel4(ChangeCenterPanel4 centerPanel4) {
        this.centerPanel4 = centerPanel4;
    }

    public void setCenterPanel3(ChangeCenterPanel3 centerPanel3) {
        this.centerPanel3 = centerPanel3;
    }



    public void setCenterPanel2(ChangeCenterPanel2 centerPanel2) {
        this.centerPanel2 = centerPanel2;
    }

    public void setSaveMusic(SaveMusicLinker saveMusic) {
        this.saveMusic = saveMusic;
    }

    public ControlPanel() {
        this.setBackground(new Color(0x636363));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(144,50));
        addToSongs = new JButton();
        addToSongs.setOpaque(false);
        addToSongs.setContentAreaFilled(false);
        addToSongs.setBorderPainted(false);
        addToSongs.setToolTipText("Add song...");
        addToSongs.setMaximumSize(new Dimension(widthDefault, heightDefault));
        addToSongs.setMargin(new Insets(0, 0, 0, 0));
        addToSongs.setBackground(new Color(0xFFFFFF));

        addToSongs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JFileChooser musicChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//                musicChooser.setDialogTitle("Select a music");
//                musicChooser.setAcceptAllFileFilterUsed(false);
//                FileNameExtensionFilter filter = new FileNameExtensionFilter("mp3", "mp3");
//                musicChooser.addChoosableFileFilter(filter);
//
//                int returnValue = musicChooser.showOpenDialog(null);
//                if (returnValue == JFileChooser.APPROVE_OPTION) {
//                    try {
//                        musicLinker.linker(musicChooser.getSelectedFile());
//                        saveMusic.linker(musicChooser.getSelectedFile());
//                    } catch (InvalidDataException e1) {
//                        e1.printStackTrace();
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    } catch (UnsupportedTagException e1) {
//                        e1.printStackTrace();
//                    }
//
//                }
                JFileChooser musicChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                musicChooser.setDialogTitle("Multiple file and directory selection:");
                musicChooser.setMultiSelectionEnabled(true);
                int returnValue = musicChooser.showDialog(null, "choose");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("mp3", "mp3");
                musicChooser.addChoosableFileFilter(filter);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File[] files = musicChooser.getSelectedFiles();
                    System.out.println("Directories found\n");
                    System.out.println("\n- - - - - - - - - - -\n");
                    System.out.println("Files Found\n");
                    Arrays.asList(files).forEach(x -> {
                        if (x.isFile()) {
                            try {
                                musicLinker.linker(x);
                                saveMusic.linker(x);
                            } catch (InvalidDataException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } catch (UnsupportedTagException e1) {
                                e1.printStackTrace();
                            }
                        }
                    })
                    ;
                }
            }

        });

        try {
            Image img = ImageIO.read(getClass().getResource("/kk.png"));
            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
            addToSongs.setIcon(new ImageIcon(image));

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        allSongs = new JButton();
        allSongs.setOpaque(false);
        allSongs.setContentAreaFilled(false);
        allSongs.setBorderPainted(false);
        allSongs.setMaximumSize(new Dimension(widthDefault, heightDefault));
        allSongs.setBackground(new Color(0xFFFFFF));
        allSongs.setToolTipText("All Songs...");
        try {
            Image img = ImageIO.read(getClass().getResource("/music.png"));
            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
            allSongs.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        allSongs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Songs songs = new Songs();
                centerPanel1.change1();
            }
        });


        albums = new JButton();
        albums.setToolTipText("Albums");
        albums.setOpaque(false);
        albums.setContentAreaFilled(false);
        albums.setBorderPainted(false);
        albums.setMaximumSize(new Dimension(widthDefault, heightDefault));
        albums.setBackground(new Color(0xFFFFFF));
        albums.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel3.change3();
            }
        });
        try {
            Image img = ImageIO.read(getClass().getResource("/song-playlist.png"));
            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
            albums.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        playListIcon = new JButton("Your Library");
        playListIcon.setFont(new Font("bold", Font.ROMAN_BASELINE, 12));
        playListIcon.setToolTipText("Playlists");
        playListIcon.setEnabled(false);
        playListIcon.setOpaque(false);
        playListIcon.setContentAreaFilled(false);
        playListIcon.setBorderPainted(false);
        playListIcon.setMaximumSize(new Dimension(widthDefault, 3 * heightDefault));
        playListIcon.setBorder(new EmptyBorder(heightDefault, 0, 0, 0));
        try {
            Image img2 = ImageIO.read(getClass().getResource("/musicplaylist.png"));

            Image image = img2.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
            playListIcon.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        home = new JButton();
        home.setOpaque(false);
        home.setContentAreaFilled(false);
        home.setBorderPainted(false);
        home.setMaximumSize(new Dimension(widthDefault, heightDefault));
        home.setBackground(new Color(0xFFFFFF));
        home.setToolTipText("HomePage...");
        try {
            Image img2 = ImageIO.read(getClass().getResource("/home-page.png"));
            Image image = img2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            home.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel2.change2();
            }
        });


        favorite = new JButton();
        favorite.setOpaque(false);
        favorite.setContentAreaFilled(false);
        favorite.setBorderPainted(false);
        favorite.setMaximumSize(new Dimension(widthDefault, heightDefault));
        favorite.setBackground(new Color(0xFFFFFF));
        favorite.setToolTipText("favorites...");
        try {
            Image img2 = ImageIO.read(getClass().getResource("/favorite.png"));
            Image image = img2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            favorite.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        favorite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel4.change4();
            }
        });

        shared = new JButton();
        shared.setOpaque(false);
        shared.setContentAreaFilled(false);
        shared.setBorderPainted(false);
        shared.setMaximumSize(new Dimension(widthDefault, heightDefault));
        shared.setBackground(new Color(0xFFFFFF));
        shared.setToolTipText("shared...");
        try {
            Image img2 = ImageIO.read(getClass().getResource("/share.png"));
            Image image = img2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            shared.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        shared.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel5.change5();
            }
        });


        singer = new JLabel();
        singer.setMaximumSize(new Dimension(widthDefault, heightDefault));
        try {
            Image img = ImageIO.read(getClass().getResource("/singer.png"));
            Image image = img.getScaledInstance(imageSizeBig, imageSizeBig, Image.SCALE_SMOOTH);
            singer.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            ex.printStackTrace();
        }


//        vector = new Vector<>();
//        playlist = new JList<>(vector);
//        playlist.setForeground(new Color(0));
//        playlist.setBackground(new Color(0x636363));
//        vector.add("play lists:      ");
//        playlist.setListData(vector);
//        playlist.setMaximumSize(new Dimension(30, heightDefault));
        playlist = new PlayLists();

        addPlay = new JButton("Add Playlist");
        addPlay.setBackground(new Color(0x989898));
        addPlay.setForeground(new Color(0));
        addPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane newNameDialog = new JOptionPane();
                String playListName = newNameDialog.showInputDialog(addPlay, "playlist name");
                if (playListName != null && !playListName.equals("")) {
                    boolean flag = true;
                    for (int i = 0; i < playlist.getPlayLists().size(); i++) {
                        if (playListName.equals(playlist.getPlayLists().get(i).getPlayListName())){
                            JOptionPane.showMessageDialog(addPlay, "you have playlist with this name!! try again");
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        SinglePlayList temp = new SinglePlayList(playListName);
                        temp.setDeletePlaylist(playlist);
                        setDeletedPlaylistSave.setDeletedPlaylistSave(temp);
                        playListsLinker.playListLinker(temp);
                        playlist.getPlayLists().add(temp);
                        addNewPlaylistSave.addNewPlaylistSave(playListName);
                        playlist.add(temp);
                        playlist.revalidate();
                    }
                }
            }
        });


        JScrollPane playlistScroll = new JScrollPane(playlist , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        Box box = Box.createVerticalBox();
        box.add(home);
        box.add(addToSongs);
        box.add(allSongs);
        box.add(albums);
        box.add(favorite);
        box.add(shared);
        box.add(playListIcon);
        box.add(playlistScroll);
        box.add(addPlay);
        box.add(Box.createVerticalGlue());
        box.add(singer);

        this.add(box);

    }


    public void setMusicLinker(ProfilePhotoLinker1 musicLinker) {
        this.musicLinker = musicLinker;
    }

    /**
     * updating songs frame after adding file
     */
    @Override
    public void update() {
        allSongs.doClick();
    }

    /**
     * set the singer photo at playing
     */
    @Override
    public void setPlayingSongProfile(File f) throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File mp3file = new Mp3File(f);
        Image image;
        if (mp3file.getId3v2Tag().getAlbumImage() != null) {
            Image img = ImageIO.read(new ByteArrayInputStream(mp3file.getId3v2Tag().getAlbumImage()));
            image = img.getScaledInstance(imageSizeBig, imageSizeBig, Image.SCALE_SMOOTH);
        } else {
            Image img = ImageIO.read(getClass().getResource("/singer.png"));
            image = img.getScaledInstance(imageSizeBig, imageSizeBig, Image.SCALE_SMOOTH);
        }
        singer.setIcon(new ImageIcon(image));
    }

    @Override
    public void playlistLoadAddingPlaylist(String name) {
        SinglePlayList temp = new SinglePlayList(name);
        temp.setDeletePlaylist(playlist);
        setDeletedPlaylistSave.setDeletedPlaylistSave(temp);
        playListsLinker.playListLinker(temp);
        playlist.getPlayLists().add(temp);
        playlist.add(temp);
        playlist.revalidate();
    }
}
