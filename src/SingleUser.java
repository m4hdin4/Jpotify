import javax.swing.*;
import java.awt.*;

public class SingleUser extends JPanel {


private JButton userName;
private JLabel trackName;
private JLabel isOnline;
private JLabel time;
    
    public SingleUser (String user ,String songName){
        this.setLayout(new BoxLayout(this ,BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(new Color(0x2CA3A3) , 3));
        userName = new JButton("thisName");
        trackName = new JLabel("trackName");
        isOnline = new JLabel("online");
        time = new JLabel(" ");
        userName.setText(user);
        trackName.setText(songName);
        this.add(userName);
        this.add(trackName);
        this.add(isOnline);
        this.add(time);
    }
}
