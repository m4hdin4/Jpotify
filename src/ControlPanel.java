
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

public class ControlPanel extends JPanel {

    private final int heightDefault = 25;
    private final int widthDefault = 100;
    private final int imageSizeSmall = 30;
    private final int imageSizeBig = 90;

    JButton addToPlayList;
    JButton allSongs;
    JButton albums;
    JButton playList;
    JButton home;
    JLabel singer;
    JButton addPlay;
    JList<String> playlist;

    ProfilePhotoLinker1 musicLinker;
    SaveMusicLinker saveMusic;

    public void setSaveMusic(SaveMusicLinker saveMusic) {
        this.saveMusic = saveMusic;
    }

    public ControlPanel() {
        this.setBackground(new Color(0x636363));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addToPlayList = new JButton();
        addToPlayList.setOpaque(false);
        addToPlayList.setContentAreaFilled(false);
        addToPlayList.setBorderPainted(false);
        addToPlayList.setToolTipText("Add song...");
        addToPlayList.setMaximumSize(new Dimension(widthDefault, heightDefault));
        addToPlayList.setMargin(new Insets(0, 0, 0, 0));
        addToPlayList.setBackground(new Color(0xFFFFFF));
        addToPlayList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser musicChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                musicChooser.setDialogTitle("Select a music");
                musicChooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("mp3", "mp3");
                musicChooser.addChoosableFileFilter(filter);

                int returnValue = musicChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        musicLinker.linker(musicChooser.getSelectedFile());
                        saveMusic.linker(musicChooser.getSelectedFile());
                    } catch (InvalidDataException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedTagException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });
        try {
            Image img = ImageIO.read(getClass().getResource("/kk.png"));
            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
            addToPlayList.setIcon(new ImageIcon(image));

        } catch (Exception ex) {
            System.out.println(ex);
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
            System.out.println(ex);
        }
        allSongs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Songs songs = new Songs();
            }
        });


        albums = new JButton();
        albums.setToolTipText("Albums");
        albums.setOpaque(false);
        albums.setContentAreaFilled(false);
        albums.setBorderPainted(false);
        albums.setMaximumSize(new Dimension(widthDefault, heightDefault));
        albums.setBackground(new Color(0xFFFFFF));
        try {
            Image img = ImageIO.read(getClass().getResource("/song-playlist.png"));
            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
            albums.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }


        playList = new JButton("Your Library");
        playList.setFont(new Font("bold" , Font.ROMAN_BASELINE,12));
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
            System.out.println(ex);
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
            System.out.println(ex);
        }


        singer = new JLabel();
        singer.setMaximumSize(new Dimension(widthDefault, heightDefault));
        try {
            Image img = ImageIO.read(getClass().getResource("/singer.png"));
            Image image = img.getScaledInstance(imageSizeBig, imageSizeBig, Image.SCALE_SMOOTH);
            singer.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }


        addPlay = new JButton("Add Playlist");
        addPlay.setBackground(new Color(0x989898));
        addPlay.setForeground(new Color(0));


        Vector<String> vector = new Vector<>();
        playlist = new JList<>(vector);
        playlist.setForeground(new Color(0));
        playlist.setBackground(new Color(0x636363));
        vector.add("ali");
        vector.add("mohammad");
        playlist.setListData(vector);
        JScrollPane jScrollPane = new JScrollPane(playlist);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        playlist.setMaximumSize(new Dimension(30, heightDefault));


        Box box = Box.createVerticalBox();
        box.add(home);
        box.add(addToPlayList);
        box.add(allSongs);
        box.add(albums);
        box.add(playList);
        box.add(jScrollPane);
        box.add(addPlay);
        box.add(Box.createVerticalGlue());
        box.add(singer);

        this.add(box);

    }

    public void setMusicLinker(ProfilePhotoLinker1 musicLinker) {
        this.musicLinker = musicLinker;
    }
}
