import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ServerPanel extends JPanel implements AddUserToServerPanel {


    private ArrayList<SingleUser> singleUsers;
    public ServerPanel(){
        singleUsers = new ArrayList<>();
        this.setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(160 , Integer.MAX_VALUE));
        for (int i = 0; i < singleUsers.size(); i++) {
            SingleUser singleUser;
            singleUser = singleUsers.get(i);
            this.add(singleUser);
        }
        this.setVisible(true);
    }

    public void addUser(SingleUser singleUser){
        singleUsers.add(singleUser);
    }

    /**
     * adding a new user to serverPanel
     */
    @Override
    public void addUserToServerPanel(SingleUser singleUser) {
        singleUsers.add(singleUser);
        this.add(singleUser);
        this.repaint();
    }
}
