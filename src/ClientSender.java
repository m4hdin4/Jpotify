import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ClientSender implements Runnable ,Serializable{


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
        PrintWriter printWriter = new PrintWriter(writer);
        while (true) {
            ObjectOutputStream objectOutputStream = null;
            try {
                objectOutputStream = new ObjectOutputStream(writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Collection<File> demoValues = songsToShare.values();
            ArrayList<File> listOfValues = new ArrayList<File>(demoValues);
            ArrayList<byte[]> arrayList = new ArrayList<>();
            for (int i = 0; i < listOfValues.size(); i++) {
                try {
                    arrayList.add(i , Files.readAllBytes(Paths.get(listOfValues.get(i).getPath())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            DataOutputStream dataOutputStream = new DataOutputStream(writer);

            try {
                printWriter.println(USERNAME);
                printWriter.flush();
                printWriter.println(SongName);
                printWriter.flush();
                objectOutputStream.writeObject(arrayList);
                objectOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
