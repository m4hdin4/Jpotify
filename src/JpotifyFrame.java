
import javax.swing.*;
import java.awt.*;

public class JpotifyFrame extends JFrame implements ShowNextFrame,JpotifyVisibility{
    private final int WIDTH = 1000, HEIGHT = 700;
    private final String WINDOWS_TITLE = "Jpotify";
    private ControlPanel controlPanel;
    private Search search;
    private PlayMusicGraphics playMusic;
    private CenterPanel centerPanel;

    public JpotifyFrame() {
        super();
        this.setSize(WIDTH, HEIGHT);
        this.setTitle(WINDOWS_TITLE);
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setVisible(false);

        controlPanel = new ControlPanel();
        search = new Search();
        centerPanel = new CenterPanel();

        controlPanel.setMusicLinker(centerPanel);

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
