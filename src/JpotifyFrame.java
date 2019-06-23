
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;


/**
 * JpotifyFrame is the main frame that user see
 * user can see songs , playlists ,albums,library
 */
public class JpotifyFrame extends JFrame implements ShowNextFrame,JpotifyVisibility{

    //private String username ;

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

    private final int WIDTH = 1000, HEIGHT = 700;
    private final String WINDOWS_TITLE = "Jpotify";
    private ControlPanel controlPanel;
    private Search search;
    private PlayMusicGraphics playMusic;
    private CenterPanel centerPanel;

    public JpotifyFrame() throws JavaLayerException, FileNotFoundException {
        super();
        this.setSize(WIDTH, HEIGHT);
        this.setTitle(WINDOWS_TITLE);
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setVisible(false);


        //username = "tuem";

        controlPanel = new ControlPanel();
        search = new Search();
        centerPanel = new CenterPanel();

        //saveAccount = new SaveAccount( this);
//        saveAccount.addFile(new File("C:\\Users\\BPTEC-32338485\\Desktop\\jpotifyMusics\\Sasy.mp3"));
//        saveAccount.addFile(new File("C:\\Users\\BPTEC-32338485\\Desktop\\jpotifyMusics\\Lady Gaga.mp3"));

        controlPanel.setMusicLinker(centerPanel.getAllSongs());

        this.setLayout(new BorderLayout());
        //JScrollPane jScrollPane = new JScrollPane(controlPanel);
        JScrollPane jScrollPane = new JScrollPane(controlPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(jScrollPane, BorderLayout.WEST);
        this.add(search, BorderLayout.NORTH);
        playMusic = new PlayMusicGraphics();
        this.add(playMusic, BorderLayout.SOUTH);
        JScrollPane centerScroll = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(centerScroll, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JpotifyFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        //this.setDefaultCloseOperation(JpotifyFrame.EXIT_ON_CLOSE);
        //this.setVisible(true);
        //this.setResizable(false);

    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public Search getSearch() {
        return search;
    }

    public PlayMusicGraphics getPlayMusic() {
        return playMusic;
    }

    public CenterPanel getCenterPanel() {
        return centerPanel;
    }

    @Override
    public void showFrame() {
        this.setVisible(true);
    }

    @Override
    public void changeVisibility(boolean b) {
        setVisible(b);
    }

}
