import javazoom.jl.decoder.JavaLayerException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientReciever extends Thread {

    InputStream reader;

    public void setSingleUserToServerPanel(AddSingleUserToServerPanel singleUserToServerPanel) {
        this.singleUserToServerPanel = singleUserToServerPanel;
    }

    private AddSingleUserToServerPanel singleUserToServerPanel;

    public ClientReciever(InputStream inputStream) throws IOException {
        this.reader = inputStream;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(reader);
        DataInputStream dataInputStream = new DataInputStream(reader);
        ObjectInputStream objectInputStream = null;
        ArrayList<byte[]> arrayList=null;
        String name = "";
        String songName = "";
        while (true) {
            name = scanner.nextLine();
            songName = scanner.nextLine();
            System.out.println(name);
            System.out.println(songName);
            SingleUser newSingleUser = new SingleUser(name, songName);
            singleUserToServerPanel.addSingleUserToServer(newSingleUser);
            try {
                objectInputStream = new ObjectInputStream(reader);
                arrayList = (ArrayList<byte[]>) objectInputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(arrayList!=null) {
                System.out.println("kir");
                File f = new File("C:\\mehdi.mp3");
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(f);
                    fileOutputStream.write(arrayList.get(0));
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    MusicPlayer musicPlayer = new MusicPlayer(new FileInputStream(f));
                    musicPlayer.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
