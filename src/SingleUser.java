import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SingleUser extends JPanel {


private JButton userName;
private JLabel trackName;
private JLabel isOnline;
private JLabel time;

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    private ArrayList<File> files;
private SharedPlaylistPanelOtherUsers sharedPlaylistPanelOtherUsers;

    public void setChangeCenterPanel7(ChangeCenterPanel7 changeCenterPanel7) {
        this.changeCenterPanel7 = changeCenterPanel7;
    }

    private ChangeCenterPanel7 changeCenterPanel7;


    
    public SingleUser (String user ,String songName){
        this.setLayout(new BoxLayout(this ,BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(new Color(0x000000) , 3));
        userName = new JButton("thisName");
        userName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sharedPlaylistPanelOtherUsers = new SharedPlaylistPanelOtherUsers();
                    changeCenterPanel7.change7(sharedPlaylistPanelOtherUsers);
                    sharedPlaylistPanelOtherUsers.setFiles(files);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (InvalidDataException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedTagException e1) {
                    e1.printStackTrace();
                }
            }
        });
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
    public SingleUser returnThis(){
        return this;
    }
}
