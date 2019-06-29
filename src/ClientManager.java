import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * this class manages every client
 */
public class ClientManager implements Runnable, Serializable {

    private Socket clientHolder;
    private Server serverHolder;
    private InputStream fromClientStream;
    private OutputStream toClientStream;
    private DataInputStream reader;
    private DataOutputStream writer;
    private boolean flag;
    private HashMap<String,String> friendsList;
    private HashMap<String,ArrayList<byte[]>> friendsFiles;


    public ClientManager(Server server, Socket socket , HashMap<String,String> hashMaph , HashMap<String ,ArrayList<byte[]>> h) {
        this.serverHolder = server;
        this.friendsList=hashMaph;
        this.friendsFiles=h;
        clientHolder = socket;
        flag = false;
    }

    @Override
    public void run() {
        String name;
        String songName;
        ObjectInputStream f = null;
        try {
            writer = new DataOutputStream(clientHolder.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader = new DataInputStream(clientHolder.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            f = new ObjectInputStream(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<byte[]> arrayList;
        Scanner scanner ;
        PrintWriter printWriter = new PrintWriter(writer);
        ObjectOutputStream objectOutputStream;
        while (true) {
            try {
                scanner = new Scanner(reader);
                name = scanner.next();
                songName = scanner.next();

                serverHolder.addClientManager(name, this);
                sendTextToAllClients(name);
                sendTextToAllClients(songName);
                arrayList = (ArrayList<byte[]>) f.readObject();

                sendObjectToAllClients(arrayList);
                AppendingObjectOutputStream a = new AppendingObjectOutputStream(writer);
                TimeUnit.SECONDS.sleep(3);
                a.writeObject(friendsList);
                a.writeObject(friendsFiles);
                a.flush();
                serverHolder.addFriend(name,songName);
                serverHolder.addFriendsFiles(name , arrayList);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * sending object to all clients
     * @param a the object tat you want to sent
     * @throws IOException
     */
    private void sendObjectToAllClients(ArrayList<byte[]> a) throws IOException {
        for (ClientManager cm : serverHolder.findAllClientManagers()) {
            cm.sendObject(a);
        }
    }

    /**
     * sending text to all clients
     * @param text the text that you want to send
     * @throws IOException
     */
    private void sendTextToAllClients(String text) throws IOException {
        for (ClientManager cm : serverHolder.findAllClientManagers()) {
            cm.sendObject(text);
        }
    }

    /**
     * sending object
     * @param a the object that you want to send
     * @throws IOException
     */
    private void sendObject(ArrayList<byte[]> a) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientHolder.getOutputStream());
        objectOutputStream.writeObject(a);
        objectOutputStream.flush();
    }

    private void sendObject(String text) throws IOException {
        PrintWriter p = new PrintWriter(writer);
        p.println(text);
        p.flush();
    }


}
