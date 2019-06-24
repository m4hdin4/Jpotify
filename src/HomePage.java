import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * HomePage class is the central JPanel ih JPotifyFrame
 * user can see songs and albums and can add song
 */
public class HomePage extends JPanel {
    private final int pictureSize = 240;
    private JButton addSong;
    private JButton albums;
    private JButton allSongs;
    private JPanel singleSongs;

    public void setCenterPanel1(ChangeCenterPanel1 centerPanel1) {
        this.centerPanel1 = centerPanel1;
    }

    private ChangeCenterPanel1 centerPanel1;

    public void setMusicLinker(ProfilePhotoLinker1 musicLinker) {
        this.musicLinker = musicLinker;
    }

    public void setSaveMusic(SaveMusicLinker saveMusic) {
        this.saveMusic = saveMusic;
    }

    private ProfilePhotoLinker1 musicLinker;
    private SaveMusicLinker saveMusic;

    public HomePage() {
        setLayout(new GridLayout(2 , 1));
        addSong = new JButton();
        albums = new JButton();
        allSongs = new JButton();
        singleSongs = new JPanel();
        singleSongs.setOpaque(false);
        allSongs.setOpaque(false);
        albums.setOpaque(false);
        addSong.setOpaque(false);
        addSong.setBorderPainted(false);
        this.setBackground(new Color(0x202020));
        singleSongs.setBackground(new Color(0x202020));
        albums.setBackground(new Color(0x202020));
        addSong.setBackground(new Color(0x202020));
        allSongs.setBackground(new Color(0x202020));



        try {
            Image img = ImageIO.read(getClass().getResource("/homeaddsong.png"));
            Image image = img.getScaledInstance(pictureSize, pictureSize, Image.SCALE_SMOOTH);
            addSong.setIcon(new ImageIcon(image));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            Image img = ImageIO.read(getClass().getResource("/homealbum.png"));
            Image image = img.getScaledInstance(pictureSize, pictureSize, Image.SCALE_SMOOTH);
            albums.setIcon(new ImageIcon(image));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            Image img = ImageIO.read(getClass().getResource("/homesong.png"));
            Image image = img.getScaledInstance(pictureSize, pictureSize, Image.SCALE_SMOOTH);
            allSongs.setIcon(new ImageIcon(image));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        addSong.addActionListener(new ActionListener() {
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
        allSongs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel1.change1();
            }
        });

        singleSongs.setLayout(new GridLayout(1 , 2));
        singleSongs.add(addSong);
        singleSongs.add(allSongs);
        this.add(singleSongs);
        this.add(albums);
    }

}
