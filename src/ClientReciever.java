import javazoom.jl.decoder.JavaLayerException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * this class is for recieving informations from other clients
 */
public class ClientReciever extends Thread {
    private ArrayList<File> convert (ArrayList<byte[]> arrayList){
        ArrayList<File> files = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            try (FileOutputStream fileOuputStream = new FileOutputStream("C:\\Users\\BPTEC-32338485\\Desktop\\files\\mehdi"+i+".mp3")){
                fileOuputStream.write(arrayList.get(i));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            files.add(new File("C:\\Users\\BPTEC-32338485\\Desktop\\files\\mehdi"+i+".mp3"));
        }
        return files;
    }

    private InputStream reader;

    public void setSetSingleUserOptions(SetSingleUserOptions setSingleUserOptions) {
        this.setSingleUserOptions = setSingleUserOptions;
    }

    private SetSingleUserOptions setSingleUserOptions;

    public void setSingleUserToServerPanel(AddSingleUserToServerPanel singleUserToServerPanel) {
        this.singleUserToServerPanel = singleUserToServerPanel;
    }

    private AddSingleUserToServerPanel singleUserToServerPanel;
    private String userName;

    public ClientReciever(InputStream inputStream ,String userName) throws IOException {
        this.reader = inputStream;
        this.userName = userName;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(reader);

        ObjectInputStream objectInputStream = null;
        ArrayList<byte[]> arrayList = null;
        String name = "";
        String songName = "";

        HashMap<String,String> friendslist;
        HashMap<String,ArrayList<byte[]>> friendsFiles;
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
                TimeUnit.SECONDS.sleep(3);
                friendsFiles = (HashMap<String , ArrayList<byte[]>>) objectInputStream.readObject();
                if (friendslist != null && friendslist.size() != 0) {
                    for (String s : friendslist.keySet()) {

                        SingleUser singleUser = new SingleUser(s, friendslist.get(s));
                        singleUserToServerPanel.addSingleUserToServer(singleUser);
                    }
                }
                if(friendsFiles.size()!=0) {
                    newSingleUser.setFiles(convert(friendsFiles.get(0)));
                }
                else {
                    newSingleUser.setFiles(convert(arrayList));
                }
                setSingleUserOptions.setSingleUserOptions(newSingleUser);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            if (arrayList != null) {
//                File f = new File("C:\\Users\\BPTEC-32338485\\Desktop\\files\\mehdi.mp3");
//                try {
//                    FileOutputStream fileOutputStream = new FileOutputStream(f);
//                    fileOutputStream.write(arrayList.get(0));
//                    fileOutputStream.close();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    MusicPlayer musicPlayer = new MusicPlayer(new FileInputStream(f));
//                    musicPlayer.play();
//                } catch (JavaLayerException e) {
//                    e.printStackTrace();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            }

        }

    }
}
