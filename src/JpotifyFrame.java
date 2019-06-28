
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


/**
 * JpotifyFrame is the main frame that user see
 * user can see songs , playlists ,albums,library
 */
public class JpotifyFrame extends JFrame implements ShowNextFrame,JpotifyVisibility,PlaySingleTrack, PlayNext ,PlayLast , SetCurrentSongsAlbum,SetCurrentSongsAllSongs,PlayListsLinker , SetCurrentSongsPlaylist, ChangeShuffle , Serializable {

    //private String username ;

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

    private final int WIDTH = 870, HEIGHT = 700;
    private final String WINDOWS_TITLE = "Jpotify";
    private ControlPanel controlPanel;
    private Search search;
    private PlayMusicGraphics playMusic;
    private CenterPanel centerPanel;
    private ArrayList<SingleTrack> currentSongPage;
    private ArrayList<SingleTrack> currentSongSave;

    public ServerPanel getServerPanel() {
        return serverPanel;
    }

    private ServerPanel serverPanel;

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
        //JScrollPane jScrollPane = new JScrollPane(controlPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(controlPanel, BorderLayout.WEST);
        this.add(search, BorderLayout.NORTH);
        playMusic = new PlayMusicGraphics();
        this.add(playMusic, BorderLayout.SOUTH);
        JScrollPane centerScroll = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        for (int i = 0; i < centerPanel.getAllSongs().getTracks().size(); i++) {
            centerPanel.getAllSongs().getTracks().get(i).setPlaySingleTrack(this);
            centerPanel.getAllSongs().getTracks().get(i).setPlayingSongProfile(controlPanel);
            centerPanel.getAllSongs().getTracks().get(i).setPlayingSongProfile2(playMusic.getSongData());
            centerPanel.getAllSongs().getTracks().get(i).setAddTrackToShared(centerPanel.getSharedPlayListS());
            centerPanel.getAllSongs().getTracks().get(i).setAddTrackToPlaylist(controlPanel.getPlaylist());
            centerPanel.getAllSongs().getTracks().get(i).setAddTrackToFavorites(centerPanel.getFavorites());
        }

        centerPanel.getAllSongs().setUpdateSongsFrame(controlPanel);
        ArrayList<SingleTrack> temp = new ArrayList<>();
        for (int i = 0; i < centerPanel.getAllSongs().getMusicCounter(); i++) {
            temp.add(centerPanel.getAllSongs().getTracks().get(i));
        }
        currentSongPage = temp;
        playMusic.setPlayNext(this);
        playMusic.setPlayLast(this);
        playMusic.setChangeShuffle(this);
        controlPanel.setCenterPanel3(centerPanel);
        controlPanel.setCenterPanel4(centerPanel);
        for (int i = 0; i < centerPanel.getAlbums().getAlbums().size(); i++) {
            centerPanel.getAlbums().getAlbums().get(i).setCurrentSongsAlbum(this);
        }
        centerPanel.setCurrentSongsAllSongs(this);
        controlPanel.setCenterPanel5(centerPanel);
        controlPanel.setPlayListsLinker(this);
        centerPanel.setSetCurrentSongsPlaylist(this);
        this.add(centerScroll, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JpotifyFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        //this.setDefaultCloseOperation(JpotifyFrame.EXIT_ON_CLOSE);
        //this.setVisible(true);
        //this.setResizable(false);
         serverPanel = new ServerPanel();
        JScrollPane server = new JScrollPane(serverPanel , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(server , BorderLayout.EAST);

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

    /**
     * showing the next frame at singing out
     */
    @Override
    public void showFrame() {
        this.setVisible(true);
    }

    /**
     * changing the visibility of JPotifyPanel in other classes
     */
    @Override
    public void changeVisibility(boolean b) {
        setVisible(b);
    }

    /**
     * handling playing the track by clicking on track
     */
    @Override
    public void play(SingleTrack singleTrack) {
        playMusic.setSingleTrack(singleTrack);
        if (singleTrack.isLike()){
            try {
                Image img = ImageIO.read(getClass().getResource("/liked.png"));
                Image image = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                playMusic.getLikeUnlike().setIcon(new ImageIcon(image));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        else {
            try {
                Image img = ImageIO.read(getClass().getResource("/unlike.png"));
                Image image = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                playMusic.getLikeUnlike().setIcon(new ImageIcon(image));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        System.out.println(singleTrack.isLike());
        File f=singleTrack.getSingleTrack();
        playMusic.setFile(f);
        int frameLength =0;
        try {
            Mp3File mp3File = new Mp3File(f);
            frameLength = (int)mp3File.getLengthInSeconds();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }

        playMusic.getjProgressBar().setMaximum(frameLength);
        String minute = ""+frameLength/60;
        int second1 = frameLength%60;
        String second;
        if (second1<10){
        second = "0"+frameLength%60;}
        else
            second=""+second1;
        playMusic.getTimeSong().setText(minute+":"+second);
        playMusic.setFlag();
        playMusic.getPlayPauseBtn().doClick();
        //playMusic.setPlayPauseCounterPlus();
    }

    /**
     * handle playing the next song
     */
    @Override
    public void next(File f) {
        for (int i = 0; i < currentSongPage.size(); i++) {
            if (currentSongPage.get(i).getSingleTrack().equals(f)){
                if (i+1 < currentSongPage.size())
                    currentSongPage.get(i+1).getSinger_Photo().doClick();
                else if(currentSongPage.size() > 0)
                    currentSongPage.get(0).getSinger_Photo().doClick();
                break;
            }
        }
    }

    /**
     * handle playing the last song
     */
    @Override
    public void last(File f) {
        for (int i = 0; i < currentSongPage.size(); i++) {
            if (currentSongPage.get(i).getSingleTrack().equals(f)){
                if (i-1 >= 0)
                    currentSongPage.get(i-1).getSinger_Photo().doClick();
                else if(currentSongPage.size() > 0)
                    currentSongPage.get(currentSongPage.size()-1).getSinger_Photo().doClick();
                break;
            }
        }
    }

    /**
     * set a album as current list
     * @param singleTracks
     */
    @Override
    public void setCurrent(ArrayList<SingleTrack> singleTracks) {
        if (playMusic.isShuffleCounter())
            playMusic.getShuffleBtn().doClick();
        currentSongPage = singleTracks;
    }

    /**
     * setting the current page allSongs
     */
    @Override
    public void setCurrentAllSongs(ArrayList<SingleTrack> singleTracks) {
        if (playMusic.isShuffleCounter())
            playMusic.getShuffleBtn().doClick();
        currentSongPage = singleTracks;
    }

    @Override
    public void playListLinker(SinglePlayList singlePlayList) {
        singlePlayList.setCenterPanel6(centerPanel);
    }

    @Override
    public void setCurrentSongsPlaylist(ArrayList<SingleTrack> singleTracks) {
        if (playMusic.isShuffleCounter())
            playMusic.getShuffleBtn().doClick();
        currentSongPage = singleTracks;
    }

    @Override
    public void changeShuffle(boolean b) {
        if (b){
            currentSongSave = new ArrayList<>();
            for (int i = 0; i < currentSongPage.size(); i++) {
                currentSongSave.add(currentSongPage.get(i));
            }
            Collections.shuffle(currentSongPage);
        }
        else {
            currentSongPage = currentSongSave;
        }
    }
}
