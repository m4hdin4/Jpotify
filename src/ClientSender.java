import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ClientSender implements Runnable {


    private OutputStream writer;
    private String USERNAME;
    private String SongName;
    private HashMap<String, File> songsToShare;

    public ClientSender(OutputStream outputStream, String userName, String songName, HashMap<String, File> hashMap) {
        this.SongName = songName;
        this.USERNAME = userName;
        this.writer = outputStream;
        this.songsToShare = hashMap;
    }

    @Override
    public void run() {

        DataOutputStream dataOutputStream = new DataOutputStream(writer);
        try {
            dataOutputStream.writeUTF(USERNAME);
            dataOutputStream.writeUTF(SongName);

            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*Collection<File> demoValues = songsToShare.values();
        ArrayList<File> listOfValues = new ArrayList<File>(demoValues);
        try {
            objectOutputStream.writeObject(listOfValues);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}
