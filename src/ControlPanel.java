
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
public class ControlPanel extends JPanel implements UpdateSongsFrame , SetPlayingSongProfile {

    private final int heightDefault = 25;
    private final int widthDefault = 100;
    private final int imageSizeSmall = 30;
    private final int imageSizeBig = 90;

    private JButton addToSongs;
    private JButton allSongs;
    private JButton albums;
    private JButton playList;
    private JButton home;
    private JLabel singer;
    private JButton addPlay;

    private Vector<String> vector;
    private JList<String> playlist;

    private ProfilePhotoLinker1 musicLinker;
    private SaveMusicLinker saveMusic;

    public void setCenterPanel1(ChangeCenterPanel1 centerPanel1) {
        this.centerPanel1 = centerPanel1;
    }

    private ChangeCenterPanel1 centerPanel1;
    private ChangeCenterPanel2 centerPanel2;

    public void setCenterPanel3(ChangeCenterPanel3 centerPanel3) {
        this.centerPanel3 = centerPanel3;
    }

    private ChangeCenterPanel3 centerPanel3;

    public void setCenterPanel2(ChangeCenterPanel2 centerPanel2) {
        this.centerPanel2 = centerPanel2;
    }

    public void setSaveMusic(SaveMusicLinker saveMusic) {
        this.saveMusic = saveMusic;
    }

    public ControlPanel() {
        this.setBackground(new Color(0x636363));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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


        playList = new JButton("Your Library");
        playList.setFont(new Font("bold", Font.ROMAN_BASELINE, 12));
        playList.setToolTipText("Playlists");
        playList.setEnabled(false);
        playList.setOpaque(false);
        playList.setContentAreaFilled(false);
        playList.setBorderPainted(false);
        playList.setMaximumSize(new Dimension(widthDefault, 3 * heightDefault));
        playList.setBorder(new EmptyBorder(heightDefault, 0, 0, 0));
        try {
            Image img2 = ImageIO.read(getClass().getResource("/musicplaylist.png"));

            Image image = img2.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
            playList.setIcon(new ImageIcon(image));
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


        singer = new JLabel();
        singer.setMaximumSize(new Dimension(widthDefault, heightDefault));
        try {
            Image img = ImageIO.read(getClass().getResource("/singer.png"));
            Image image = img.getScaledInstance(imageSizeBig, imageSizeBig, Image.SCALE_SMOOTH);
            singer.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        addPlay = new JButton("Add Playlist");
        addPlay.setBackground(new Color(0x989898));
        addPlay.setForeground(new Color(0));
        addPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane newNameDialog = new JOptionPane();
                String playListName = newNameDialog.showInputDialog(playList, "new name");
                if (playListName != null && !playListName.equals("")) {
                    vector.add(playListName);
                    setPlaylist(vector);
                }
            }
        });


        vector = new Vector<>();
        playlist = new JList<>(vector);
        playlist.setForeground(new Color(0));
        playlist.setBackground(new Color(0x636363));
        vector.add("play lists:      ");
//        vector.add("mohammad");
        playlist.setListData(vector);
//        Iterator<String> iterator=vector.iterator();
//        while (iterator.hasNext()){
//            if(playlist.iterator.next()
//        }
        JScrollPane jScrollPane = new JScrollPane(playlist);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        playlist.setMaximumSize(new Dimension(30, heightDefault));


        Box box = Box.createVerticalBox();
        box.add(home);
        box.add(addToSongs);
        box.add(allSongs);
        box.add(albums);
        box.add(playList);
        box.add(jScrollPane);
        box.add(addPlay);
        box.add(Box.createVerticalGlue());
        box.add(singer);

        this.add(box);

    }

    public void setPlaylist(Vector<String> vector) {
        this.playlist.setListData(vector);
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
}
