
import javax.swing.*;
import java.awt.*;

public class JpotifyFrame extends JFrame {
    private final int WIDTH = 1000, HEIGHT = 700;
    private final String WINDOWS_TITLE = "Jpotify";
    private ControlPanel controlPanel;
    private Search search;
    private PlayMusicGraphics playMusic;
    public JpotifyFrame() {
        super();
        this.setSize(WIDTH, HEIGHT);
        this.setTitle(WINDOWS_TITLE);
        this.setMinimumSize(new Dimension(WIDTH , HEIGHT));

        controlPanel = new ControlPanel();
        this.setLayout(new BorderLayout());
        //JScrollPane jScrollPane = new JScrollPane(controlPanel);
        JScrollPane jScrollPane = new JScrollPane(controlPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(jScrollPane, BorderLayout.WEST);
        search = new Search();
        this.add(search , BorderLayout.NORTH);
        playMusic = new PlayMusicGraphics();
        this.add(playMusic , BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JpotifyFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
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
    public void setSongProfile(){

    }

    public static void main(String[] args) {
        JpotifyFrame view = new JpotifyFrame();

    }
}
