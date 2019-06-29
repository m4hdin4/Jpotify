import javazoom.jl.decoder.JavaLayerException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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

        ObjectInputStream objectInputStream =null;

        ArrayList<byte[]> arrayList=null;
        String name = "";
        String songName = "";
        HashMap<String,String> friendslist;
        name = scanner.nextLine();
        songName = scanner.nextLine();
        SingleUser newSingleUser = new SingleUser(name, songName);
        singleUserToServerPanel.addSingleUserToServer(newSingleUser);
        while (true) {


            try {
                objectInputStream = new ObjectInputStream(reader);
                arrayList = (ArrayList<byte[]>) objectInputStream.readObject();
                TimeUnit.SECONDS.sleep(3);
                friendslist = (HashMap<String, String>) objectInputStream.readObject();
                if(friendslist!=null&&friendslist.size()!=0){
                    for(String s:friendslist.keySet()){
                        SingleUser singleUser = new SingleUser(s,friendslist.get(s));
                        singleUserToServerPanel.addSingleUserToServer(singleUser);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            File f = new File("C:\\Users\\mm\\Desktop\\files\\mehdi.mp3");
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
