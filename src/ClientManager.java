import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientManager implements Runnable, Serializable {

    private Socket clientHolder;
    private Server serverHolder;
    private InputStream fromClientStream;
    private OutputStream toClientStream;
    private DataInputStream reader;
    private DataOutputStream writer;
    private boolean flag;
    private HashMap<String,String> friendsList;


    public ClientManager(Server server, Socket socket , HashMap<String,String> hashMaph) {
        this.serverHolder = server;
        this.friendsList=hashMaph;
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
                serverHolder.addFriend(name,songName);
                serverHolder.addClientManager(name, this);
                sendTextToAllClients(name);
                sendTextToAllClients(songName);
                arrayList = (ArrayList<byte[]>) f.readObject();

                sendObjectToAllClients(arrayList);
                AppendingObjectOutputStream a = new AppendingObjectOutputStream(writer);
                TimeUnit.SECONDS.sleep(3);
                a.writeObject(friendsList);
                a.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void sendObjectToAllClients(ArrayList<byte[]> a) throws IOException {
        for (ClientManager cm : serverHolder.findAllClientManagers()) {
            cm.sendObject(a);
        }
    }

    private void sendTextToAllClients(String text) throws IOException {
        for (ClientManager cm : serverHolder.findAllClientManagers()) {
            cm.sendObject(text);
        }
    }

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
