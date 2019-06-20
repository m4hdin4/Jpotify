
import javax.swing.*;
import java.awt.*;

public class JpotifyFrame extends JFrame {
    private final int WIDTH = 1300, HEIGHT = 720;
    private final String WINDOWS_TITLE = "Jpotify";
    private ControlPanel controlPanel;
    private Search search;
    private PlayMusicGraphics playMusic;
    private CenterPanel centerPanel;
    public JpotifyFrame() {
        super();
        this.setSize(WIDTH, HEIGHT);
        this.setTitle(WINDOWS_TITLE);

        controlPanel = new ControlPanel();
        this.setLayout(new BorderLayout());
        //JScrollPane jScrollPane = new JScrollPane(controlPanel);
        JScrollPane jScrollPane = new JScrollPane(controlPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(jScrollPane, BorderLayout.WEST);
        search = new Search();
        this.add(search , BorderLayout.NORTH);
        playMusic = new PlayMusicGraphics();
        this.add(playMusic , BorderLayout.SOUTH);

        centerPanel = new CenterPanel();
        JScrollPane centerScroll = new JScrollPane(centerPanel , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(centerScroll ,BorderLayout.CENTER);
        this.setDefaultCloseOperation(JpotifyFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

    }
    public void setVisible(){
        this.setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException {
       FirstFrame firstFrame = new FirstFrame();




    }
}
