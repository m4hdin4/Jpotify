import javazoom.jl.player.advanced.PlaybackListener;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Client implements Runnable  {

    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private InputStream inputStream;
    private OutputStream outputStream;
    private HashMap<String, File> sharedSongs;

    public HashMap<String, File> getSharedSongs() {
        return sharedSongs;
    }

    public void setSharedSongs(HashMap<String, File> sharedSongs) {
        this.sharedSongs = sharedSongs;
    }


    public void setGetUserNameToServer(GetUserNameToServer getUserNameToServer) {
        this.getUserNameToServer = getUserNameToServer;
    }

    public void setGetCurrentSongToServer(GetCurrentSongToServer getCurrentSongToServer) {
        this.getCurrentSongToServer = getCurrentSongToServer;
    }

    private GetCurrentSongToServer getCurrentSongToServer;
    private GetUserNameToServer getUserNameToServer;
    private String USERNAME;
    private String SONGNAME;


    public Client() throws IOException, ClassNotFoundException {
        sharedSongs = new HashMap<>();

        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\mm\\Desktop\\Quera\\Jpotify\\src\\saves\\shared.tuem");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        HashMap temp = (HashMap) objectInputStream.readObject();
        sharedSongs = temp;
        objectInputStream.close();
        fileInputStream.close();
//        long fileLength = file.length();
//        byte[] fileData = new byte[(int)fileLength];
//        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
//        bis.read(fileData, 0, fileData.length);
//        OutputStream os = socket.getOutputStream();
//        os.write(fileData, 0, fileData.length);
//        os.flush();



    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
            System.out.println("fuck");

            try {
                socket = new Socket("localhost", 1630);
            } catch (IOException e) {
                e.printStackTrace();
            }

        USERNAME = getUserNameToServer.getUserNameToServer();
        SONGNAME = getCurrentSongToServer.getCurrentSongToServer();


        if (USERNAME != null || SONGNAME != null) {
                    USERNAME = getUserNameToServer.getUserNameToServer();
                    SONGNAME = getCurrentSongToServer.getCurrentSongToServer();
                    try {
                        dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        dataInputStream = new DataInputStream(socket.getInputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        dataOutputStream.writeUTF(USERNAME);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        dataOutputStream.writeUTF(SONGNAME);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        dataOutputStream.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                Set<String> keys = sharedSongs.keySet();
                String[] array = keys.toArray(new String[keys.size()]);
        try {

            ObjectOutputStream oi = new ObjectOutputStream(socket.getOutputStream());
            oi.writeObject(array);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < sharedSongs.size(); i++) {

        }


    }


//    public static void main(String[] args) throws IOException {
//        new Client();
//    }
/*public static void main(String[] args) throws IOException {
    new Client();
}*/
}
