
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Vector;

public class ControlPanel extends JPanel {

    private final int heightDefault = 25;
    private final int widthDefault = 100;
    private final int imageSizeSmall = 30;
    private final int imageSizeBig = 90;


    public ControlPanel() {
        this.setBackground(new Color(0x636363));
        LayoutManager M = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(M);
        JButton addToPlayList = new JButton();
        addToPlayList.setOpaque(false);
        addToPlayList.setContentAreaFilled(false);
        addToPlayList.setBorderPainted(false);
        addToPlayList.setToolTipText("Add song...");
        addToPlayList.setMaximumSize(new Dimension(widthDefault, heightDefault));
        addToPlayList.setMargin(new Insets(0, 0, 0, 0));
        addToPlayList.setBackground(new Color(0xFFFFFF));
        try {
            Image img = ImageIO.read(getClass().getResource("/kk.png"));
            Image image = img.getScaledInstance(imageSizeSmall, imageSizeSmall, Image.SCALE_SMOOTH);
            addToPlayList.setIcon(new ImageIcon(image));

        } catch (Exception ex) {
            System.out.println(ex);
        }


        JButton allSongs = new JButton();
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


        JButton albums = new JButton();
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


        JButton playList = new JButton("Your Library");
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


        JButton home = new JButton();
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


        JLabel singer = new JLabel();
        singer.setMaximumSize(new Dimension(widthDefault, heightDefault));
        try {
            Image img = ImageIO.read(getClass().getResource("/singer.png"));
            Image image = img.getScaledInstance(imageSizeBig, imageSizeBig, Image.SCALE_SMOOTH);
            singer.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            System.out.println(ex);
        }


        JButton addPlay = new JButton("Add Playlist");
        addPlay.setBackground(new Color(0x989898));
        addPlay.setForeground(new Color(0));


        Vector<String> vector = new Vector<>();
        JList<String> jList = new JList<>(vector);
        jList.setForeground(new Color(0));
        jList.setBackground(new Color(0x636363));
        vector.add("ali");
        vector.add("mohammad");
        jList.setListData(vector);
        JScrollPane jScrollPane = new JScrollPane(jList);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jList.setMaximumSize(new Dimension(30, heightDefault));


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


}
