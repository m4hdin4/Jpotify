import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class SharedPlayListS extends JPanel implements AddTrackToShared {
    public ArrayList<Share> getShared() {
        return shared;
    }

    public void setShared(ArrayList<Share> shared) {
        this.shared = shared;
    }

    private ArrayList<Share> shared;
    private int sharedCounter;
    private HashMap <String , File> sharedList;

    public HashMap<String, File> getSharedList() {
        return sharedList;
    }

    public void setSharedList(HashMap<String, File> sharedList) {
        this.sharedList = sharedList;
    }



    public SharedPlayListS(){
        shared = new ArrayList<>();
        sharedList = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            shared.add(new Share());
            this.add(shared.get(i));
            shared.get(i).setVisible(false);
        }
        this.setLayout(new GridLayout(30 , 1));
    }

    @Override
    public void addTrackToShared(SingleTrack singleTrack) {
        for (int i = 0; i < sharedCounter; i++) {
            if (shared.get(i).getSingleTrack().equals(singleTrack))
                return;
        }
        sharedList.put(singleTrack.getTrackName() , singleTrack.getSingleTrack());
        shared.get(sharedCounter).setSingleTrack(singleTrack);
        shared.get(sharedCounter).setText(singleTrack.getTrackName());
        shared.get(sharedCounter).setVisible(true);
        sharedCounter++;
    }
}
