import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeHandler {

    public TimeHandler(){
        int delay = 3000; // Delay in milliseconds
        JpotifyFrame jpotifyFrame = new JpotifyFrame();
        FirstFrame firstFrame = new FirstFrame();
        Timer timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstFrame.setVisible(false); // Show the second frame
                jpotifyFrame.setVisible(true); // Hide the first frame
            }
        });
        timer.start();
    }
}
