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

public class Client implements Runnable {

    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private InputStream inputStream;
    private OutputStream outputStream;
    private HashMap<String, File> sharedSongs;
    private ClientReciever clientReciever;

    public ClientReciever getClientReciever() {
        return clientReciever;
    }

    public void setClientReciever(ClientReciever clientReciever) {
        this.clientReciever = clientReciever;
    }

    public ClientSender getClientSender() {
        return clientSender;
    }

    public void setClientSender(ClientSender clientSender) {
        this.clientSender = clientSender;
    }

    private ClientSender clientSender;

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

    public void setSetClientReciever(SetClientReciever setClientReciever) {
        this.setClientReciever = setClientReciever;
    }

    private SetClientReciever setClientReciever;


    public Client() throws IOException, ClassNotFoundException {
        //System.exit(0);

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
        try {
            socket = new Socket("localhost", 1622);

            USERNAME = getUserNameToServer.getUserNameToServer();
            SONGNAME = getCurrentSongToServer.getCurrentSongToServer();
            if (USERNAME != null && SONGNAME != null) {
                sharedSongs = new HashMap<>();
                FileInputStream fileInputStream = new FileInputStream("C:\\Users\\mm\\Desktop\\Quera\\Jpotify\\src\\saves\\shared.tuem");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                HashMap temp = (HashMap) objectInputStream.readObject();
                sharedSongs = temp;
                objectInputStream.close();
                fileInputStream.close();
                clientSender = new ClientSender(socket.getOutputStream(), USERNAME, SONGNAME, sharedSongs);
                Thread t = new Thread(clientSender);t.start();
                clientReciever = new ClientReciever(socket.getInputStream());
                setClientReciever.setClientReciever(clientReciever);
                Thread t1 = new Thread(clientReciever);
                t1.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


