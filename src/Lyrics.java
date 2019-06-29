import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;

import javax.swing.*;
import java.awt.*;

public class Lyrics extends JFrame {
    private JLabel jLabel;
    public Lyrics(String song , String artist) throws MusixMatchException {
        jLabel = new JLabel();
        this.setSize(500 , 500);
        this.setResizable(false);
        String apiKey = "5c816ea1678c68472364e0d23f9e4302";
        MusixMatch musixMatch = new MusixMatch(apiKey);
        String trackName = song;
        String artistName = artist;

        Track track;

        track = musixMatch.getMatchingTrack(trackName, artistName);

        TrackData data = track.getTrack();

        int trackID = data.getTrackId();

        org.jmusixmatch.entity.lyrics.Lyrics lyrics = null;
        try {
            lyrics = musixMatch.getLyrics(trackID);
        } catch (MusixMatchException e) {
            e.printStackTrace();
        }

        jLabel.setText("<html>" + lyrics.getLyricsBody().replaceAll("\n", "<br/>") + "</html>");
        this.setLayout(new BorderLayout());
        this.add(jLabel , BorderLayout.CENTER);
    }

}
